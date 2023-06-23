/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Server.Pi.IPi_Server;
import Server.Pi.MPi;
import Utils.Consts;
import Utils.IMessage;
import Utils.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server thread class.
 */
public class TServer implements Runnable {
    private ServerSocket serviceServerSocket;
    private final String hostname;
    private int serverPort;
    private final int monitorPort;
    private final int loadBalancerPort;
    
    private final IPi_Server mPiServer;
    
    javax.swing.JTextArea receivedRequestsTextArea;
    javax.swing.JTextArea processedRequestsTextArea;
    
    private TServer(javax.swing.JTextArea receivedRequestsTextArea, javax.swing.JTextArea processedRequestsTextArea) {
        this.hostname = "localhost";
        this.serverPort = 0;
        this.monitorPort = 3000;
        this.mPiServer = (IPi_Server)MPi.getInstance();
        this.loadBalancerPort = 4000;
        
        this.receivedRequestsTextArea = receivedRequestsTextArea;
        this.processedRequestsTextArea = processedRequestsTextArea;
    }
    
    public static Runnable getInstance(javax.swing.JTextArea receivedRequestsTextArea, javax.swing.JTextArea processedRequestsTextArea) {
        return new TServer(receivedRequestsTextArea, processedRequestsTextArea);
    }
    
    @Override
    public void run() {
        System.out.println("Server started");
        
        // connect to the monitor and request server port
        establishMonitorConnection();
        
        // requests handler logic
        try {
            this.serviceServerSocket = new ServerSocket(this.serverPort);
            System.out.println("Listening for service connections on port " + this.serverPort);
            this.receivedRequestsTextArea.append("Listening for service connections on port " + this.serverPort + "\n");
            System.out.println("SERVICE SERVER SOCKET=" + this.serviceServerSocket);
            
            while(true) {
                Socket serviceSocket = serviceServerSocket.accept(); // blocking
                System.out.println("SERVICE HANDLE SOCKET (created at accept())=" + serviceSocket);
                new Thread(() -> {
                    IMessage message = handleServiceRequest(serviceSocket);
                    
                    sendServiceReply(message);
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
                Socket monitorSocket = new Socket(this.hostname, this.monitorPort);
                System.out.println("MONITOR CLIENT SOCKET=" + monitorSocket);

                ObjectOutputStream out = new ObjectOutputStream(monitorSocket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(monitorSocket.getInputStream());

                // send port request
                out.writeObject(Message.getInstance(0, -1, 0, Consts.REQUESTPORT, 0, 0, 0));
                out.flush();

                // get port response
                try {
                    this.serverPort = ((IMessage)in.readObject()).getServerId();
                } catch (ClassNotFoundException e) {
                    System.out.println("ClassNotFoundException " + e.getMessage());
                }

                // keep connection alive in a blocking state
                new Thread(() -> {
                    try {
                        in.read();
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
            }
            
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException " + e.getMessage());
            }
        }
    }
    
    public IMessage handleServiceRequest(Socket serviceSocket) {
        IMessage message = null;
        try {
            ObjectOutputStream out = new ObjectOutputStream(serviceSocket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(serviceSocket.getInputStream());

            // service handling
            message = (IMessage)in.readObject();
            System.out.println("RQST > " + message);
            this.receivedRequestsTextArea.append("RQST > " + message + "\n");
            
            mPiServer.waitTurn(message);
            
            // get pi number iterations
            String pi = "3.";
            for (int i = 0; i < message.getIterations(); i++) {
                pi += mPiServer.getPiIteration(i + 2);
                Thread.sleep(4000);
            }
            
            mPiServer.signalCompletion();
            
            message.setPi(Double.parseDouble(pi));
            message.setMessageCode(Consts.REPLYPI);
            return message;
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
        
        return message;
    }
    
    public void sendServiceReply(IMessage message) {
        // connect to client
        try {
            Socket serviceSocket = new Socket(this.hostname, this.loadBalancerPort);
            System.out.println("LOAD BALANCER SOCKET=" + serviceSocket);

            ObjectOutputStream out = new ObjectOutputStream(serviceSocket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(serviceSocket.getInputStream());

            // send service reply
            out.writeObject(message);
            out.flush();
            
            this.processedRequestsTextArea.append("RPLY > " + message + "\n");
            
        } catch(UnknownHostException e) {
            System.out.println("Could not connect to load balancer (UnknownHostException " + e.getMessage() + ")");
        } catch (IOException e) {
            System.out.println("Could not connect to load balancer (IOException " + e.getMessage() + ")");
        }
    }
}
