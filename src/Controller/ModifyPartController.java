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
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
 * Methods for modifying a part.
 * This class is the controller for modifying a part.
 * This class provides implementations and methods for a selected part from the Inventory Management System window Part table.
 * The selected Part is then modifiable through this controller window. 
 * The class allows a part to be modified while supplying the selection for the part to be InHouse or Outsourced.
 * 
 */
public class ModifyPartController implements Initializable
{

    @FXML
    private Label CompNameLabel;
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
    private TextField ModPartInvTB;
    @FXML
    private TextField ModPartIDTB;
    @FXML
    private TextField ModPartNameTB;
    @FXML
    private TextField ModPartPriceTB;
    @FXML
    private TextField ModPartMinTB;
    @FXML
    private TextField ModPartMaxTB;
    
    private boolean Outsourced = false;
    
    Part part;
    Stage stage;
    Parent scene;
    
    ToggleGroup toggleGroup = new ToggleGroup();
    
    /**
     * This initializes the controller for modifying a part.
     * The method disables the ID text field for modifying a part to prevent modifications.
     * @param url N/A
     * @param rb N/A
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ModPartIDTB.setDisable(true);
    }
    
    /**
     * This method sets a part.
     * This method is used to set a part to the inventory.
     * @param part This is the part that is being modified
     */
    public void setPart(Part part)
    {
   
       this.part = part;
    }


    @FXML
    private void CompNameTB(ActionEvent event) 
    {
        
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
        ModPartIDTB.setText(String.valueOf(++parID));
        
        String name = ModPartNameTB.getText();
        int inv = Integer.parseInt(ModPartInvTB.getText());
        double price = Double.parseDouble(ModPartPriceTB.getText());
        int max = Integer.parseInt(ModPartMaxTB.getText());
        int min = Integer.parseInt(ModPartMinTB.getText());
        
        boolean Outsourced;
        boolean InHouse;
        
   
        if(OutsourcedRadioButton.isSelected())
        {
            Outsourced = true;
            String compName = CompNameTB.getText();
            Outsourced selectedPart = new Outsourced(parID, name, price, inv, min, max, compName);
        }
        else
        {
            Outsourced = false;
        }
        
        
        if(InHouseRadioButton.isSelected())
        {
            InHouse = true;
            int machineID = Integer.parseInt(CompNameTB.getText()); 
            InHouse selectedPart = new InHouse(parID, name, price, inv, min, max, machineID);
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
                   
                    //return to Main
     
                }
            }
        if (inv > max || inv < min)
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
        CompNameLabel.setText("Machine ID");
        InHouseRadioButton.setToggleGroup(toggleGroup);
    }

    @FXML
    private void Outsourced(ActionEvent event)
    {
        Outsourced = true;
        CompNameLabel.setText("Company Name");
        OutsourcedRadioButton.setToggleGroup(toggleGroup);
    }

    @FXML
    private void ModifyPartInv(ActionEvent event) {
    }

    @FXML
    private void ModPartID(ActionEvent event) {
    }

    @FXML
    private void ModPartName(ActionEvent event) {
    }

    @FXML
    private void ModPartPrice(ActionEvent event) {
    }

    @FXML
    private void ModPartMin(ActionEvent event) {
    }

    @FXML
    private void ModPartMax(ActionEvent event) {
    }

  
    
}
