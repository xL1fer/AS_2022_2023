/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.BedRoom;

/**
 *
 * Bead Room Customer interface.
 */
public interface IBedRoom_Customer {
    /**
     * Customer go to bed.
     * 
     * @param customerId customer id
     * @param bedOffset customer bed offset
     */
    void goToBed(int customerId, int bedOffset);
    /**
     * Customer wait bathroom.
     * 
     * @param customerId customer id
     * @param bedOffset customer bed offset
     */
    void waitBathroom(int customerId, int bedOffset);
    /**
     * Customer use bathroom.
     * 
     * @param customerId customer id
     * @param bedOffset customer bed offset
     */
    void useBathroom(int customerId, int bedOffset);
}
