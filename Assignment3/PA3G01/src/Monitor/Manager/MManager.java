/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Monitor.Manager;

import Utils.Consts;
import Utils.IMessage;
import Utils.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Manager monitor.
 */
public class MManager implements IManager {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Wait manage synchronization condition.
     */
    private final Condition cWaitManage;
    
    private boolean isBusy;
    
    private int serverPortAssign;
    
    private int clientPortAssign;
    
    private int requestIdAssign;
    
    private final int maxServerRequests;
    private final int maxServerIterations;
    
    private final ArrayList<Integer> availableServers;
    private final ArrayList<Integer> totalRequests;
    private final ArrayList<Integer> totalIterations;
    private final ArrayList<ArrayList<IMessage>> serversRequests;
    
    private final javax.swing.JTextArea textArea;
    
    /**
     * Manager class constructor.
     * 
     */
    private MManager(javax.swing.JTextArea textArea) {
        this.rl = new ReentrantLock();
        this.cWaitManage = rl.newCondition();
        this.isBusy = false;
        this.serverPortAssign = 6000;
        this.clientPortAssign = 2000;
        this.requestIdAssign = 0;
        this.maxServerRequests = 5;
        this.maxServerIterations = 25;
        this.availableServers = new ArrayList<>();
        this.totalRequests = new ArrayList<>();
        this.totalIterations = new ArrayList<>();
        this.serversRequests = new ArrayList<>();
        this.textArea = textArea;
    }

    /**
     * Create instance of manager monitor.
     * 
     * @param textArea console display text area
     * @return manager monitor instance
     */
    public static IManager getInstance(javax.swing.JTextArea textArea) {
        return new MManager(textArea);
    }
    
    @Override
    public IMessage getAvailablePort(IMessage message) {
        try {
            this.rl.lock();
            
            this.availableServers.add(this.serverPortAssign);
            this.totalRequests.add(0);
            this.totalIterations.add(0);
            this.serversRequests.add(new ArrayList<>());
            
            System.out.println("Assigning server port " + this.serverPortAssign);
            this.textArea.append("Assigning server port " + this.serverPortAssign + "\n");
            
            message.setServerId(this.serverPortAssign++);
            message.setMessageCode(Consts.REPLYPORT);
            
            return message;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public IMessage getAvailableServer(IMessage message) {
        try {
            this.rl.lock();
            
            int serverIndex = -1;
            
            int requestId = 1000 * message.getClientId() + this.requestIdAssign;
            
            IMessage saveMessage;
            if (message.getRequestId() == -1)
                saveMessage = Message.getInstance(message.getClientId(), requestId, 0, message.getMessageCode(), message.getIterations(), 0.0, message.getDeadline());
            else
                saveMessage = Message.getInstance(message.getClientId(), message.getRequestId(), 0, message.getMessageCode(), message.getIterations(), 0.0, message.getDeadline());
            
            for (int i = 0; i < this.availableServers.size(); i++) {
                if (serverIndex == -1 && this.totalRequests.get(i) < this.maxServerRequests && (this.totalIterations.get(i) + message.getIterations()) <= this.maxServerIterations)
                    serverIndex = i;
                else if (serverIndex != -1 && this.totalRequests.get(serverIndex) > this.totalRequests.get(i) && (this.totalIterations.get(i) + message.getIterations()) <= this.maxServerIterations)
                    serverIndex = i;
            }
            
            // got available server
            if (serverIndex != -1) {
                this.totalRequests.set(serverIndex, this.totalRequests.get(serverIndex) + 1);
                this.totalIterations.set(serverIndex, this.totalIterations.get(serverIndex) + message.getIterations());
                this.serversRequests.get(serverIndex).add(saveMessage);
                System.out.println("Got available server " + this.availableServers.get(serverIndex) + " (reqs = " + this.totalRequests.get(serverIndex) + ", iters = " + this.totalIterations.get(serverIndex) + ")");
                this.textArea.append("Got available server " + this.availableServers.get(serverIndex) + " (reqs = " + this.totalRequests.get(serverIndex) + ", iters = " + this.totalIterations.get(serverIndex) + ")\n");
                
                message.setServerId(this.availableServers.get(serverIndex));
            }
            // no server currently available
            else {
                System.out.println("No available servers");
                this.textArea.append("No available servers\n");
            }
            
            if (message.getRequestId() == -1) {
                message.setRequestId(requestId);
                this.requestIdAssign++;
            }
            
            return message;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public IMessage getClientId(IMessage message) {
        try {
            this.rl.lock();
            
            System.out.println("Assigning client port " + this.clientPortAssign);
            this.textArea.append("Assigning client port " + this.clientPortAssign + "\n");
            
            message.setClientId(this.clientPortAssign++);
            message.setMessageCode(Consts.REPLYCLIENT);
            
            
            return message;
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void removeRequest(IMessage message) {
        try {
            this.rl.lock();
            
            int serverIndex = this.availableServers.indexOf((Integer)message.getServerId());
            
            this.totalIterations.set(serverIndex, this.totalIterations.get(serverIndex) - message.getIterations());
            this.totalRequests.set(serverIndex, this.totalRequests.get(serverIndex) - 1);
            
            for (IMessage request : this.serversRequests.get(serverIndex)) {
                if (request.getRequestId() == message.getRequestId()) {
                    System.out.println("Removing request " + request.getRequestId() + " (reqs = " + this.totalRequests.get(serverIndex) + ", iters = " + this.totalIterations.get(serverIndex) + ")");
                    this.textArea.append("Removing request " + request.getRequestId() + " (reqs = " + this.totalRequests.get(serverIndex) + ", iters = " + this.totalIterations.get(serverIndex) + ")\n");
                    this.serversRequests.get(serverIndex).remove(request);
                    
                    return;
                }
            }
            
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void removeServer(int serverPort, String hostname, int loadBalancerPort) {
        try {
            this.rl.lock();
            
            if (this.availableServers.contains(serverPort)) {
                
                // TODO: when the server goes down, send all its pending requests to the primary load balancer
                for (IMessage message : this.serversRequests.get(this.availableServers.indexOf((Integer)serverPort))) {
                    // connect to the load balancer
                    try {
                        Socket serviceSocket = new Socket(hostname, loadBalancerPort);
                        System.out.println("SERVICE MANAGER SOCKET=" + serviceSocket);

                        ObjectOutputStream out = new ObjectOutputStream(serviceSocket.getOutputStream());
                        out.flush();
                        ObjectInputStream in = new ObjectInputStream(serviceSocket.getInputStream());

                        // re-send service request
                        out.writeObject(message);
                        out.flush();
                        System.out.println("Re-sent pi request > " + message);
                        this.textArea.append("Re-sent pi request > " + message + "\n");

                    } catch(UnknownHostException e) {
                        System.out.println("Could not connect to service (UnknownHostException " + e.getMessage() + ")");
                    } catch (IOException e) {
                        System.out.println("Could not connect to service (IOException " + e.getMessage() + ")");
                    }
                }
                
                this.totalRequests.remove(this.availableServers.indexOf((Integer)serverPort));
                this.totalIterations.remove(this.availableServers.indexOf((Integer)serverPort));
                this.availableServers.remove((Integer)serverPort);
                System.out.println("Server " + serverPort + " was shutdown, removing from avialable servers");
                this.textArea.append("Server " + serverPort + " was shutdown, removing from avialable servers\n");
            }
        } finally {
            this.rl.unlock();
        }
    }
}
