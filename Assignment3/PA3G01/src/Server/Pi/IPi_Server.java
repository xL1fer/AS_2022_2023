/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Server.Pi;

import Utils.IMessage;

/**
 * Pi service Server interface.
 */
public interface IPi_Server {
    void waitTurn(IMessage message);
    
    char getPiIteration(int i);
    
    void signalCompletion();
}
