/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Monitor.Manager;

import Utils.IMessage;

/**
 * Manager Monitor interface.
 */
public interface IManager_Monitor {
    IMessage getAvailablePort(IMessage message);
    
    IMessage getAvailableServer(IMessage message);
    
    IMessage getClientId(IMessage message);
    
    void removeRequest(IMessage message);
    
    void removeServer(int serverPort, String hostname, int loadBalancerPort);
}
