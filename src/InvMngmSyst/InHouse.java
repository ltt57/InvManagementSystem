/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvMngmSyst;

/**
 *
 * @author lucytran
 */
public class InHouse extends Part 
{
    private int machineID;
    
    public InHouse(int parID, String name, double price, int inv, int min, int max, int machineID)
    {
        super(parID, name, price, inv, min, max);
        this.machineID = machineID;
    }
    
    public int getMachineID()
    {
        return machineID;
    }
    
    public void setMachineID(int machineID)
    {
        this.machineID = machineID;
    }
}
