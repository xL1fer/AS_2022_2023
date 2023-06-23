/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.Pi;

import Utils.IMessage;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Pi service monitor.
 */
public class MPi implements IPi {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Wait iteration synchronization condition.
     */
    private final Condition cWaitTurn;
    /**
     * Pi service provider variable.
     */
    private final String pi;
    
    private final int computationTime;
    
    private final int maxActiveRequests;
    
    private final int queueSize;
    
    private int activeRequests;
    
    private final ArrayList<IMessage> pendingRequests;
    
    private final ArrayList<Long> timeStamps;
    
    /**
     * Pi service class constructor.
     * 
     */
    private MPi() {
        this.rl = new ReentrantLock();
        this.cWaitTurn = rl.newCondition();
        this.pi = "3.1415926535897932384626433";
        this.computationTime = 4;
        this.maxActiveRequests = 3;
        this.queueSize = 2;
        this.activeRequests = 0;
        this.pendingRequests = new ArrayList<>();
        this.timeStamps = new ArrayList<>(); 
    }

    /**
     * Create instance of pi service monitor.
     * 
     * @return pi service monitor instance
     */
    public static IPi getInstance() {
        return new MPi();
    }
    
    public boolean requestEntry(IMessage message) {
        if (this.activeRequests >= this.maxActiveRequests)
            return false;
        else if (this.pendingRequests.size() < this.queueSize)
            return true;
        
        int minIndex = 0;
        long minTime = Long.MAX_VALUE;
        for (int i = 0; i < this.timeStamps.size(); i++) {
            long curTime = (this.pendingRequests.get(i).getDeadline()) - (this.computationTime * this.pendingRequests.get(i).getIterations()) - (System.currentTimeMillis() - this.timeStamps.get(i)) / 1000;
            if (curTime < minTime) {
                minIndex = i;
                minTime = curTime;
            }
        }
        
        return message == this.pendingRequests.get(minIndex);
    }
    
    @Override
    public void waitTurn(IMessage message) {
        try {
            this.rl.lock();
            
            this.pendingRequests.add(message);
            this.timeStamps.add(System.currentTimeMillis());
            
            while (!requestEntry(message)) {
                try {
                    this.cWaitTurn.await();
                } catch (InterruptedException ex){}
            }
            
            this.activeRequests++;
            
            int ind = pendingRequests.indexOf(message);
            
            pendingRequests.remove(ind);
            timeStamps.remove(ind);
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public char getPiIteration(int i) {
        try {
            this.rl.lock();
            
            return pi.charAt(i);
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void signalCompletion() {
        try {
            this.rl.lock();
            
            this.activeRequests--;
            
            this.cWaitTurn.signalAll();
            
        } finally {
            this.rl.unlock();
        }
    }
}
