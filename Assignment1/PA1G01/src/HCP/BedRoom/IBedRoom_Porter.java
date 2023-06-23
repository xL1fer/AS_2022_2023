/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.BedRoom;

/**
 *
 * Bead Room Porter interface.
 */
public interface IBedRoom_Porter {
    /**
     * Porter reset checkout.
     * 
     * @param porterTotalCustomers porter total customers reference
     * @return true if there are more checkouts to be done
     */
    boolean resetCheckOut(int porterTotalCustomers);
}
