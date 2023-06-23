/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import Utils.Consts;
import Utils.IMessage;
import Utils.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client class.
 */
public class Client {
    private int clientPort;
    private final String hostname;
    private final int loadBalancerPort;
    
    private final javax.swing.JTextArea textArea;
    
    public Client(javax.swing.JTextArea textArea) {
        this.clientPort = 0;
        this.hostname = "localhost";
        this.loadBalancerPort = 4000;
        this.textArea = textArea;
        
        System.out.println("Client Started");
    }
    
    public void requestClientId() {
        while (true) {
            // connect to the load balancer
            try {
                Socket serviceSocket = new Socket(this.hostname, this.loadBalancerPort);
                System.out.println("SERVICE CLIENT SOCKET=" + serviceSocket);

                ObjectOutputStream out = new ObjectOutputStream(serviceSocket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(serviceSocket.getInputStream());

                // send service request
                IMessage message = (IMessage)Message.getInstance(this.clientPort, -1, 0, Consts.REQUESTCLIENT, 0, 0, 0);
                out.writeObject(message);
                out.flush();

                // receive service response
                message = (IMessage)in.readObject();
                this.clientPort = message.getClientId();
                System.out.println("Received client id " + this.clientPort);
                this.textArea.append("Received client id " + this.clientPort + "\n");

                break;

            } catch(UnknownHostException e) {
                System.out.println("Could not connect to service (UnknownHostException " + e.getMessage() + ")");
            } catch (IOException e) {
                System.out.println("Could not connect to service (IOException " + e.getMessage() + ")");
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException " + e.getMessage());
            }
            
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException " + e.getMessage());
            }
        }
    }
    
    public void requestPiService(int iterations, int deadline) {
        while (true) {
            // connect to the load balancer
            try {
                Socket serviceSocket = new Socket(this.hostname, this.loadBalancerPort);
                System.out.println("SERVICE CLIENT SOCKET=" + serviceSocket);

                ObjectOutputStream out = new ObjectOutputStream(serviceSocket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(serviceSocket.getInputStream());

                // send service request
                IMessage message = (IMessage)Message.getInstance(this.clientPort, -1, 0, Consts.REQUESTPI, iterations, 0.0, deadline);
                out.writeObject(message);
                out.flush();
                System.out.println("RQST > " + message);
                this.textArea.append("RQST > " + message + "\n");
                
                break;

            } catch(UnknownHostException e) {
                System.out.println("Could not connect to service (UnknownHostException " + e.getMessage() + ")");
            } catch (IOException e) {
                System.out.println("Could not connect to service (IOException " + e.getMessage() + ")");
                System.exit(0);
            }
            
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException " + e.getMessage());
            }
        }
    }
    
    public int getClientPort() {
        return clientPort;
    }
}
