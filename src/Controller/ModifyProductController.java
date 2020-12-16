/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import InventorySystem.Inventory;
import InventorySystem.Part;
import InventorySystem.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * Methods for modifying a product.
 * This class is the controller for modifying a product.
 * This method is used to modify a selected product and then saved into the inventory.
 * The method contains the parts table that is searchable.
 * This method also allows a product to add or remove associated parts.
 * 
 * @author lucytran
 */
public class ModifyProductController implements Initializable
{

    @FXML
    private Button AddProButton;
    @FXML
    private Button RemoveAssociatedParButton;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField SearchProTB;
    @FXML
    private TableView<Part> PartInvTbl;
    @FXML
    private TableColumn<Part, Integer> PartID;
    @FXML
    private TableColumn<Part, String> PartName;
    @FXML
    private TableColumn<Part, Integer> PartInv;
    @FXML
    private TableColumn<Part, Double> PartPrice;
    @FXML
    private TableView<Part> AssociatedPartTbl;
    @FXML
    private TableColumn<Part, Integer> AssocPartID;
    @FXML
    private TableColumn<Part, String> AssocPartName;
    @FXML
    private TableColumn<Part, Integer> AssocPartInv;
    @FXML
    private TableColumn<Part, Double> AssocPartPrice;
    @FXML
    private Button SaveProButton;
    @FXML
    private TextField AddProIDTB;
    @FXML
    private TextField AddProNameTB;
    @FXML
    private TextField AddProInvTB;
    @FXML
    private TextField AddProPriceTB;
    @FXML
    private TextField AddProMinTB;
    @FXML
    private TextField AddProMaxTB;

    /**
     * All Parts List.
     * This list contains all the parts from the inventory.
     */
    public ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Associated Parts List.
     * This list contains associated parts from the inventory.
     */
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Found Parts List.
     * This list contains found parts from the inventory.
     */
    public ObservableList<Part> foundPartList = FXCollections.observableArrayList();
    
    Part part;
    Product product;
    
    /**
     * This method initializes the modifying product controller.
     * This method populates the Parts table with text and values for each column.
     * Each item in the table displays the part ID, name, price, and inventory.
     * @param url N/A
     * @param rb N/A
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
      //Populate Top Table
      PartInvTbl.setItems(Inventory.getAllParts());  
      PartID.setCellValueFactory(new PropertyValueFactory<>("parID"));
      PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
      PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
      PartInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        
    }
    
    @FXML
    private void AddPart(ActionEvent event) 
    {
        Part part = PartInvTbl.getSelectionModel().getSelectedItem();   
        associatedParts.add(part);
       

        AssociatedPartTbl.setItems(associatedParts);

        AssocPartID.setCellValueFactory(new PropertyValueFactory<>("parID"));
        AssocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssocPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        AssocPartInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
       
        AssociatedPartTbl.refresh();
    }

    /**
     * This method sets the Product.
     * This method is used to set a product to the inventory.
     * @param product This is the product that is being modified
     */
    public void setProduct(Product product)
    {
        this.product = product;
     
    }
    @FXML
    private void RemoveAssocPart(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Removing Associated Part...");
        alert.setHeaderText(null);
        alert.setContentText("Continue?");
        
        Optional<ButtonType> answer = alert.showAndWait();
       
        if(answer.get() == ButtonType.OK)
        {
            Part deleteAssociatedPart = AssociatedPartTbl.getSelectionModel().getSelectedItem();
            AssociatedPartTbl.getItems().remove(deleteAssociatedPart);
            
            AssociatedPartTbl.refresh();
        }
        else
        {
            System.out.println("Part Selected: Not Removed.");
            
        }
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Canceling...");
        alert.setHeaderText(null);
        alert.setContentText("Continue?");
        
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK)
        {
            Parent root = FXMLLoader.load(getClass().getResource("InventorySystem.fxml"));
            Scene scene = new Scene(root);
           
           
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(scene);
            window.show();
  
        }
        else
        {
            System.out.println("Not Canceled.");
        }
    }


    @FXML
    private void SearchPart(ActionEvent event) 
    {       
        String partName = SearchProTB.getText();
        int parID = 0;

        partName = Inventory.getAllParts().toString();
        
        if (Inventory.lookupProduct(partName) == null)
        { 

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("No Product(s) Found... Please Try Again.");
            
            alert.showAndWait();
        }
        else
        {
            for (Part part : Inventory.getAllParts())
            {
                if (part.getName().contains(SearchProTB.getText()))
                {
                    foundPartList.add(part);     
                } 
            }
            
            for (int i = 0; i < allParts.size(); i++)  
            {   
                part = Inventory.lookupPart(Integer.parseInt(partName));
                
                if (part.getParID() == parID);
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
        SearchProTB.clear(); 
    }

    @FXML
    private void SaveProduct(ActionEvent event) throws IOException 
    { 
        int proID = 0;
        
        for (Product product : Inventory.getAllProducts())
        {
            if (product.getProID() == proID)
            {
                proID = product.getProID();
            }
        }
        AddProIDTB.setText(String.valueOf(++proID));
        String name = AddProNameTB.getText();
        int inv = Integer.parseInt(AddProInvTB.getText());
        double price = Double.parseDouble(AddProPriceTB.getText());           
        int min = Integer.parseInt(AddProMinTB.getText());
        int max = Integer.parseInt(AddProMaxTB.getText());
     
        Product product = new Product(proID, name, price, inv, min, max);
       
        product.setProID(proID);
        product.setName(name);
        product.setInventory(inv);
        product.setMin(min);
        product.setMax(max);
   

        Parent root = FXMLLoader.load(getClass().getResource("InventorySystem.fxml"));
        Scene scene = new Scene(root);
           
           
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
            
    }

    

    @FXML
    private void AddProID(ActionEvent event) {
    }

    @FXML
    private void AddProName(ActionEvent event) {
    }

    @FXML
    private void AddProInv(ActionEvent event) {
    }

    @FXML
    private void AddProPrice(ActionEvent event) {
    }

    @FXML
    private void AddProMin(ActionEvent event) {
    }

    @FXML
    private void AddProMax(ActionEvent event) {
    }

   
    
}
