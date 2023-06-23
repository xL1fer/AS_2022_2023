/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.Outside;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import HCP.Log.ILog_ControlCentreProxy;

/**
 *
 * Outside monitor.
 */
public class MOutside implements IOutside {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Walk around synchronization condition.
     */
    private final Condition cWalkAround;
    /**
     * Wait turn synchronization condition.
     */
    private final Condition cWaitTurn;
    /**
     * Wait entrance synchronization condition.
     */
    private final Condition cWaitEntrance;
    /**
     * Wait check-in button synchronization condition.
     */
    private final Condition cWaitCheckInButton;
    /**
     * Wait resume button synchronization condition.
     */
    private final Condition cWaitResumeButton;
    /**
     * CCp message log monitor.
     */
    private final ILog_ControlCentreProxy mLogCCP;
    /**
     * Number of customers to enter the hostel.
     */
    private int nCustomers;
    /**
     * Total customers paricipating in the simulation.
     */
    private int totalCustomers;
    /**
     * Remaining customers to enter the hostel.
     */
    private int remainingCustomers;
    /**
     * Simulation started flag.
     */
    private boolean simulationStarted;
    /**
     * Check-in started flag.
     */
    private boolean checkInStarted;
    /**
     * Check-out transition flag.
     */
    private boolean checkOutTransition;
    /**
     * Simulation suspended status.
     */
    private boolean isSuspended;
    /**
     * Simulation mode status.
     */
    private boolean isAuto;
    
    /**
     * Outside class constructor.
     * 
     * @param mLogCCP CCP message log monitor
     */
    private MOutside(ILog_ControlCentreProxy mLogCCP) {
        this.rl = new ReentrantLock();
        this.cWalkAround = this.rl.newCondition();
        this.cWaitTurn =  this.rl.newCondition();
        this.cWaitEntrance =  this.rl.newCondition();
        this.cWaitCheckInButton = this.rl.newCondition();
        this.cWaitResumeButton = this.rl.newCondition();
        this.mLogCCP = mLogCCP;
        this.nCustomers = 0;
        this.totalCustomers = 0;
        this.remainingCustomers = 0;
        this.simulationStarted = false;
        this.checkInStarted = true;
        this.checkOutTransition = false;
        this.isSuspended = false;
        this.isAuto = true;
    }
    
    /**
     * Create instance of outside monitor.
     * 
     * @param mLogCCP CCP message log monitor
     * @return outside monitor instance
     */
    public static IOutside getInstance(ILog_ControlCentreProxy mLogCCP) {
        return new MOutside(mLogCCP);
    }
    
    @Override
    public void nextSimulation(int totalCustomers) {
        try {
            this.rl.lock();
            if (!this.simulationStarted) {
                this.mLogCCP.ccp_logIdle(totalCustomers);
                this.mLogCCP.ccp_logRun();
                System.out.println("> Signaling next simulation");
                this.totalCustomers = totalCustomers;
                this.remainingCustomers = totalCustomers;
                this.simulationStarted = true;
                
                this.cWalkAround.signalAll();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void startCheckIn() {
        try {
            this.rl.lock();
            if (!this.checkInStarted) {
                this.mLogCCP.ccp_logCheckIn();
                System.out.println("> Signaling checkin");
                this.checkInStarted = true;
                this.cWaitCheckInButton.signalAll();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void suspendSimulation() {
        if (!this.isSuspended && this.simulationStarted && !this.checkOutTransition) {
            this.mLogCCP.ccp_logSuspend(true);
        }
        this.isSuspended = true;
    }
    
    @Override
    public void resumeSimulation(boolean resume) {
        try {
            this.rl.lock();
            
            // log step simulation if simulation is in manual and the command is step (resume = false)
            if (!this.isAuto && !resume && this.simulationStarted && !this.checkOutTransition) {
                this.mLogCCP.ccp_logStep(true);
                this.cWaitResumeButton.signalAll();
            }
            
            if (resume) {
                // log resume simulation if simulation was suspended
                if (this.isSuspended && this.simulationStarted && !this.checkOutTransition) {
                    this.mLogCCP.ccp_logResume(true);
                }
                
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
        if (this.isAuto && this.simulationStarted && !this.checkOutTransition) {
            this.mLogCCP.ccp_logManual(true);
        }
        this.isAuto = false;
    }
    
    @Override
    public void autoSimulation() {
        try {
            this.rl.lock();
            
            if (!this.isAuto && this.simulationStarted && !this.checkOutTransition) {
                mLogCCP.ccp_logAuto(true);
            }
            
            this.isAuto = true;
            this.cWaitResumeButton.signalAll();
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void walkAround(int customerId){
        try {
            this.rl.lock();
            System.out.printf("> Customer %d is walking around\n", customerId);
            while (this.totalCustomers == 0) {
                try {
                    this.cWalkAround.await();
                } catch (InterruptedException ex){}
            }
            this.totalCustomers--;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void waitTurn(int customerId) {
        try {
            this.rl.lock();
            System.out.printf("> Customer %d is waiting turn\n", customerId);
            while (this.nCustomers == 0) {
                try {
                    this.cWaitTurn.await();
                } catch (InterruptedException ex){}
            }
            this.nCustomers--;
            this.remainingCustomers--;
            
            // signal the porter in case it is the last customer
            if (this.nCustomers == 0 || this.remainingCustomers == 0)
                this.cWaitEntrance.signalAll();
            
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void resetSimulation() {
        try {
            this.rl.lock();
            System.out.println("> Porter is preparing next simulation");
            
            // allow a new simulation
            this.simulationStarted = false;
            this.checkOutTransition = false;
            
            while (!this.simulationStarted) {
                try {
                    this.cWalkAround.await();
                } catch (InterruptedException ex){}
            }
            
            // reset check-in when a new simulation was signaled
            this.checkInStarted = false;
            
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void comeIn(int nCustomers) {
        try {
            this.rl.lock();
            
            while (!this.checkInStarted) {
                try {
                    this.cWaitCheckInButton.await();
                } catch (InterruptedException ex){}
            }
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
            
            this.nCustomers = nCustomers;
            this.cWaitTurn.signalAll();
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void waitEntrance() {
        try {
            this.rl.lock();
            while (this.nCustomers != 0 && this.remainingCustomers != 0) {
                try {
                    // wait for last customer to enter the hostel
                    this.cWaitEntrance.await();
                } catch (InterruptedException ex){}
            }
            
            //porter.setRemainingCustomers(this.remainingCustomers);
            //porter.setcheckInCustomers(6 - this.nCustomers);
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public int getRemainingCustomers() {
        try {
            this.rl.lock();
            
            return this.remainingCustomers;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public int getCheckInCustomers() {
        try {
            this.rl.lock();
            
            int tCustomers = 6 - this.nCustomers;
            
            // all customers entered
            if (this.remainingCustomers == 0) {
                this.nCustomers = 0;                // reset nCustomers for the next simulation
                this.checkOutTransition = true;     // mark checkout as the next simulation step
            }
            
            return tCustomers;
        } finally {
            this.rl.unlock();
        }
    }
    
}
