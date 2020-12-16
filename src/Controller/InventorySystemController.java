/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


/** This class . (documentation comment) */
import InventorySystem.Inventory;
import InventorySystem.Part;
import InventorySystem.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Methods for the Inventory Management System.
 * This is the InventorySystem Controller class.
 * Features: Sample inventory data is provided for functionality testing.
 * 
 * This method is used to display the inventory for parts and product in a table format.
 * The method enables user to access parts and product to be added, modified, or deleted. 
 * Parts and products are also searchable.
 * Adding or modifying parts or products will return back to this window with the updated information.
 * 
 * Logic challenges I face while implementing the project were small errors where I would place code within a loop that did not belong.
 * For example, for the delete buttons, I could not get the alert box to close on its own after user presses OK. 
 * Because I had placed the implementations for the button inside an if statement for when the button was selected,
 * the program would execute the deletion with no instructions for what to do next. Therefore, I removed the if statement and the issue resolved.
 * Another issue I ran into was the search text fields for parts and products. I cannot get the table to refresh when the text was deleted from the text field.
 * I could get the table to refresh, but the text would automatically delete after match. I resolved the issue by having user manually backspace the searched text and
 * pressing enter key to refresh the table. 
 * @author lucytran
 */
public class InventorySystemController implements Initializable {
    
    Inventory data;

    @FXML
    private Pane PartPane;
    @FXML
    private TableView<Part> PartInvTbl;
    @FXML
    private TableColumn<Part, Integer> PartID;
    @FXML
    private TableColumn<Part, String> PartName;
    @FXML
    private TableColumn<Part, Integer> PartInvLvl;
    @FXML
    private TableColumn<Part, Double> PartPrice;
    @FXML
    private TextField PartSearchTB;
    @FXML
    private Button AddParButton;
    @FXML
    private Button ModParButton;
    @FXML
    private Button DeleteParButton;
    @FXML
    private Pane ProductPane;
    @FXML
    private TableView<Product> ProInvTbl;
    @FXML
    private TableColumn<Product, Integer> ProID;
    @FXML
    private TableColumn<Product, String> ProName;
    @FXML
    private TableColumn<Product, Integer> ProInvLvl;
    @FXML
    private TableColumn<Product, Double> ProPrice;
    @FXML
    private TextField ProSearchTB;
    @FXML
    private Button AddProButton;
    @FXML
    private Button ModProButton;
    @FXML
    private Button DeleteProButton;
    @FXML
    private Button ExitButton;
     
             
    Stage stage;
    Parent root;
    
    Part part;
    Product product;
   
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private ObservableList<Part> foundPartList = FXCollections.observableArrayList();
    private ObservableList<Product> foundProductList = FXCollections.observableArrayList();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();//for associated part
    
