/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoadBalancer;

import Utils.Consts;
import Utils.IMessage;
import Utils.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Load Balancer thread class.
 */
public class TLoadBalancer implements Runnable {
    private ServerSocket clientServerSocket;
    private final String hostname;
    private final int monitorPort;
    private final int requestsPort;
    private final int heartbeatPort;
    private final int heartbeatDelay;
    private final javax.swing.JTextArea textArea;
    
    private TLoadBalancer(javax.swing.JTextArea textArea) {
        this.hostname = "localhost";
        this.monitorPort = 3000;
        this.requestsPort = 4000;
        this.heartbeatPort = 5000;
        this.heartbeatDelay = 1000;
        this.textArea = textArea;
    }
    
    public static Runnable getInstance(javax.swing.JTextArea textArea) {
        return new TLoadBalancer(textArea);
    }
    
    @Override
    public void run() {
        System.out.println("Load Balancer started");
        this.textArea.append("Load Balancer started\n");
        
        establishMonitorConnection();
        
        // verify if there the primary server up
        checkHeartBeat();
        
        // create a thread responsible for handling heartbeats
        new Thread(THeartBeatHandler.getInstance(this.heartbeatPort)).start();
        
        // clients handler logic
        try {
            this.clientServerSocket = new ServerSocket(this.requestsPort);
            System.out.println("Listening for client connections on port " + this.requestsPort);
            this.textArea.append("Listening for client connections on port " + this.requestsPort + "\n");
            System.out.println("LOAD BALANCER SERVER SOCKET=" + this.clientServerSocket);
            
            while(true) {
                Socket requestSocket = clientServerSocket.accept(); // blocking
                System.out.println("LOAD BALANCER HANDLE SOCKET (created at accept())=" + requestSocket);
                new Thread(() -> {
                    handleRequest(requestSocket);
                }).start();
            }
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }
    
    public void establishMonitorConnection() {
        while (true) {
            // connect to the monitor
            try {
                Socket monitorKeepAliveSocket = new Socket(this.hostname, this.monitorPort);
                System.out.println("MONITOR CLIENT SOCKET=" + monitorKeepAliveSocket);
                
                ObjectOutputStream outKeepAliveMonitor = new ObjectOutputStream(monitorKeepAliveSocket.getOutputStream());
                outKeepAliveMonitor.flush();
                ObjectInputStream inKeepAliveMonitor = new ObjectInputStream(monitorKeepAliveSocket.getInputStream());
                
                // send connection request
                outKeepAliveMonitor.writeObject(Message.getInstance(0, -1, 0, Consts.REQUESTCONNECT, 0, 0, 0));
                outKeepAliveMonitor.flush();
                
                // keep connection alive in a blocking state
                new Thread(() -> {
                    try {
                        inKeepAliveMonitor.read();
                    } catch (IOException e) {
                        System.out.println("Monitor server was shutdown (IOException " + e.getMessage() + ")");
                        System.exit(0);
                    }
                }).start();
                
                break;

            } catch(UnknownHostException e) {
                System.out.println("Could not connect to monitor (UnknownHostException " + e.getMessage() + ")");
            } catch (IOException e) {
                System.out.println("Could not connect to monitor (IOException " + e.getMessage() + ")");
                this.textArea.append("Could not connect to monitor\n");
            }
            
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException " + e.getMessage());
            }
        }
    }
    
    /**
     * Check for heartbeats from the primary server.
     */
    public void checkHeartBeat() {
        // try to establish a socket connection to the primary load balancer
        try {
            Socket heartbeatSocket = new Socket(this.hostname, this.heartbeatPort);
            System.out.println("HEARTBEAT CLIENT SOCKET=" + heartbeatSocket);

            DataOutputStream out = new DataOutputStream(heartbeatSocket.getOutputStream());
            out.flush();
            DataInputStream in = new DataInputStream(heartbeatSocket.getInputStream());
            
            // keep checking hearbeats while the primary server is up
            while (true) {
                // send heartbeat request
                out.write(0xAA);
                out.flush();
                // receive heartbeat response
                byte data = in.readByte();

                System.out.println("Received heart beat");
                this.textArea.append("Received heart beat\n");

                Thread.sleep(this.heartbeatDelay);
            }
        } catch(UnknownHostException e) {
            System.out.println("No primary load balancer avaialbe (UnknownHostException " + e.getMessage() + ")");
        } catch (IOException e) {
            System.out.println("No primary load balancer avaialbe (IOException " + e.getMessage() + ")");
        } catch (InterruptedException e) {
            System.out.println("No primary load balancer avaialbe (InterruptedException " + e.getMessage() + ")");
        }
    }
    
