/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Methods for obtaining, adding, or removing product(s) from the inventory.
 * This class is a subclass which derives from another class. 
 * This class inherits methods and implementations from another class.
 * This method allows access to product information.
 */
 

public class Product 
{
   
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
 
    private int proID;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;
      
    /** This method allows certain text and values for product to be accessed. 
    * Using this method, a new product can be created using the same methods and implementation.
    * @param proID product ID
    * @param name product name
    * @param price product price
    * @param inv product inventory
    * @param min product minimum
    * @param max product maximum
    */
    public Product(int proID, String name, double price, int inv, int min, int max) 
    {
        this.proID = proID;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;      
    }
    /**
     * 
     * @return The ID for product.
     */
    public int getProID()
    {
     return proID;   
    }
    
    /**
     * 
     * @return The name for product.
     */
    public String getName()
    {
        return name;
    }
    /**
     * 
     * @return The price for product.
     */
    public double getPrice()
    {
        return price;
    }
    /** 
     * @return The inventory for product.
     */
    public int getInv()
    {
        return inv;
    }
    /**
     * Get Product Minimum.
     * @return The product minimum value.
     */
    public int getMin()
    {
        return min;
    }
    /**
     * 
     * @return The product maximum value.
     */
    public int getMax()
    {
        return max;
    }

    /**
     * 
     * @param proID The product ID to set.
     */
    public void setProID(int proID)
    {
        this.proID = proID;
    }
   
    /**
     * 
     * @param name The product name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * 
     * @param price The product price to set.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
           
    /**
     * 
     * @param inv The product inventory to set.
     */
    public void setInventory(int inv)
    {
        this.inv = inv;
    }
    
   /**
    * 
    * @param min The product minimum value to set.
    */
    public void setMin(int min)
    {
        this.min = min;
    }
    
    /**
     * 
     * @param max The product maximum value to set.
     */
    public void setMax(int max)
    {
        this.max = max;
    }
    
    /**
     * 
     * @param part This is the part that is added to be associated with a product.
     */
    public void addAssociatedPart(Part part)
    {
        associatedParts.add(part);
    }
    
    /**
     * 
     * @param selectedAssociatedPart This is the part selected to be deleted that is associated with a product.
     * @return true This will delete an associated part.
     */
    
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        int i;
        for (i = 0; i < associatedParts.size(); i ++)
        {
            if (associatedParts.get(i).getParID() == selectedAssociatedPart.getParID())
            {
                 associatedParts.remove(i);
                 return true;
            }
               
        }
        return false; 
    }
    
    /**
     * Associated Parts List.
     * This contains a list of parts that have association with a product.
     * @return associatedParts This returns the list of associated parts for product.
     */
    public ObservableList<Part> getAssociatedParts()
    {
        return associatedParts;
    }
     
    
}