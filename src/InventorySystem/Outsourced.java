/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

/**
 * Methods for a part that is Outsourced.
 * This class extends Part class.
 * This class is related to the Part class by inheritance.
 * This class is a subclass of Part and inherits the attributes and methods from the Part class.
 */

public class Outsourced extends Part
{
    private String compName;
    
    /**
     * Methods for text and values for a part that is Outsourced.
     * The method allows these attributes and methods to be inherited from another class.
     * This method is to allow an Outsourced part to be created or modified.
     * This method requests a Company Name.
     * @param parID part ID
     * @param name part name
     * @param price part price
     * @param inv part inventory
     * @param min part minimum
     * @param max part maximum
     * @param compName part company name
     */
    public Outsourced(int parID, String name, double price, int inv, int min, int max, String compName)
    {
        super(parID, name, price, inv, min, max);
        this.compName = compName;
    }
    /**
     * The Company Name for Outsourced Part.
     * @return CompName The company name retrieved
     */
    public String getCompName()
    {
        return compName;
    }
 
    /**
     * Company Name implementation for a Part that is Outsourced.
     * Takes the Company Name and sets it.
     * @param compName This is the company name to set for an outsourced part
     */
    public void setCompName(String compName)
    {
        this.compName = compName;
    }
}
