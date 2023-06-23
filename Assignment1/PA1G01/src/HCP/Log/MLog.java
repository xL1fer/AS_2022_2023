/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Log monitor.
 */
public class MLog implements ILog {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Log file writer object.
     */
    private FileWriter logFile;
    
    /**
     * Log class constructor.
     */
    private MLog() {
        rl = new ReentrantLock();
        
        try {
            this.logFile = new FileWriter("simulationlog.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }    
    }
    
    /**
     * Create instance of log monitor.
     * 
     * @return log monitor instance
     */
    public static ILog getInstance() {
        return new MLog();
    }

    @Override
    public void customer_logReception(int customerId, int rPos) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |                      ");
                for (int i = 0; i < 3; i++) {
                    if (i == rPos) {
                        if (customerId < 10)
                            this.logFile.write(String.format("0%d ", customerId));
                        else
                            this.logFile.write(String.format("%d ", customerId));
                    }
                    else
                        this.logFile.write("   ");
                }
                this.logFile.write("|                            |                            |                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void customer_logFloor(int customerId, int bedOffset) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |                               ");
                for (int i = 0; i < 27; i++) {
                    if (i % 9 == 0)
                        this.logFile.write("| ");

                    if (i == bedOffset) {
                        if (customerId < 10)
                            this.logFile.write(String.format("0%d ", customerId));
                        else
                            this.logFile.write(String.format("%d ", customerId));
                    }
                    else {
                        this.logFile.write(String.format("   ", customerId));
                    }
                }
                this.logFile.write("|\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void customer_logBathroom(int customerId, int bedRoomNumber) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    | ");
                for (int i = 0; i < 3; i++) {
                    if (i == bedRoomNumber - 1) {
                        if (customerId < 10)
                            this.logFile.write(String.format("0%d ", customerId));
                        else
                            this.logFile.write(String.format("%d ", customerId));
                    }
                    else {
                        this.logFile.write("   ");
                    }
                }
                this.logFile.write("|                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void customer_logMealroom(int customerId, int seatNumber) {        
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |          | ");
                for (int i = 0; i < 9; i++) {
                    if (i == seatNumber) {
                        if (customerId < 10)
                            this.logFile.write(String.format("0%d ", customerId));
                        else
                            this.logFile.write(String.format("%d ", customerId));
                    }
                    else {
                        this.logFile.write("   ");
                    }
                }
                this.logFile.write("|                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void customer_logBreakfast(int customerId, int seatNumber) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |          |                            | ");
                for (int i = 0; i < 9; i++) {
                    if (i == seatNumber) {
                        if (customerId < 10)
                            this.logFile.write(String.format("0%d ", customerId));
                        else
                            this.logFile.write(String.format("%d ", customerId));
                    }
                    else {
                        this.logFile.write("   ");
                    }
                }
                this.logFile.write("|                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void customer_logLeaving(int customerId, int leavingPos) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |          |                            |                            | ");
                for (int i = 0; i < 9; i++) {
                    if (i == leavingPos) {
                        if (customerId < 10)
                            this.logFile.write(String.format("0%d ", customerId));
                        else
                            this.logFile.write(String.format("%d ", customerId));
                    }
                    else {
                        this.logFile.write("   ");
                    }
                }
                this.logFile.write("|                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void customer_logOutside(int customerId, int outsidePos) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |          |                            |                            |                            |    ");
                for (int i = 0; i < 9; i++) {
                    if (i == outsidePos) {
                        if (customerId < 10)
                            this.logFile.write(String.format("0%d ", customerId));
                        else
                            this.logFile.write(String.format("%d ", customerId));
                    }
                    else {
                        this.logFile.write("   ");
                    }
                }
                this.logFile.write("|\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void porter_logQueue(int queue[]) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |    ");
                for (int i = 0; i < queue.length; i++) {
                    if (queue[i] == 0)
                        this.logFile.write("   ");
                    else if (queue[i] < 10)
                        this.logFile.write(String.format("0%d ", queue[i]));
                    else
                        this.logFile.write(String.format("%d ", queue[i]));
                }
                this.logFile.write("         |                            |                            |                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void porter_logDoorCheckIn(boolean frontDoorOpen) {
        try {
            this.rl.lock();
            try {
                if (frontDoorOpen)
                    this.logFile.write("|    | OP                            |                            |                            |                            |\n");
                else
                    this.logFile.write("|    | CL                            |                            |                            |                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void porter_logDoorCheckOut(boolean frontDoorOpen) {
        try {
            this.rl.lock();
            try {
                if (frontDoorOpen)
                    this.logFile.write("|    |          |                            |                            |                            | OP                            |\n");
                else
                    this.logFile.write("|    |          |                            |                            |                            | CL                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void porter_logIdle() {
        try {
            this.rl.lock();
            try {
                this.logFile.write("| ID |          |                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void porter_logOutside(int queue[], int customers) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |          |                            |                            |                            |    ");
                for (int i = 0; i < 9; i++) {
                    if (i < customers) {
                        if (queue[i] < 10)
                            this.logFile.write(String.format("0%d ", queue[i]));
                        else
                            this.logFile.write(String.format("%d ", queue[i]));
                    }
                    else {
                        this.logFile.write("   ");
                    }
                }
                this.logFile.write("|\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void receptionist_logCall(int receptionistId) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |                      ");
                for (int i = 0; i < 3; i++) {
                    if (i == receptionistId - 1)
                        this.logFile.write("RC ");
                    else
                        this.logFile.write("   ");
                }
                this.logFile.write("|                            |                            |                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void waiter_logServing(int seatNumber) {
        try {
            this.rl.lock();
            try {
                this.logFile.write("|    |          |                            | ");
                for (int i = 0; i < 9; i++) {
                    if (i == seatNumber) {
                        this.logFile.write("WT ");
                    }
                    else {
                        this.logFile.write("   ");
                    }
                }
                this.logFile.write("|                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logIdle(int totalCustomers) {
        try {
            this.rl.lock();
            try {
                this.logFile.write(String.format("\n(check-in) - Number of Clients: %d\n", totalCustomers));
                this.logFile.write("                   (MCI)                   FLOOR 1 (MBR - bed)          FLOOR 2 (MBR - bed)          FLOOR 3 (MBR - bed)\n");
                this.logFile.write("| ST | DR P1 P2 P3 P4 P5 P6 R1 R2 R3 | 11 12 13 21 22 23 31 32 33 | 11 12 13 21 22 23 31 32 33 | 11 12 13 21 22 23 31 32 33 |\n");
                this.logFile.write("| ID |                               |                            |                            |                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logRun() {
        try {
            this.rl.lock();
            try {
                this.logFile.write("| RN |                               |                            |                            |                            |\n");
                this.logFile.write("|    | CL                            |                            |                            |                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logSuspend(boolean checkin) {
        try {
            this.rl.lock();
            try {
                if (checkin)
                    this.logFile.write("| SP |                               |                            |                            |                            |\n");
                else
                    this.logFile.write("| SP |          |                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logResume(boolean checkin) {
        try {
            this.rl.lock();
            try {
                if (checkin)
                    this.logFile.write("| RS |                               |                            |                            |                            |\n");
                else
                    this.logFile.write("| RS |          |                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logManual(boolean checkin) {
        try {
            this.rl.lock();
            try {
                if (checkin)
                    this.logFile.write("| MN |                               |                            |                            |                            |\n");
                else
                    this.logFile.write("| MN |          |                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logStep(boolean checkin) {
        try {
            this.rl.lock();
            try {
                if (checkin)
                    this.logFile.write("| ST |                               |                            |                            |                            |\n");
                else
                    this.logFile.write("| ST |          |                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logAuto(boolean checkin) {
        try {
            this.rl.lock();
            try {
                if (checkin)
                    this.logFile.write("| AU |                               |                            |                            |                            |\n");
                else
                    this.logFile.write("| AU |          |                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logCheckIn() {
        try {
            this.rl.lock();
            try {
                this.logFile.write("| CI |                               |                            |                            |                            |\n");
                this.logFile.write("|    | OP                            |                            |                            |                            |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void ccp_logCheckOut(int floorNumber) {
        try {
            this.rl.lock();
            try {
                this.logFile.write(String.format("\n(check-out) - Floor number: %d\n", floorNumber));
                this.logFile.write("     (MBR- bathr)       (MMR - seated)               (MMR - eating)               (MLH - leaving)                 (outside)\n");
                this.logFile.write("| ST | B1 B2 B3 | S1 S2 S3 S4 S5 S6 S7 S8 S9 | E1 E2 E3 E4 E5 E6 E7 E8 E9 | L1 L2 L3 L4 L5 L6 L7 L8 L9 | DR O1 O2 O3 O4 O5 O6 O7 O8 O9 |\n");
                this.logFile.write("| CO |          |                            |                            |                            |                               |\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void ccp_logOpen() {
        try {
            this.rl.lock();
            try {
                this.logFile.write("Hostel Simulation Logger\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void ccp_logClose() {
        try {
            this.rl.lock();
            try {
                this.logFile.write("\nLogger Ended\n");
                this.logFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.rl.unlock();
        }
    }
}
