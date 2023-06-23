/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.CheckIn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import HCP.Log.ILog_Customer;
import HCP.Log.ILog_Porter;
import HCP.Log.ILog_Receptionist;
import HCP.Main.Main;

/**
 *
 * Check In monitor.
 */
public class MCheckIn implements ICheckIn {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Wait reception synchronization condition.
     */
    private final Condition cWaitReception;
    /**
     * Wait customer synchronization condition.
     */
    private final Condition cWaitCustomer;
    /**
     * Wait arrival synchronization condition.
     */
    private final Condition cWaitArrival;
    /**
     * Wait check-in synchronization condition.
     */
    private final Condition cWaitCheckIn;
    /**
     * Wait resume button synchronization condition.
     */
    private final Condition cWaitResumeButton;
    /**
     * Wait queue positioning synchronization condition.
     */
    private final Condition cWaitQueuePositioning;
    /**
     * Customer message log monitor.
     */
    private final ILog_Customer mLogCustomer;
    /**
     * Porter message log monitor.
     */
    private final ILog_Porter mLogPorter;
    /**
     * Receptionist message log monitor.
     */
    private final ILog_Receptionist mLogReceptionist;
    /**
     * Check-in queue.
     */
    private final int[] checkInQueue;
    /**
     * Check-in queue write pointer.
     */
    private int queueWrite;
    /**
     * Check-in queue read pointer.
     */
    private int queueRead;
    /**
     * Reception queue.
     */
    private final int[] receptionists;
    /**
     * Customer check-in time.
     */
    private int timeCheckIn;
    /**
     * Check-in floor assignment.
     */
    private int floorNumber;
    /**
     * Check-in bedroom assignment.
     */
    private int bedRoomNumber;
    /**
     * Check-in bed assignment.
     */
    private int bedRoomTotal;
    /**
     * Front door status.
     */
    private boolean frontDoorOpen;
    /**
     * Remaining check-ins.
     */
    private int remainingCheckIns;
    /**
     * Remaining customers.
     */
    private int remainingCustomers;
    /**
     * Next receptionist to call a customer.
     */
    private int nextReceptionist;
    /**
     * Porter total customers inside the hostel reference.
     */
    private int porterTotalCustomers;
    /**
     * Waiter total customers inside the hostel reference.
     */
    private int waiterTotalCustomers;
    /**
     * Simulation suspended status.
     */
    private boolean isSuspended;
    /**
     * Simulation mode status.
     */
    private boolean isAuto;
    /**
     * Each customer assigned bed offset
     */
    private int[] customersBedOffset;
    /**
     * Current bed offset
     */
    private int curBedOffset;
    
    /**
     * Check-in class constructor.
     * 
     * @param mLogCustomer customer message log monitor
     * @param mLogPorter porter message log monitor
     * @param mLogReceptionist receptionist message log monitor
     */
    private MCheckIn(ILog_Customer mLogCustomer, ILog_Porter mLogPorter, ILog_Receptionist mLogReceptionist) {
        this.rl = new ReentrantLock();
        this.cWaitReception = rl.newCondition();
        this.cWaitCustomer = rl.newCondition();
        this.cWaitArrival = rl.newCondition();
        this.cWaitCheckIn = rl.newCondition();
        this.cWaitResumeButton = this.rl.newCondition();
        this.cWaitQueuePositioning = this.rl.newCondition();
        this.mLogCustomer = mLogCustomer;
        this.mLogPorter = mLogPorter;
        this.mLogReceptionist = mLogReceptionist;
        
        this.checkInQueue = new int[6];
        this.queueWrite = -1;
        this.queueRead = -1;
        this.receptionists = new int[3];
        this.timeCheckIn = 100;
        this.floorNumber = 1;
        this.bedRoomNumber = 1;
        this.bedRoomTotal = 0;
        this.frontDoorOpen = true;
        this.remainingCheckIns = 0;
        this.remainingCustomers = -1;
        this.nextReceptionist = 1;
        this.porterTotalCustomers = 0;
        this.waiterTotalCustomers = 0;
        this.isSuspended = false;
        this.isAuto = true;
        this.customersBedOffset = new int[27];
        this.curBedOffset = 0;
    }
    
    /**
     * Create instance of check-in monitor.
     * 
     * @param mLogCustomer customer message log monitor
     * @param mLogPorter porter message log monitor
     * @param mLogReceptionist receptionist message log monitor
     * @return check-in monitor instance
     */
    public static ICheckIn getInstance(ILog_Customer mLogCustomer, ILog_Porter mLogPorter, ILog_Receptionist mLogReceptionist) {
        return new MCheckIn(mLogCustomer, mLogPorter, mLogReceptionist);
    }
    
