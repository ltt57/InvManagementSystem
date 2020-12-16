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
 * Methods for adding product to the inventory.
 * This class is the controller for adding a product.
 * This method is used to add a new product to the inventory.
 * When the add product button is clicked from the Inventory Management System window,
 * a new window is presented using this class to display and implement methods for adding a product to the Inventory.
 * This class allows new products to be added and the ability to search for parts, as well as add or remove associated parts for a product. 
 * The data provided from user will then be saved to the Inventory and displayed on the Inventory Management System window in the Product table.
 */
public class AddProductController implements Initializable {

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
    
    Product product;

    /**
     * This method initializes the controller for adding a product.
     * The method gets all the parts that are located in the Inventory and displays the parts in the Parts table.
     * The method disables the ID text field for adding a product to prevent modifications.
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
 
     
      AddProIDTB.setDisable(true);
      
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
        
        for(Part part : PartInvTbl.getItems())
        {
            if (part.getName().contains(partName))
            {
                foundPartList = PartInvTbl.getItems();
                foundPartList.add(PartInvTbl.getSelectionModel().getSelectedItem());      
            }
            else
            {
                part = Inventory.lookupPart(Integer.parseInt(partName));
                
                if(part.getParID() == parID)
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
        String proName = AddProNameTB.getText();
        int proID = 0;
        

        for (Product product : Inventory.getAllProducts())
        {
            
            if (product.getProID() > proID)
            {
                proID = product.getProID();
            }
        }
        
        AddProIDTB.setText(String.valueOf(++proID)); //Generate ID
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

        Inventory.addProduct(product);

        
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
    
}
