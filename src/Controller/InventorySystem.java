/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import InventorySystem.InHouse;
import InventorySystem.Inventory;
import InventorySystem.Outsourced;
import InventorySystem.Part;
import InventorySystem.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class extends an application that displays an Inventory Management System. 
 *  @author Lucy Tran
 */
public class InventorySystem extends Application
{

    /**
     * This is the main method.
     * This method launches class InventorySystem, 
     * The method contains inventory data that can be accessed.
     * @param args Unused.
     */
    public static void main(String[] args) 
    {
      
        //Create & Display Sample Data
        Inventory data = new Inventory();
        
        
        //Sample Part Data 
        Part a = new InHouse(1, "Part A", 2.60, 12, 3, 109, 103);
        Part a2 = new InHouse(3, "Part A2", 5.49, 10, 2, 120, 104);
        Part b = new InHouse(2, "Part B", 3.87, 9, 3, 122, 102);
        
        Inventory.addPart(a);
        Inventory.addPart(a2);
        Inventory.addPart(b);
        
        Inventory.addPart(new InHouse(4, "Part A3", 2.73, 13, 12, 130, 150));
        Inventory.addPart(new InHouse(5, "Part A4", 5.09, 3, 6, 103, 109));
        
        Part c = new Outsourced(6, "Part C", 2.50, 12, 4, 110, "CHEKN Co.");
        Part d = new Outsourced(7, "Part D", 3.55, 10, 3, 100, "DIFF Co.");
        Part e = new Outsourced(8, "Part E", 2.45, 15, 5, 105, "EURO Co.");
        
        Inventory.addPart(c);
        Inventory.addPart(d);
        Inventory.addPart(e);
        
        Inventory.addPart(new Outsourced(9, "Part S", 2.80, 6, 3, 80,"SOUTHPOLE Co."));
        Inventory.addPart(new Outsourced(10, "Part L", 2.90, 10, 7, 205, "LINCOLN Co."));
        
        //Sample Product Data
        Product pro1 = new Product(104, "Product 1", 6.95, 13, 8, 120);
        pro1.addAssociatedPart(a);
        pro1.addAssociatedPart(c);
        
        Inventory.addProduct(pro1);
        
        Product pro2 = new Product(202, "Product 2", 7.83, 18, 2, 140);
        pro2.addAssociatedPart(a2);
        pro2.addAssociatedPart(d);
        
        Inventory.addProduct(pro2);
        
        Product pro3 = new Product(302, "Product 3", 9.99, 36, 2, 103);
        pro3.addAssociatedPart(b);
        pro3.addAssociatedPart(e);
        
        Inventory.addProduct(pro3);
        Inventory.addProduct(new Product(400, "Product 4", 37.00, 22, 10, 100));
        Inventory.addProduct(new Product(500, "Product 5", 40.05, 19, 8, 205));
        
        
        launch(args); 
    }

    /**
     * Displays Inventory Management System window.
     * This method starts the application with this stage.
     * @param stage The first stage.
     * @throws Exception Gets class InventorySystem to load as the first stage.
     */
    @Override
    public void start(Stage stage) throws Exception       
    {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InventorySystem.fxml"));
        InventorySystemController controller = new InventorySystemController();
        loader.setController(controller);
        
        Parent root = FXMLLoader.load(getClass().getResource("InventorySystem.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();

    }
    
   
}
