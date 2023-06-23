/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.Serializable;

/**
 *
 * Message class.
 */
public class Message implements Serializable, IMessage{
    /**
     * Message serial specifier.
     */
    private static final long serialVersionUID = 2023L;
    
    /**
     * Message's action.
     */
    private final String action;
    /**
     * Message's number of customers.
     */
    private final int customers;
    /**
     * Message's check-in time.
     */
    private final int timeCheckIn;
    /**
     * Message's bathroom time.
     */
    private final int timeBathroom;
    /**
     * Message's breakfast time.
     */
    private final int timeBreakfast;
      
    /**
     * Message class constructor.
     * 
     * @param msg message object
     */
    private Message(Message msg) {
        this.action = msg.action;
        this.customers = msg.customers;
        this.timeCheckIn = msg.timeCheckIn;
        this.timeBathroom = msg.timeBathroom;
        this.timeBreakfast = msg.timeBreakfast;
    }
    
    /**
     * Message class constructor.
     * 
     * @param action message action
     * @param customers message number of customers
     * @param timeCheckIn message check-in time
     * @param timeBathroom message bathroom time
     * @param timeBreakfast message breakfast time
     */
    private Message(String action, int customers, int timeCheckIn, int timeBathroom, int timeBreakfast) {
        this.action = action;
        this.customers = customers;
        this.timeCheckIn = timeCheckIn;
        this.timeBathroom = timeBathroom;
        this.timeBreakfast = timeBreakfast;
    }

    /**
     * Create instance of message class.
     * 
     * @param msg message object
     * @return message object instance
     */
    public static  IMessage getInstance(Message msg) {
        return new Message(msg);
    }  
    
    /**
     * Create instance of message class.
     * 
     * @param action message action
     * @param customers message number of customers
     * @param timeCheckIn message check-in time
     * @param timeBathroom message bathroom time
     * @param timeBreakfast message breakfast time
     * @return message object instance
     */
    public static IMessage getInstance(String action, int customers, int timeCheckIn, int timeBathroom, int timeBreakfast) {
        return new Message(action, customers, timeCheckIn, timeBathroom, timeBreakfast);
    }
    
    @Override
    public void printMessage() {
        System.out.println("(" + this.action + ", customers = " + this.customers + ", tci = " + this.timeCheckIn + ", tbr = " + this.timeBathroom + ", tbf = " + this.timeBreakfast + ")");
    }

    @Override
    public String getAction() {
        return this.action;
    }
    
    @Override
    public int getCustomers() {
        return this.customers;
    }

    @Override
    public int getTimeCheckIn() {
        return this.timeCheckIn;
    }

    @Override
    public int getTimeBathroom() {
        return this.timeBathroom;
    }

    @Override
    public int getTimeBreakfast() {
        return this.timeBreakfast;
    }
}
