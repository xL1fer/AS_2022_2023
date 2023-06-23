/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.MealRoom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import HCP.Log.ILog_Customer;
import HCP.Log.ILog_Waiter;
import HCP.Main.Main;

/**
 *
 * Meal Room monitor.
 */
public class MMealRoom implements IMealRoom {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Wait floor synchronization condition.
     */
    private final Condition cWaitFloor;
    /**
     * Wait breakfast synchronization condition.
     */
    private final Condition cWaitBreakfast;
    /**
     * Wait eat synchronization condition.
     */
    private final Condition cWaitEat;
    /**
     * Wait resume button synchronization condition.
     */
    private final Condition cWaitResumeButton;
    /**
     * Customer message log monitor.
     */
    private final ILog_Customer mLogCustomer;
    /**
     * Waiter message log monitor.
     */
    private final ILog_Waiter mLogWaiter;
    /**
     * Customer breakfast time.
     */
    private int timeBreakfast;
    /**
     * Breakfast queue.
     */
    private int[] breakfastQueue;
    /**
     * Check-in queue write pointer.
     */
    private int queueWrite;
    /**
     * Check-in queue read pointer.
     */
    private int queueRead;
    /**
     * Current customers in meal room.
     */
    private int currentCustomers;
    /**
     * Customer being served id.
     */
    private int servingCustomer;
    /**
     * Simulation suspended status.
     */
    private boolean isSuspended;
    /**
     * Simulation mode status.
     */
    private boolean isAuto;
    
    /**
     * Meal room class constructor.
     * 
     * @param mLogCustomer customer message log monitor
     * @param mLogWaiter waiter message log monitor
     */
    private MMealRoom(ILog_Customer mLogCustomer, ILog_Waiter mLogWaiter) {
        this.rl = new ReentrantLock();
        this.cWaitFloor = rl.newCondition();
        this.cWaitBreakfast = rl.newCondition();
        this.cWaitEat = rl.newCondition();
        this.cWaitResumeButton = this.rl.newCondition();
        this.mLogCustomer = mLogCustomer;
        this.mLogWaiter = mLogWaiter;
        this.timeBreakfast = 100;
        this.breakfastQueue = new int[9];
        this.queueWrite = -1;
        this.queueRead = -1;
        this.currentCustomers = 0;
        this.servingCustomer = 0;
        this.isSuspended = false;
        this.isAuto = true;
    }

    /**
     * Create instance of meal room monitor.
     * 
     * @param mLogCustomer customer message log monitor
     * @param mLogWaiter waiter message log monitor
     * @return meal room monitor instance
     */
    public static IMealRoom getInstance(ILog_Customer mLogCustomer, ILog_Waiter mLogWaiter) {
        return new MMealRoom(mLogCustomer, mLogWaiter);
    }
    
    /**
     * Update breakfast queue GUI.
     */
    public void updateBreakfastQueueGUI() {
        String[] strings = new String[9];
        for (int i = 0; i < 9; i++) {
            if (this.breakfastQueue[i] < 10) {
                strings[i] = "0";
                strings[i] += Integer.toString(this.breakfastQueue[i]);
            }
            else
                strings[i] = Integer.toString(this.breakfastQueue[i]);
        }
        Main.mealQueue.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    @Override
    public void setTimeBreakfast(int timeBreakfast) {
        this.timeBreakfast = timeBreakfast;
    }
    
    @Override
    public void suspendSimulation() {
        this.isSuspended = true;
    }
    
    @Override
    public void resumeSimulation(boolean resume) {
        try {
            this.rl.lock();
            
            if (!this.isAuto && !resume)
                this.cWaitResumeButton.signalAll();
            
            if (resume) {
                this.isSuspended = false;
                if (this.isAuto)
                    this.cWaitResumeButton.signalAll();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void manualSimulation() {
        this.isAuto = false;
    }
    
    @Override
    public void autoSimulation() {
        try {
            this.rl.lock();
            this.isAuto = true;
            this.cWaitResumeButton.signalAll();
        } finally {
            this.rl.unlock();
        }
    }
    
    /**
     * Get customer queue pos based on his id.
     * 
     * @param customerId customer id
     * @return customer queue pos
     */
    public int getCustomerQueuePos(int customerId) {
        for (int i = 0; i < 9; i++) {
            if (this.breakfastQueue[i] == customerId) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void waitBreakfast(int customerId) {
        try {
            this.rl.lock();
            this.breakfastQueue[++this.queueWrite] = customerId;
            //customer.setQueuePos(this.queueWrite);
            System.out.printf("> Customer %d is waiting for breakfast (queue pos = %d)\n", customerId, this.queueWrite);
            
            this.mLogCustomer.customer_logMealroom(customerId, getCustomerQueuePos(customerId));
            
            updateBreakfastQueueGUI();
            
            this.currentCustomers++;
            this.cWaitFloor.signalAll();
            
            while (this.servingCustomer != customerId) {
                try {
                    this.cWaitBreakfast.await();
                } catch (InterruptedException ex){}
            }
            
            this.servingCustomer = 0;
            // signal waiter customer has the meal
            this.cWaitEat.signalAll();
            
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void takeBreakfast(int customerId) {
        try {
            System.out.printf("> Customer %d is taking breakfast\n", customerId);
            this.mLogCustomer.customer_logBreakfast(customerId, getCustomerQueuePos(customerId));
            Thread.sleep(this.timeBreakfast);
        } catch (InterruptedException ex) {}
        
        try {
            this.rl.lock();
            
            /*
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
            */
            
            this.breakfastQueue[getCustomerQueuePos(customerId)] = 0;
            
            updateBreakfastQueueGUI();
            
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public int waitMealRoomCustomers(int waiterTotalCustomers) {
        try {
            this.rl.lock();

            // reset queue read and queue write
            this.queueRead = -1;
            this.queueWrite = -1;
            
            // all floors were served
            if (waiterTotalCustomers == 0)
                return 0;
            
            System.out.print("> Waiter is waiting to serve customers\n");
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
            
            // get the next amount of customers
            int nextCustomers = 9;
            if (waiterTotalCustomers < nextCustomers)
                nextCustomers = waiterTotalCustomers;
            
            while (this.currentCustomers < nextCustomers) {
                try {
                    this.cWaitFloor.await();
                } catch (InterruptedException ex){}
            }
            // remove the number of customers that are going to be served
            //waiterTotalCustomers -= nextCustomers;
            return nextCustomers;
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public boolean serveBreakfast() {        
        try {
            this.rl.lock();
            
            // whole floor served, reset current customers and exit serving breakfast
            if (this.queueRead == this.currentCustomers - 1)
            {
                this.currentCustomers = 0;
                return false;
            }
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
            
            this.servingCustomer = this.breakfastQueue[++this.queueRead];
            System.out.printf("> Waiter is serving breakfast to customer %d\n", this.servingCustomer);
            
            this.mLogWaiter.waiter_logServing(this.queueRead);
            
            // signal customers waiting for breakast
            this.cWaitBreakfast.signalAll();
            
            // wait served customer to have breakfast
            while (this.servingCustomer != 0) {
                try {
                    this.cWaitEat.await();
                } catch (InterruptedException ex){}
            }
            
        } finally {
            this.rl.unlock();
        }
        
        return true;
    }
}
