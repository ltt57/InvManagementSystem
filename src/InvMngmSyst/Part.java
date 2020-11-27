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
public abstract class Part {
    private int parID;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;    
    
    public Part(int parID, String name, double price, int inv, int min, int max) {
        this.parID = parID;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getParID() {
        return parID;
    }

    /**
     * @param id the id to set
     */
    public void setParID(int parID) {
        this.parID = parID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the stock
     */
    public int getInv() {
        return inv;
    }

    /**
     * @param stock the stock to set
     */
    public void setInv(int inv) {
        this.inv = inv;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    public static Part findPart(String findName)
    {
        for (Part findPart : Inventory.getAllParts())
        {
            if (findPart.getName().contains(findName)  == new Integer (findPart.getParID()).toString().equals(findName))
      
            
            return findPart;
        }
    
        return null;
    
    }
}
