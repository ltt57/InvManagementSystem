/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvMngmSyst;

import static InvMngmSyst.Inventory.lookupPart;
import static InvMngmSyst.Part.findPart;
import static InvMngmSyst.Product.findProduct;
import javafx.application.Application;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Inventory extends Application
{
 
    @Override
    public void start(Stage primarystage) throws Exception  //KEEP//
    {   
      

       Parent root;
       root = FXMLLoader.load(getClass().getResource("AddModify/Main.fxml"));
       Scene scene = new Scene(root);
       
       primarystage.setScene(scene);
       primarystage.show();
     
    }
    
    public static void main(String[] args)
    {
      launch(args);
    }    
 
 
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
    
    public static void deletePart(Part part)
    {
        allParts.remove(part);
    }
    
    public static void deleteProduct(Product product)
    {
        allProducts.remove(product);
    }
   
    public int partListArraySize()
    {
        return allParts.size();
    }
    
    public static Part lookupPart(int parID)
    {
       return null;
    }    
    
    public static Product lookupProduct(int proID)
    {
        return null;
    }
    
    public static ObservableList<Part> lookupPart(String parName)
    {
        return allParts;
    }
    
    public static ObservableList<Product> lookupProduct(String proName)
    {
        return allProducts;
    }
    
    
    
    public static void addPart(Part part)
    {
        allParts.add(part);
    }
    
    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }
    
    public static void updatePart(int index, Part part)
    {
        allParts.set(index, part);
        
    }
    
    public static void updateProduct(int index, Product product)
    {
        allProducts.set(index, product);
    }

   

}