    /** 
     * This method initializes the controller for the inventory system.
     * This method is used to populate and display for the part and product tables.
     * Each table contains item's ID, name, price, and inventory.
     * Any time this window is loaded or returned to, the tables will populate with the most up to date information.
     * @param url N/A
     * @param rb N/A
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    { 

        //Parts Table - Main//
        PartInvTbl.setItems(Inventory.getAllParts()); 
        PartID.setCellValueFactory(new PropertyValueFactory<>("parID"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartInvLvl.setCellValueFactory(new PropertyValueFactory<>("inv"));
        
       
        //Products Table - Main//
        ProInvTbl.setItems(Inventory.getAllProducts()); 
        ProID.setCellValueFactory(new PropertyValueFactory<>("proID"));
        ProName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProInvLvl.setCellValueFactory(new PropertyValueFactory<>("inv"));
        
        
        PartInvTbl.refresh();
        ProInvTbl.refresh();

    }   
  

    @FXML
    private void SearchPart(ActionEvent event) 
    {
        String partName = PartSearchTB.getText();
        int parID = 0;
 
        partName = Inventory.getAllParts().toString();
        
        if (Inventory.lookupPart(partName) == null)
        { 

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("No Part(s) Found... Please Try Again.");
            
            alert.showAndWait();
        }          
        else          
        {
            for (Part part : Inventory.getAllParts())
            {
                
  
                if (part.getName().contains(PartSearchTB.getText()))
                {
                    foundPartList.add(part);   
                } 
            }
            
            for (int i = 0; i < allParts.size(); i++)  
            {
                part = Inventory.lookupPart(Integer.parseInt(partName));
                
                if (part.getParID() == parID) 
                {
                    allParts.get(parID); 
                    foundPartList.add(part);
                }
            }
            
            PartID.setCellValueFactory(new PropertyValueFactory<>("parID"));
            PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
            PartInvTbl.setItems(foundPartList);    
        }
        PartInvTbl.refresh();
        PartSearchTB.clear();
    }

        @FXML
    private void SearchProduct(ActionEvent event) 
    {
        String proName = ProSearchTB.getText();
        int proID = 0;
 
        proName = Inventory.getAllProducts().toString();
        
        if (Inventory.lookupProduct(proName) == null)
        { 

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("No Product(s) Found... Please Try Again.");
            
            alert.showAndWait();
        }
        else          
        {
            for (Product product : Inventory.getAllProducts())
            {
                if (product.getName().contains(ProSearchTB.getText()))
                {
                    foundProductList.add(product);   
                } 
            }
            
            for (int i = 0; i < allProducts.size(); i++)  
            {
                product = Inventory.lookupProduct(Integer.parseInt(proName));
                
                if (product.getProID() == proID)
                {
                    allProducts.get(proID); 
                    foundProductList.add(product);
                }
            
            }
            
            ProID.setCellValueFactory(new PropertyValueFactory<>("proID"));
            ProName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ProInvTbl.setItems(foundProductList);
        }
        ProInvTbl.refresh();   
        ProSearchTB.clear();

    }

    @FXML
    private void AddPart(ActionEvent event) throws IOException 
    { 
        Parent a = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene scene = new Scene(a);
  
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();   
    }

    @FXML
    private void ModifyPart(ActionEvent event) throws IOException 
    {
        
        Part selectedPart = PartInvTbl.getSelectionModel().getSelectedItem();
        int index = PartInvTbl.getSelectionModel().getSelectedIndex();
       

        if (PartInvTbl.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("No Part(s) Selected... Please Try Again.");
            
            return;
        }
        
        allParts.add(part);
        allParts.remove(part);
        
     
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        root = loader.load();
        ModifyPartController controller = loader.getController();

        if(selectedPart != null)
        {
            controller.setPart(part);
        }
        
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
        window.setScene(scene);
        window.show();
        
    }
     @FXML
    private void ModifyProduct(ActionEvent event) throws IOException 
    {
        Product selectedProduct = ProInvTbl.getSelectionModel().getSelectedItem();
        int index = ProInvTbl.getSelectionModel().getSelectedIndex();
       
        //Cannot Modify Without Selection
        if (ProInvTbl.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("No Product(s) Selected... Please Try Again.");
            
            return;
        }
        
        allProducts.add(product);
        allProducts.remove(product);
        
     
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        root = loader.load();
        ModifyProductController controller = loader.getController();

        if(selectedProduct != null)
        {
            controller.setProduct(product);
        }
        
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    
        window.setScene(scene);
        window.show();
        
    }

    @FXML
    private void DeletePart(ActionEvent event) 
    {
        Part selectedPart = PartInvTbl.getSelectionModel().getSelectedItem();
        int index = PartInvTbl.getSelectionModel().getSelectedIndex();
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Part...");
        alert.setHeaderText(null);          
        alert.setContentText("Part Deleted.");
            
        alert.showAndWait(); 
        
        allParts = PartInvTbl.getItems();        
        Inventory.getAllParts().remove(selectedPart);

        PartInvTbl.getItems().remove(selectedPart);
        PartInvTbl.refresh(); 
      
    }

     @FXML
    private void DeleteProduct(ActionEvent event) 
    {
        Product selectedProduct = ProInvTbl.getSelectionModel().getSelectedItem();
        int index = ProInvTbl.getSelectionModel().getSelectedIndex();
  
        //Error For Deletion of Product Containing Associated Part
        if (selectedProduct.getAssociatedParts().contains(index))
        { 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Cannot Be Deleted!");
            alert.setHeaderText(null);          
            alert.setContentText("ERROR: Product Contains Associated Part(s).");
           
            alert.showAndWait(); 
        }
        
        if(selectedProduct == null)
        {
            System.out.println("ERROR! No Product(s) Found.");
        }
            
        else
        {
          
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting Product...");      
            alert.setHeaderText(null);                 
            alert.setContentText("Product Deleted.");
            alert.showAndWait();
        
           
            allProducts = ProInvTbl.getItems();
            Inventory.getAllProducts().remove(selectedProduct);            

            ProInvTbl.getItems().remove(selectedProduct);
            ProInvTbl.refresh(); 
        }
    }
    
 
    @FXML
    private void AddProduct(ActionEvent event) throws IOException 
    {
        Parent b = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene scene = new Scene(b);
  
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
        
    }

    
    @FXML
    private void Exit(ActionEvent event) 
    {
        Platform.exit();
    }

}
