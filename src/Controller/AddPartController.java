/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import InventorySystem.Inventory;
import InventorySystem.Part;
import InventorySystem.InHouse;
import InventorySystem.Outsourced;
import java.io.IOException;
import static java.lang.Integer.max;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * Methods for adding a part.
 * This class is the controller for adding a part.
 * This method is used to add a new part to the inventory.
 * This method allows a new part to be added while supplying the selection for the part to be InHouse or Outsourced.
 * Once data is provided by user, the data can be saved and displayed in the Inventory Management System window on the Part table.
 */
public class AddPartController implements Initializable
{

    @FXML
    private Label CompNameLabel;
    @FXML
    private TextField AddPartIDTB;
    @FXML
    private TextField CompNameTB;
    @FXML
    private Button CancelButton;
    @FXML
    private Button SaveButton;
    @FXML
    private RadioButton InHouseRadioButton;
    @FXML
    private RadioButton OutsourcedRadioButton;
    @FXML
    private TextField AddPartNameTB;
    @FXML
    private TextField AddPartMinTB;
    @FXML
    private TextField AddPartMaxTB;
    @FXML
    private TextField AddPartInvTB;
    @FXML
    private TextField AddPartPriceTB;
   
    private boolean Outsourced = false;
   
    Stage stage;
    Parent scene;
    Part part;
    
    ToggleGroup toggleGroup = new ToggleGroup();
   
    
    /**
     * This list contains all parts from the Inventory.
     * The contains all parts from the Inventory that can be accessed and displayed.
     * Items that are parts for the inventory can be added or removed from this list.
     */
    public ObservableList<Part> allParts = FXCollections.observableArrayList();
   
    
    
    
    /**
     * This method initializes the controller for adding a part.
     * The method disables the ID text field for adding a part to prevent modifications.
     * @param url N/A
     * @param rb N/A
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         AddPartIDTB.setDisable(true);
    }
    
    
    /**
     * This method is to set a part.
     * This method is used to set a part to the inventory.
     * @param part This is the part that will be added
     */
    public void setPart(Part part)
    {
        this.part = part;   
    }


    @FXML
    private void CompNameTB(ActionEvent event) {
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
    private void SavePart(ActionEvent event) throws IOException 
    {  
        int parID = 0;
        for (Part part : Inventory.getAllParts())
        {
            if(part.getParID() > parID)
            {    
                parID = part.getParID();
            }
        }
      
        AddPartIDTB.setText(String.valueOf(++parID)); //Generate ID
        String name = AddPartNameTB.getText();
        int inv = Integer.parseInt(AddPartInvTB.getText());
        double price = Double.parseDouble(AddPartPriceTB.getText());
        int max = Integer.parseInt(AddPartMaxTB.getText());
        int min = Integer.parseInt(AddPartMinTB.getText());
        
        boolean Outsourced;
        boolean InHouse;
        

        if(OutsourcedRadioButton.isSelected())
        {
            Outsourced = true;
            String compName = CompNameTB.getText();
            Outsourced part = new Outsourced(parID, name, price, inv, min, max, compName);
            
            Inventory.addPart(part);
        }
        else
        {
            Outsourced = false;
        }
        
        
        if(InHouseRadioButton.isSelected())
        {
            InHouse = true;
            int machineID = Integer.parseInt(CompNameTB.getText());
            InHouse part = new InHouse(parID, name, price, inv, min, max, machineID);
                    
                   
            Inventory.addPart(part);     //Update new Part
        }
        else
        { 
            InHouse = false;
        }
       
      
        if (min > max)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Minimum Value Cannot Exceed Maximum Value... Please Try Again.");
               
            Optional<ButtonType> answer = alert.showAndWait();
            if(answer.get() == ButtonType.OK)
                {
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("InventorySystem.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                   
                   // return to Main  
                }
            }
        else if (inv > max || inv < min)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Inventory Value Entered Must Be Between Values For Min & Max... Please Try Again.");
            
            Optional<ButtonType> answer = alert.showAndWait();
            if(answer.get() == ButtonType.OK)
            {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("InventorySystem.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
                   
                   // return to Main 
            }
            return;
        }
        //Main Stage
        
        
        Parent root = FXMLLoader.load(getClass().getResource("InventorySystem.fxml"));
        Scene scene = new Scene(root);
           
           
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void InHouse(ActionEvent event) 
    {
        Outsourced = false;
        InHouseRadioButton.setToggleGroup(toggleGroup);
        CompNameLabel.setText("Machine ID");
    }

    @FXML
    private void Outsourced(ActionEvent event) 
    {
        Outsourced = true;
        OutsourcedRadioButton.setToggleGroup(toggleGroup);
        CompNameLabel.setText("CompanyName");
    }

    @FXML
    private void AddPartID(ActionEvent event) 
    {
    }

    @FXML
    private void AddPartName(ActionEvent event) {
    }

    @FXML
    private void AddPartMin(ActionEvent event) 
    {
    }

    @FXML
    private void AddPartMax(ActionEvent event) {
    }

    @FXML
    private void AddPartInv(ActionEvent event) {
    }

    @FXML
    private void AddPartPrice(ActionEvent event) {
    }

}
