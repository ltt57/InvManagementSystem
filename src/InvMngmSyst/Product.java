package InvMngmSyst;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Product 
{   
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int proID;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;
            
    public Product(int proID, String name, double price, int inv, int min, int max) 
    {
        this.proID = proID;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;      
    }
    
    public int getProID()
    {
     return proID;   
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public int getInv()
    {
        return inv;
    }
    
    public int getMin()
    {
        return min;
    }
    
    public int getMax()
    {
        return max;
    }
    
    public ObservableList<Part> getAssociatedParts()
    {
        return associatedParts;
    }
    
    public void setProID(int proID)
    {
        this.proID = proID;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
           
    public void setInventory(int inv)
    {
        this.inv = inv;
    }
    
    public void setMin(int min)
    {
        this.min = min;
    }
    
    public void setMax(int max)
    {
        this.max = max;
    }
    
    public void setAssociatedParts (Part part)
    {
        associatedParts.add(part);
    }
   
    
    public static Product findProduct(String findName)
    {
        for (Product findProduct : Inventory.getAllProducts())
        {
            if (findProduct.getName().contains(findName)  == new Integer (findProduct.getProID()).toString().equals(findName))
      
            
            return findProduct;
        }
        
        return null;
           
    }

}