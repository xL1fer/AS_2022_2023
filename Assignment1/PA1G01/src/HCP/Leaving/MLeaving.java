/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.Leaving;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import HCP.Log.ILog_Customer;
import HCP.Log.ILog_Porter;

/**
 *
 * Leaving monitor.
 */
public class MLeaving implements ILeaving {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Wait leave synchronization condition.
     */
    private final Condition cWaitLeave;
    /**
     * Wait customer synchronization condition.
     */
    private final Condition cWaitCustomer;
    /**
     * Wait resume button synchronization condition.
     */
    private final Condition cWaitResumeButton;
    /**
     * Customer message log monitor.
     */
    private final ILog_Customer mLogCustomer;
    /**
     * Porter message log monitor.
     */
    private final ILog_Porter mLogPorter;
    /**
     * Outside queue.
     */
    private int[] outsideQueue;
    /**
     * Outside queue write pointer.
     */
    private int queueWrite;
    /**
     * Outside queue read pointer.
     */
    private int queueRead;
    /**
     * Front door status.
     */
    private boolean frontDoorOpen;
    /**
     * Current customers in leaving hall.
     */
    private int currentCustomers;
    /**
     * Number of customers that need to be in leaving hall for the porter to open front door.
     */
    private int nextCustomers;
    /**
     * Simulation suspended status.
     */
    private boolean isSuspended;
    /**
     * Simulation mode status.
     */
    private boolean isAuto;
    
    /**
     * Leaving class constructor.
     * 
     * @param mLogCustomer customer message log monitor
     * @param mLogPorter porter message log monitor
     */
    private MLeaving(ILog_Customer mLogCustomer, ILog_Porter mLogPorter) {
        this.rl = new ReentrantLock();
        this.cWaitLeave = rl.newCondition();
        this.cWaitCustomer = rl.newCondition();
        this.cWaitResumeButton = this.rl.newCondition();
        this.mLogCustomer = mLogCustomer;
        this.mLogPorter = mLogPorter;
        this.outsideQueue = new int[9];
        this.queueWrite = -1;
        this.queueRead = -1;
        this.frontDoorOpen = false;
        this.currentCustomers = 0;
        this.nextCustomers = 9;
        this.isSuspended = false;
        this.isAuto = true;
    }

    /**
     * Create instance of leaving monitor.
     * 
     * @param mLogCustomer customer message log monitor
     * @param mLogPorter porter message log monitor
     * @return leaving monitor instance
     */
    public static ILeaving getInstance(ILog_Customer mLogCustomer, ILog_Porter mLogPorter) {
        return new MLeaving(mLogCustomer, mLogPorter);
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

    @Override
    public void waitLeave(int customerId) {
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

            this.mLogCustomer.customer_logLeaving(customerId, currentCustomers);
            this.currentCustomers++;
            
            System.out.printf("> Customer %d is waiting to leave the hostel\n", customerId);
            this.cWaitCustomer.signalAll();
            
            // wait front door to be open to leave hostel
            while (!this.frontDoorOpen) {
                try {
                    this.cWaitLeave.await();
                } catch (InterruptedException ex){}
            }
            
            //this.mLogCustomer.customer_logOutside(customerId, this.nextCustomers - currentCustomers);
            this.currentCustomers--;
            
            // add customer to outside queue
            this.outsideQueue[++this.queueWrite] = customerId;
            
            System.out.printf("> Customer %d LEFT the hostel\n", customerId);
            // signal porter that the last customer left the hostel
            if (currentCustomers == 0)
                this.cWaitCustomer.signalAll();
            
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public int waitArrival(int porterTotalCustomers) {
        try {
            this.rl.lock();

            System.out.print("> Porter is waiting for all floor customers\n");
            
            // get the next amount of customers
            this.nextCustomers = 9;
            if (porterTotalCustomers < this.nextCustomers)
                this.nextCustomers = porterTotalCustomers;
            
            // wait for the arrival of all current floor customers
            while (this.currentCustomers < this.nextCustomers) {
                try {
                    this.cWaitCustomer.await();
                } catch (InterruptedException ex){}
            }
            
            // remove the number of customers that are going to leave the hostel
            //porter.setTotalCustomers(porter.getTotalCustomers() - nextCustomers);
            return nextCustomers;
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void openDoor(int porterTotalCustomers) {
        try {
            this.rl.lock();
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }

            this.frontDoorOpen = true;
            System.out.print("> Porter is opening front door\n");
            this.mLogPorter.porter_logDoorCheckOut(this.frontDoorOpen);
            this.cWaitLeave.signalAll();
            
            // wait for all customers to leave the hostel
            while (this.currentCustomers != 0) {
                try {
                    this.cWaitCustomer.await();
                } catch (InterruptedException ex){}
            }
            
            this.mLogPorter.porter_logOutside(this.outsideQueue, this.nextCustomers);

            this.queueWrite = -1;
            this.queueRead = -1;
            
            this.frontDoorOpen = false;
            System.out.print("> Porter is closing front door\n");
            this.mLogPorter.porter_logDoorCheckOut(this.frontDoorOpen);
            
            if (porterTotalCustomers == 0)
                this.mLogPorter.porter_logIdle();

        } finally {
            this.rl.unlock();
        }
    }
}
