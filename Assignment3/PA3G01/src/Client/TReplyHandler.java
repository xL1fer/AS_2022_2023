/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import Utils.Consts;
import Utils.IMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Client thread class.
 */
public class TReplyHandler implements Runnable {
    private int clientPort;
    private final String hostname;
    private final int loadBalancerPort;
    private int executedRequests;
    
    private final javax.swing.JTextArea textArea;
    private final javax.swing.JLabel executedRequestsLabel;
    
    private TReplyHandler(int clientId, javax.swing.JTextArea textArea, javax.swing.JLabel executedRequestsLabel) {
        this.clientPort = clientId;
        this.hostname = "localhost";
        this.loadBalancerPort = 4000;
        this.executedRequests = 0;
        this.textArea = textArea;
        this.executedRequestsLabel = executedRequestsLabel;
    }
    
    public static Runnable getInstance(int clientId, javax.swing.JTextArea textArea, javax.swing.JLabel executedRequestsLabel) {
        return new TReplyHandler(clientId, textArea, executedRequestsLabel);
    }
    
    @Override
    public void run() {
        // reply handler logic
        try {
            ServerSocket replyServerSocket = new ServerSocket(this.clientPort);
            System.out.println("Listening for reply connections on port " + this.clientPort);
            this.textArea.append("Listening for reply connections on port " + this.clientPort + "\n");
            System.out.println("REPLY SERVER SOCKET=" + replyServerSocket);
            
            while(true) {
                Socket replySocket = replyServerSocket.accept(); // blocking
                System.out.println("REPLY HANDLE SOCKET (created at accept())=" + replySocket);
                new Thread(() -> {
                    handleReply(replySocket);
                }).start();
            }
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }
    
    public void handleReply(Socket replySocket) {
        try {
            ObjectOutputStream outReply = new ObjectOutputStream(replySocket.getOutputStream());
            outReply.flush();
            ObjectInputStream inReply = new ObjectInputStream(replySocket.getInputStream());
            
            // get load balancer reply
            IMessage message = (IMessage)inReply.readObject();
            System.out.println("RPLY > " + message);
            this.textArea.append("RPLY > " + message + "\n");
            
            if (message.getMessageCode() == Consts.REPLYPI) {
                this.executedRequests++;
                this.executedRequestsLabel.setText("Executed Requests: " + this.executedRequests);
            }

        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
    }
}
