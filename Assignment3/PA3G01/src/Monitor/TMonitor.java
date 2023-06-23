/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Monitor;

import Monitor.Manager.IManager_Monitor;
import Monitor.Manager.MManager;
import Utils.Consts;
import Utils.IMessage;
import Utils.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Monitor thread class.
 */
public class TMonitor implements Runnable {
    private ServerSocket monitorServerSocket;
    private final String hostname;
    private final int monitorPort;
    private final int loadBalancerPort;
    private final IManager_Monitor mManagerMonitor;
    private int activeLoadBalancers;
    private int activeServers;
    private final javax.swing.JTextArea textArea;
    private final javax.swing.JLabel activeLoadBalancersLabel;
    private final javax.swing.JLabel activeServersLabel;
    
    private TMonitor(javax.swing.JTextArea textArea, javax.swing.JLabel activeLoadBalancersLabel, javax.swing.JLabel activeServersLabel) {
        this.hostname = "localhost";
        this.monitorPort = 3000;
        this.loadBalancerPort = 4000;
        this.mManagerMonitor = (IManager_Monitor)MManager.getInstance(textArea);
        this.activeLoadBalancers = 0;
        this.activeServers = 0;
        
        this.textArea = textArea;
        this.activeLoadBalancersLabel = activeLoadBalancersLabel;
        this.activeServersLabel = activeServersLabel;
    }
    
    public static Runnable getInstance(javax.swing.JTextArea textArea, javax.swing.JLabel activeLoadBalancersLabel, javax.swing.JLabel activeServersLabel) {
        return new TMonitor(textArea, activeLoadBalancersLabel, activeServersLabel);
    }
    
    @Override
    public void run() {
        System.out.println("Monitor started");
        this.textArea.append("Monitor started\n");
        
        // requests handler logic
        try {
            this.monitorServerSocket = new ServerSocket(this.monitorPort);
            System.out.println("Listening for monitor connections on port " + this.monitorPort);
            this.textArea.append("Listening for monitor connections on port " + this.monitorPort + "\n");
            System.out.println("MONITOR SERVER SOCKET=" + this.monitorServerSocket);
            
            while(true) {
                Socket monitorHandlerSocket = monitorServerSocket.accept(); // blocking
                System.out.println("MONITOR HANDLE SOCKET (created at accept())=" + monitorHandlerSocket);
                new Thread(() -> {
                    handleRequest(monitorHandlerSocket);
                }).start();
            }
        } catch (IOException e) {
            System.out.println("Could not initialize monitor (IOException " + e.getMessage() + ")");
            System.exit(0);
        }
    }
    
    public void handleRequest(Socket monitorHandlerSocket) {
        int assignedServerPort = 0;
        IMessage message = (IMessage)Message.getInstance(0, -1, 0, 0, 0, 0, 0);
        try {
            // NOTE(L1fer): ObjectOuputStream needs to be created before ObjectInputStream
            ObjectOutputStream out = new ObjectOutputStream(monitorHandlerSocket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(monitorHandlerSocket.getInputStream());

            // request handling
            try {
                message = (IMessage)in.readObject();

                // server port request
                if (message.getMessageCode() == Consts.REQUESTPORT) {
                    this.activeServersLabel.setText("Active Servers: " + (++this.activeServers));
                    
                    message = this.mManagerMonitor.getAvailablePort(message);
                    assignedServerPort = message.getServerId();
                    out.writeObject(message);
                    out.flush();
                    
                    // keep connection with server alive in a blocking state
                    in.readObject();
                }
                // load balancer connect request
                else if (message.getMessageCode() == Consts.REQUESTCONNECT) {
                    this.activeLoadBalancersLabel.setText("Active Load Balancers: " + (++this.activeLoadBalancers));
                    
                    message.setMessageCode(Consts.REPLYCONNECT);
                    
                    // keep connection with load balancer alive in a blocking state
                    in.readObject();
                }
                // client id request
                else if (message.getMessageCode() == Consts.REQUESTCLIENT) {
                    this.mManagerMonitor.getClientId(message);
                    out.writeObject(message);
                    out.flush();
                }
                // available server request
                else if (message.getMessageCode() == Consts.REQUESTPI) {
                    this.mManagerMonitor.getAvailableServer(message);
                    out.writeObject(message);
                    out.flush();
                }
                else if (message.getMessageCode() == Consts.REPLYPI) {
                    this.mManagerMonitor.removeRequest(message);
                }

            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
            
            if (message.getMessageCode() == Consts.REPLYPORT) {
                this.activeServersLabel.setText("Active Servers: " + (--this.activeServers));
            }
            else if (message.getMessageCode() == Consts.REPLYCONNECT) {
                this.activeLoadBalancersLabel.setText("Active Load Balancers: " + (--this.activeLoadBalancers));
            }
            
            mManagerMonitor.removeServer(assignedServerPort, this.hostname, this.loadBalancerPort);
        }
    }
}
