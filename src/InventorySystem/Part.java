/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

/**
 * Methods for obtaining, adding, or removing part(s) from the inventory.
 * This abstract class is accessed when it is inherited from another class.
 * This class is the superclass.
 * This method allows certain text and values for part to be accessed.
 * Using this method, a new Part can be created using the same methods and implementation.
 */

public abstract class Part 
{
    private int parID;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;    
    
    /**
     * 
     * @param parID part ID
     * @param name part name
     * @param price part price
     * @param inv part inventory
     * @param min part minimum
     * @param max part maximum
     */
    public Part(int parID, String name, double price, int inv, int min, int max) 
    {
        this.parID = parID;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    /**
     * @return The ID for part.
     */
    public int getParID() 
    {
        return parID;
    }

    /**
     * @param parID The part ID to set.
     */
    public void setParID(int parID) 
    {
        this.parID = parID;
    }

    /**
     * @return The name for part.
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @param name The part name to set.
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * @return The price for part.
     */
    public double getPrice() 
    {
        return price;
    }

    /**
     * @param price The part price to set.
     */
    public void setPrice(double price) 
    {
        this.price = price;
    }
    
    /**
     * @return The inventory for part.
     */
    public int getInv() 
    {
        return inv;
    }

    /**
     * @param inv The part inventory to set.
     */
    public void setInv(int inv) 
    {
        this.inv = inv;
    }

    /**
     * @return The part minimum value.
     */
    public int getMin() 
    {
        return min;
    }

    /**
     * @param min The part minimum value to set.
     */
    public void setMin(int min) 
    {
        this.min = min;
    }

    /**
     * @return The part maximum value.
     */
    public int getMax() 
    {
        return max;
    }

    /**
     * @param max The part maximum value to set.
     */
    public void setMax(int max) 
    {
        this.max = max;
    }
   
    
 }