    public void handleRequest(Socket requestSocket) {
        try {
            ObjectOutputStream outRequest = new ObjectOutputStream(requestSocket.getOutputStream());
            outRequest.flush();
            ObjectInputStream inRequest = new ObjectInputStream(requestSocket.getInputStream());
            
            // get request
            IMessage message = (IMessage)inRequest.readObject();
            System.out.println("RQST > " + message);
            this.textArea.append("RQST > " + message + "\n");

            // message has no assigned server, communicate with monitor
            if (message.getServerId() == 0) {
                
                try {
                    Socket monitorSocket = new Socket(this.hostname, this.monitorPort);
                    ObjectOutputStream outMonitor = new ObjectOutputStream(monitorSocket.getOutputStream());
                    outMonitor.flush();
                    ObjectInputStream inMonitor = new ObjectInputStream(monitorSocket.getInputStream());
                    
                    // send request to monitor
                    outMonitor.writeObject(message);
                    outMonitor.flush();
                    
                    // get monitor response
                    message = (IMessage)inMonitor.readObject();
                    
                    if (message.getMessageCode() == Consts.REPLYCLIENT) {
                        outRequest.writeObject(message);
                        outRequest.flush();
                    }
                    else if (message.getMessageCode() == Consts.REQUESTPI) {
                        // send service request to available server
                        try {
                            Socket socket = new Socket(this.hostname, message.getServerId());
                            System.out.println("SERVICE LOAD BALANCER SOCKET=" + socket);

                            ObjectOutputStream outServer = new ObjectOutputStream(socket.getOutputStream());
                            outServer.flush();
                            ObjectInputStream inServer = new ObjectInputStream(socket.getInputStream());

                            // send pi request
                            outServer.writeObject(message);
                            outServer.flush();
                            // receive server reply
                            //message = (IMessage)inServer.readObject();
                        } catch (IOException e) {
                            System.out.println("No servers available (IOException " + e.getMessage() + ")");
                            this.textArea.append("No servers available\n");
                            message.setMessageCode(Consts.REPLYREJECT);
                            sendClientReply(message);
                        }
                    }
                    
                } catch(UnknownHostException e) {
                    System.out.println("Could not connect to service (UnknownHostException " + e.getMessage() + ")");
                } catch (IOException e) {
                    System.out.println("Could not connect to service (IOException " + e.getMessage() + ")");
                }

            }
            // message processed by a server
            else {
                // send reply to client
                sendClientReply(message);
                
                // signal monitor that the service request was completed
                try {
                    Socket monitorSocket = new Socket(this.hostname, this.monitorPort);
                    ObjectOutputStream outMonitor = new ObjectOutputStream(monitorSocket.getOutputStream());
                    outMonitor.flush();
                    ObjectInputStream inMonitor = new ObjectInputStream(monitorSocket.getInputStream());
                    
                    outMonitor.writeObject(message);
                    outMonitor.flush();

                } catch(UnknownHostException e) {
                    System.out.println("Could not connect to service (UnknownHostException " + e.getMessage() + ")");
                } catch (IOException e) {
                    System.out.println("Could not connect to service (IOException " + e.getMessage() + ")");
                }
            }
                
            //}

        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
    }
    
    public void sendClientReply(IMessage message) {
        // connect to client
        try {
            Socket serviceSocket = new Socket(this.hostname, message.getClientId());
            System.out.println("CLIENT SOCKET=" + serviceSocket);

            ObjectOutputStream out = new ObjectOutputStream(serviceSocket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(serviceSocket.getInputStream());
            this.textArea.append("RPLY > " + message.toString() + "\n");

            // send service reply
            out.writeObject(message);
            out.flush();

        } catch(UnknownHostException e) {
            System.out.println("Could not connect to client (UnknownHostException " + e.getMessage() + ")");
        } catch (IOException e) {
            System.out.println("Could not connect to client (IOException " + e.getMessage() + ")");
        }
    }
}