/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.Comms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import HCP.Log.ILog;

/**
 *
 * Server interface.
 */
public class TServer implements Runnable{
    /**
     * Acceptance server TCP socket connection.
     */
    private ServerSocket serverSocket;
    /**
     * Server TCP socket connection.
     */
    private Socket socket;
    /**
     * General message log monitor.
     */
    private final ILog mLogMessage;

    /**
     * Server class constructor.
     * 
     * @param port connection port
     * @param mLogMessage general message log monitor
     */
    private TServer(int port, ILog mLogMessage) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Hostel is listening on port: " + port);
        } catch (IOException ex) {}
        this.mLogMessage = mLogMessage;
    }

    /**
     * Create instance of server object.
     * 
     * @param port connection port
     * @param mLogMessage general message log monitor
     * @return server instance
     */
    public static Runnable getInstance(int port, ILog mLogMessage) {
        return new TServer(port, mLogMessage);
    }
    
    @Override
    public void run() {
       while(true) {
           try {
                socket = serverSocket.accept();
                new Thread(TControlCentreProxy.getInstance(socket, mLogMessage)).start();
           } catch (IOException ex) {}
       }
    }
}
