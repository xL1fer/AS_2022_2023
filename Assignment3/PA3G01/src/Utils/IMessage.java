/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Utils;

/**
 * Message interface.
 */
public interface IMessage {
    String toString();

    int getClientId();

    int getRequestId();

    int getServerId();

    int getMessageCode();

    int getIterations();

    double getPi();

    int getDeadline();
    
    void setClientId(int clientId);

    void setRequestId(int requestId);

    void setServerId(int serverId);

    void setMessageCode(int messageCode);

    void setIterations(int iterations);

    void setPi(double pi);

    void setDeadline(int deadline);
}
