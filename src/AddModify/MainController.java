/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddModify;

import InvMngmSyst.Inventory;
import InvMngmSyst.Part;
import InvMngmSyst.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucytran
 */
public class MainController implements Initializable {

    @FXML
    private TextField SearchParInv;
    @FXML
    private Label PartsLabel;
    @FXML
    private Button AddParButton;
    @FXML
    private Button ModParButton;
    @FXML
    private Button DeleteParButton;
    @FXML
    private TableView<Part> ParInvTbl;
    @FXML
    private TableColumn<Part, Integer> PartID;
    @FXML
    private TableColumn<Part, String> ParName;
    @FXML
    private TableColumn<Part, Integer> PartInvLvl;
    @FXML
    private TableColumn<Part, Double> ParPrice;
    @FXML
    private Button ParGoButton;
    @FXML
    private Label ProductLabel;
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
    private TextField SearchProInv;
    @FXML
    private Button AddProButton;
    @FXML
    private Button ModProButton;
    @FXML
    private Button DeleteProButton;
    @FXML
    private Button ProGoButton;
    @FXML
    private Button ExitInvButton;
    @FXML
    private AnchorPane Main;
    @FXML
    private Label InventoryLabel;
    @FXML
    private AnchorPane MainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Parts Table - Main//
        ParInvTbl.setItems(Inventory.getAllParts());  
        PartID.setCellValueFactory(new PropertyValueFactory<>("parID"));
        ParName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ParPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartInvLvl.setCellValueFactory(new PropertyValueFactory<>("inv"));
       
        //Products Table - Main//
        ProInvTbl.setItems(Inventory.getAllProducts());   
        ProID.setCellValueFactory(new PropertyValueFactory<>("proID"));
        ProName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProInvLvl.setCellValueFactory(new PropertyValueFactory<>("inv"));

    }    

    @FXML
    private void SearchParInv(ActionEvent event) 
    {
        int parID = Integer.parseInt(SearchParInv.getText());
        ParInvTbl.getSelectionModel().select(Inventory.lookupPart(parID));
        String findName = SearchParInv.getText();
        ParInvTbl.getSelectionModel().select(Part.findPart(findName));
        
        if (Inventory.lookupPart(parID) == null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Part Not Found. Try Again...");
            
            alert.showAndWait();
           
        }

    }

    @FXML
    private void AddPar(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    @FXML
    private void ModPar(ActionEvent event) throws IOException 
    {
        Stage stage;
        Parent root;
        stage = (Stage) ModParButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        
        
        root = loader.load();
        
        ModifyPartController controller = loader.getController();
        Part part = ParInvTbl.getSelectionModel().getSelectedItem();
        
        int index = ParInvTbl.getSelectionModel().getSelectedIndex();
        if(part != null)
        {
            controller.setPart(part, index); 
        }
        
      
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void DeletePar(ActionEvent event) 
    {
        Alert alert = new Alert(AlertType.WARNING);
    
         alert.setTitle("Warning!");
         alert.setHeaderText("Deleting...");
         alert.setContentText("Continue?");

         alert.showAndWait();
         
        Optional<ButtonType> answer = alert.showAndWait();

        if(answer.get() == ButtonType.OK) 
        {
            Part selectedItem = ParInvTbl.getSelectionModel().getSelectedItem();
            ParInvTbl.getItems().remove(selectedItem);
            ParInvTbl.refresh();;
            //nov. 26 5:33//
        }
    }

    @FXML
    private void SearchProInv(ActionEvent event) 
    {
         int proID = Integer.parseInt(SearchProInv.getText());       
         ProInvTbl.getSelectionModel().select(Inventory.lookupProduct(proID));
         String findName = SearchProInv.getText();
         ProInvTbl.getSelectionModel().select(Product.findProduct(findName));
        
       if(Inventory.lookupProduct(proID) == null)
       { 
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Product Not Found. Try Again...");
            
            alert.showAndWait();
       }

    }

    @FXML
    private void AddPro(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    @FXML
    private void ModPro(ActionEvent event) throws IOException 
    {
        Stage stage;
        Parent root;
        stage = (Stage) ModProButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        
        
      
       root = loader.load();
        ModifyPartController controller = loader.getController();
        Product product = ProInvTbl.getSelectionModel().getSelectedItem();
        int index = ProInvTbl.getSelectionModel().getSelectedIndex();
        
      
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void DeletePro(ActionEvent event) 
    {
         
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Deleting...");
        alert.setContentText("Continue?");

        Optional<ButtonType> answer = alert.showAndWait();

        if(answer.get() == ButtonType.OK) 
        {
            Product selectedItem = ProInvTbl.getSelectionModel().getSelectedItem();
            ProInvTbl.getItems().remove(selectedItem);
            ProInvTbl.refresh();
            //nov. 26 5:33//
        }
    } 

    @FXML
    private void ExitInv(ActionEvent event) 
    {
        Stage stage = (Stage) ExitInvButton.getScene().getWindow();
        stage.close();
    }
    
}
