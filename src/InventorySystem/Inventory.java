/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory program implements an application that has parts and products located an inventory.
 * The program allows parts and products to be accessed for purposes as to search, add, delete, or update.
 */
public class Inventory 
{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
  
/**
 * This method adds a part.
 * This method adds a part to all parts list in the Inventory.
 * @param part This is the part to be added
 */    
  public static void addPart(Part part)
  {
      allParts.add(part);
      
  }
  
  /**
   * This method adds a product.
   * This method adds a product to all products list in the Inventory.
   * @param product This is the product that will be added
   */
  public static void addProduct(Product product)
  {
      allProducts.add(product);
    
  }
  
  /**
   * This method lookup a part using the part ID.
   * This method finds a part using the part ID.
   * @param parID This is the part ID used to find the matching part.
   * @return part This returns the part with the matching part ID.
   */ 
  public static Part lookupPart (int parID)
  {
     
     if (allParts != null)
     {
         for (int i = 0; i < allParts.size(); i++)
         {
             if (allParts.get(i).getParID() == parID)
             {
             return allParts.get(i); 
             }
         }  
     }
     return null;
  }
 
  /**
   * This method lookup a product using the product ID.
   * This method finds a product using the product ID.
   * @param proID This is the product ID used to find the matching product
   * @return product This returns the product with the matching product ID
   */
  public static Product lookupProduct(int proID)
  {
    if (allProducts != null)
     {
         for (int i = 0; i < allProducts.size(); i++)
         {
             if (allProducts.get(i).getProID() == proID)
             {
             return allProducts.get(proID); 
             }
         }  
     }
     return null;
  }

  /**
   * This method lookup part using the part name.
   * This method finds a part within the part list in the inventory by matching the part name.
   * @param partName This is the part name used to find the matching part
   * @return foundPartList This returns the part with the matching part name from list of names matching for part
   */
  public static ObservableList<Part> lookupPart (String partName)

  {
     if(allParts != null)
     {
         ObservableList foundPartList = FXCollections.observableArrayList();
         for (Part part : getAllParts())
         {
             if (part.getName().contains(partName))
             {
                 foundPartList.add(part);
             }
         }
         return foundPartList;
     }
     return null; 
  }

  /**
   * This method lookup product using the product name.
   * This method finds a product within the product list in the inventory by matching the product name.
   * @param proName This is the product name used to find the matching product
   * @return foundProductList This returns the product with the matching product name from list of names matching for product
   */
  public static ObservableList<Product> lookupProduct (String proName)
  {
      if(allProducts != null)
     {
         ObservableList foundProductList = FXCollections.observableArrayList();
         for (Product product : getAllProducts())
         {
             if (product.getName().contains(proName))
             {
                 foundProductList.add(product);
             }
         }
         return foundProductList;
     }
     return null; 
      
  }
  
  /**
   * This method updates a part.
   * This method updates a part that is selected.
   * @param selectedPart This is the part that will be updated
   * @param index This is the index of the selected part that will be updated
   */
  public static void updatePart(Part selectedPart, int index)
  {
      
      for (int i = 0; i < allParts.size(); i++ ) 
          {
              
              if(allParts.get(i).getParID()  == selectedPart.getParID())
              {
                  allParts.set(i, selectedPart);
                  break;
              }
          } 
      
      selectedPart.setParID(selectedPart.getParID());
      allParts.set(index, selectedPart);

  }
  
  /**
   * This method updates a product.
   * This method updates a product that is selected.
   * @param product This is the product that will be updated
   * @param index This is the index of the product that will be updated
   */
  public static void updateProduct(Product product, int index)
  {
          for (int i = 0; i < allProducts.size(); i++ )
          {
              if(allProducts.get(i).getProID()  == product.getProID())
              {
                  allProducts.set(i, product);
                  break;
              }
          }
      
          product.setProID(product.getProID());
          allProducts.set(index, product);
  }

  /**
   * This method removes a part.
   * This method deletes a part that is selected.
   * @param selectedPart This is the part that will be removed
   * @return true The return removes the part
   */
  public boolean deletePart(Part selectedPart)
  {
     for (int i = 0; i < allParts.size(); i ++)
     {
         if (allParts.get(i).getParID() == selectedPart.getParID())
         {
             allParts.remove(selectedPart);
             return true;
         }    
     }  
     return false; 
  }
 
/**
 * This method removes a product.
 * This method deletes a product that is selected.
 * @param selectedProduct This is the product that will be removed
 * @return true The return removes the product
 */  
  public boolean deleteProduct(Product selectedProduct)
  {
      for (int i = 0; i < allProducts.size(); i ++)
     {
         if (allProducts.get(i).getProID() == selectedProduct.getProID())
         {
             allProducts.remove(selectedProduct);
             return true;
         }
     }
     return false; 
  }
  
  /**
   * This method retrieves all parts.
   * This method gets all the parts located in the inventory.
   * @return allParts This returns all the parts from the inventory
   */
  public static ObservableList<Part> getAllParts()
  {
      return allParts;
  }
  
  /**
   * This method retrieves all products.
   * This method gets all the products located in the inventory.
   * @return allProducts This returns all the products from the inventory
   */
  public static ObservableList<Product> getAllProducts()
  {
      return allProducts;
  }
  
}