    /**
     * Update check-in queue GUI.
     */
    public void updateCheckInQueueGUI() {
        String[] strings = new String[6];
        for (int i = 0, j = 5; i < 6; i++, j--) {
            if (this.checkInQueue[i] < 10) {
                strings[j] = "0";
                strings[j] += Integer.toString(this.checkInQueue[i]);
            }
            else
                strings[j] = Integer.toString(this.checkInQueue[i]);
        }
        Main.checkInQueue.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    /**
     * Update reception queue GUI.
     */
    public void updateReceptionQueueGUI() {
        String[] strings = new String[3];
        for (int i = 0; i < 3; i++) {
            if (this.receptionists[i] < 10) {
                strings[i] = "0";
                strings[i] += Integer.toString(this.receptionists[i]);
            }
            else
                strings[i] = Integer.toString(this.receptionists[i]);
        }
        Main.receptionQueue.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    @Override
    public void setTimeCheckIn(int timeCheckIn) {
        this.timeCheckIn = timeCheckIn;
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
    public int getCustomerBedOffset(int customerId) {
        try {
            this.rl.lock();
            
            return this.customersBedOffset[customerId - 1];
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void inQueue(int customerId){
        try {
            this.rl.lock();
            
            this.checkInQueue[++this.queueWrite] = customerId;
            //customer.setQueuePos(this.queueWrite);
            this.remainingCheckIns++;
            System.out.printf("> Customer %d is in queue pos %d\n", customerId, this.queueWrite);
            
            updateCheckInQueueGUI();
            
            // signal the porter that the customer is ready in the queue
            this.cWaitQueuePositioning.signalAll();
            
            // wait for turn
            int rPos;
            while ((rPos = receptionPos(customerId)) == -1) {
                try {
                    this.cWaitReception.await();
                } catch (InterruptedException ex){}
            }
            this.mLogCustomer.customer_logReception(customerId, rPos);
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    /**
     * Get customer position in reception queue given his id
     * 
     * @param customerId customer id
     * @return customer's reception queue position
     */
    public int receptionPos(int customerId) {
        for (int i = 0; i < this.receptionists.length; i++) {
            if (this.receptionists[i] == customerId)
                return i;
        }
        return -1;
    }
    
    @Override
    public void checkIn(int customerId){
        try {
            System.out.printf("> Customer %d is checking-in\n", customerId);
            Thread.sleep(this.timeCheckIn);
        } catch (InterruptedException ex) {}
        
        try {
            this.rl.lock();

            // NOTE(L1fer): the check in process is done here so that the rooms dont get wrongly distributed
            // check if bedroom is full
            if (++this.bedRoomTotal > 3) {
                this.bedRoomTotal = 1;
                // check if floor is full
                if (++this.bedRoomNumber > 3) {
                    this.bedRoomNumber = 1;
                    this.floorNumber++;
                }
            }
            
            System.out.println("_______DEBUG BED OFFSET:" + this.curBedOffset);
            this.customersBedOffset[customerId - 1] = this.curBedOffset;
            
            this.mLogCustomer.customer_logFloor(customerId, curBedOffset);
            
            this.curBedOffset++;

            //this.checkInQueue[getCustomerQueuePos(customerId)] = 0;
            this.receptionists[receptionPos(customerId)] = 0;
            updateReceptionQueueGUI();
            
            this.cWaitCustomer.signalAll();
            this.remainingCheckIns--;
            
            if (this.remainingCheckIns == 0) {
                this.porterTotalCustomers = (this.floorNumber - 1) * 9 + (this.bedRoomNumber - 1) * 3 + this.bedRoomTotal;
                this.waiterTotalCustomers = (this.floorNumber - 1) * 9 + (this.bedRoomNumber - 1) * 3 + this.bedRoomTotal;
                this.cWaitCheckIn.signalAll();
            }

        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void waitArrival(int receptionistId) {
        try {
            this.rl.lock();
            System.out.printf("> Receptionist %d is waiting for customers arrival\n", receptionistId);
            
            //if (this.remainingCustomers == 0 && this.remainingCheckIns == 0)
            //    return false;
            
            // wait until the porter has not closed the front door and it is not the recepetionist turn; or until there are no checkins remaining; or until the current checkin has not been reseted
            while ((this.frontDoorOpen && this.nextReceptionist != receptionistId) || this.remainingCheckIns == 0 || (this.queueRead == this.queueWrite && this.queueWrite != -1)) {
                try {
                    this.cWaitArrival.await();
                } catch (InterruptedException ex){}
                //System.out.println("AWAKE? " + receptionistId + " | " + "RECEPTIONIST ID" + this.nextReceptionist);
            }
            
            this.nextReceptionist++;
            if (nextReceptionist > 3)
                nextReceptionist = 1;
            //System.out.println("DEBUG " + receptionistId + " | " + "RECEPTIONIST ID" + this.nextReceptionist);
            
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void attendCustomer(int receptionistId) {
        try {
            this.rl.lock();
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
            
            if (this.frontDoorOpen || this.remainingCheckIns < 1 || (this.queueRead == this.queueWrite && this.queueWrite != -1))
                return;
            
            int customerPos = this.queueRead + 1;
            System.out.printf("> Receptionist %d is attending customer %d\n", receptionistId, checkInQueue[customerPos]);
            this.mLogReceptionist.receptionist_logCall(receptionistId);
            
            this.receptionists[receptionistId - 1] = this.checkInQueue[customerPos];
            updateReceptionQueueGUI();
            this.checkInQueue[customerPos] = 0;
            updateCheckInQueueGUI();
            
            this.queueRead++;
            this.cWaitReception.signalAll();
            
            // wait until customer leaves the queue
            while (this.receptionists[receptionistId - 1] != 0) {
                try {
                    this.cWaitCustomer.await();
                } catch (InterruptedException ex){}
            }

            // signal next receptionist
            this.cWaitArrival.signalAll();
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public boolean waitCheckIn(int remainingCustomers, int checkInCustomers) {
        try {
            this.rl.lock();
            
            this.remainingCustomers = remainingCustomers;
            
            //System.out.println("_________DEBUG: PORTER HERE!" + this.queueWrite + " - " + (checkInCustomers - 1));
           
            // wait until all customers are positioned in the queue (otherwise sometimes only a part of the queue is logged)
            while (this.queueWrite != checkInCustomers - 1) {
                try {
                    this.cWaitQueuePositioning.await();
                } catch (InterruptedException ex){}
            }
            
            this.mLogPorter.porter_logQueue(this.checkInQueue);
            
            System.out.printf("> Porter is closing the door\n");
            // close front door
            this.frontDoorOpen = false;
            
            this.mLogPorter.porter_logDoorCheckIn(this.frontDoorOpen);
            
            // signal receptionists that the door was closed
            this.cWaitArrival.signalAll();
            
            // wait until all costumers have been attended
            while (this.remainingCheckIns != 0) {
                try {
                    this.cWaitCheckIn.await();
                } catch (InterruptedException ex){}
            }
            
            this.queueRead = -1;
            this.queueWrite = -1;
            
            // check-in procedure ended
            if (this.remainingCustomers == 0) {
                //porter.setTotalCustomers(this.totalCustomers);
                this.cWaitArrival.signalAll();
                
                return false;
            }
            
            System.out.printf("> Porter is re-opening the door\n");
            // reopen front door
            this.frontDoorOpen = true;
            this.mLogPorter.porter_logDoorCheckIn(this.frontDoorOpen);
        } finally {
            this.rl.unlock();
        }
        
        return true;
    }
    
    @Override
    public void waitCheckInCustomers() {
        try {
            this.rl.lock();
            System.out.print("> Waiter is awating customers\n");
            
            while (this.remainingCustomers != 0 || this.remainingCheckIns != 0 || this.bedRoomTotal == 0) {
                try {
                    this.cWaitCheckIn.await();
                } catch (InterruptedException ex){}
            }
            
            //waiter.setTotalCustomers(this.totalCustomers);
            System.out.printf("> Waiter is going to mealroom\n");
            
            // before leaving, reset floor, room and bed numbers
            this.floorNumber = 1;
            this.bedRoomNumber = 1;
            this.bedRoomTotal = 0;
            this.curBedOffset = 0;
            
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public int getWaiterTotalCustomers() {
        try {
            this.rl.lock();
            
            return this.waiterTotalCustomers;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void subtractWaiterTotalCustomers(int nCustomers) {
        try {
            this.rl.lock();
            
            this.waiterTotalCustomers -= nCustomers;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public int getPorterTotalCustomers() {
        try {
            this.rl.lock();
            
            return this.porterTotalCustomers;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void subtractPorterTotalCustomers(int nCustomers) {
        try {
            this.rl.lock();
            
            this.porterTotalCustomers -= nCustomers;
        } finally {
            this.rl.unlock();
        }
    }
}
