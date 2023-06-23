/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.Serializable;

/**
 * Message class.
 */
public class Message implements Serializable, IMessage {
    /**
     * Message serial specifier.
     */
    private static final long serialVersionUID = 2023L;
    
    private int clientId;
    private int requestId;
    private int serverId;
    private int messageCode;
    private int iterations;
    private double pi;
    private int deadline;
    
    /**
     * Message class constructor.
     * 
     * @param clientId client id
     * @param requestId request id
     * @param serverId server id
     * @param messageCode message code
     * @param iterations iterations number
     * @param pi pi number
     * @param deadline deadline
     */
    private Message(int clientId, int requestId, int serverId, int messageCode, int iterations, double pi, int deadline) {
        this.clientId = clientId;
        this.requestId = requestId;
        this.serverId = serverId;
        this.messageCode = messageCode;
        this.iterations = iterations;
        this.pi = pi;
        this.deadline = deadline;
    }
    
    /**
     * Create instance of reply message object.
     * 
     * @param clientId client id
     * @param requestId request id
     * @param serverId server id
     * @param messageCode message code
     * @param iterations iterations number
     * @param pi pi number
     * @param deadline deadline
     * @return message object instance
     */
    public static IMessage getInstance(int clientId, int requestId, int serverId, int messageCode, int iterations, double pi, int deadline) {
        return new Message(clientId, requestId, serverId, messageCode, iterations, pi, deadline);
    }

    @Override
    public String toString() {
        return "[" + "clId=" + clientId + ", reqId=" + requestId + ", svId=" + serverId + ", msgC=" + messageCode + ", iter=" + iterations + ", pi=" + pi + ", dl=" + deadline + ']';
    }

    @Override
    public int getClientId() {
        return clientId;
    }

    @Override
    public int getRequestId() {
        return requestId;
    }
    @Override
    public int getServerId() {
        return serverId;
    }

    @Override
    public int getMessageCode() {
        return messageCode;
    }

    @Override
    public int getIterations() {
        return iterations;
    }

    @Override
    public double getPi() {
        return pi;
    }

    @Override
    public int getDeadline() {
        return deadline;
    }
    
    @Override
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Override
    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public void setPi(double pi) {
        this.pi = pi;
    }

    @Override
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
}
