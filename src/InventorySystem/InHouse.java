/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

/** 
 * Methods for a part that is InHouse.
 * This class extends Part class. 
 * This class is related to the Part class by inheritance.
 * This class is a subclass of Part and inherits the attributes and methods from the Part class.
 */

public class InHouse extends Part 
{
    private int machineID;

    /**
     * Methods for text and values for a part that is InHouse.
     * The method allows these attributes and methods to be inherited from another class. 
     * This method is to allow an InHouse part to be created or modified.
     * This method requests a Machine ID.
     * @param parID part ID
     * @param name part name
     * @param price part price
     * @param inv part inventory
     * @param min part minimum
     * @param max part maximum
     * @param machineID part machine ID
     */
    public InHouse(int parID, String name, double price, int inv, int min, int max, int machineID)
    {
        super(parID, name, price, inv, min, max);
        this.machineID = machineID;
    }
    
    /** 
     * The MachineID for InHouse Part.  
     * Retrieves Machine ID.
     * @return machineID The machineID retrieved
     */
    public int getMachineID()
    {
        return machineID;
    }
    
    /** 
     * MachineID implementation for a Part that is InHouse.
     * Takes the MachineID and sets it.
     * @param machineID This is the Machine ID 
     */
    public void setMachineID(int machineID)
    {
        this.machineID = machineID;
    }
}