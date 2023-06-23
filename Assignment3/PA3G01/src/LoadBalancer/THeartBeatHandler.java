/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoadBalancer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Load Balancer thread class.
 */
public class THeartBeatHandler implements Runnable {
    private ServerSocket heartbeatServerSocket;
    private final int heartbeatPort;
    
    private THeartBeatHandler(int heartbeatPort) {
        this.heartbeatPort = heartbeatPort;
    }

    public static Runnable getInstance(int heartbeatPort) {
        return new THeartBeatHandler(heartbeatPort);
    }
    
    @Override
    public void run() {
        try {
            this.heartbeatServerSocket = new ServerSocket(this.heartbeatPort);
            System.out.println("Listening for heartbeat connections on port " + this.heartbeatPort);
            System.out.println("HEARTBEAT SERVER SOCKET=" + this.heartbeatServerSocket);
            while(true) {
                Socket heartbeatSocket = heartbeatServerSocket.accept(); // blocking
                System.out.println("HEARTBEAT HANDLE SOCKET (created at accept())=" + heartbeatSocket);
                new Thread(() -> {
                    handleHeartBeat(heartbeatSocket);
                }).start();
            }
        } catch(IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }
    
    public void handleHeartBeat(Socket heartbeatSocket) {
        try {
            DataOutputStream out = new DataOutputStream(heartbeatSocket.getOutputStream());
            out.flush();
            DataInputStream in = new DataInputStream(heartbeatSocket.getInputStream());

            // heartbeat handling
            while (true) {
                byte data = in.readByte();
                out.writeByte(0xAA);
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }
}
