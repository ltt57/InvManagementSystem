/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddModify;

import InvMngmSyst.InHouse;
import InvMngmSyst.Inventory;
import InvMngmSyst.Outsourced;
import InvMngmSyst.Part;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucytran
 */
public class AddPartController implements Initializable {

    @FXML
    private Label IDAddParLabel;
    @FXML
    private Label NameAddParLabel;
    @FXML
    private Label InvAddParLabel;
    @FXML
    private Label MaxAddParLabel;
    @FXML
    private Label CompNamAddParLabel;
    @FXML
    private Label AddPartLabel;
    @FXML
    private Label MinAddLabel;
 
    private boolean Outsourced = true;
    @FXML
    private VBox AddParLabelVBox;
    @FXML
    private VBox AddParTextVBox;
    @FXML
    private TextField IDAddParTB;
    @FXML
    private TextField NameAddParTB;
    @FXML
    private TextField MinAddParTB;
    @FXML
    private TextField InvAddParTB;
    @FXML
    private TextField PriceAddParTB;
    @FXML
    private TextField MaxAddParTB;
    @FXML
    private TextField CompNamAddParTB;
    @FXML
    private RadioButton InHouseRadioButton;
    @FXML
    private RadioButton OutsourcedRadioButton;
    @FXML
    private Button SaveParButton;
    @FXML
    private Button CancelParButton;
    @FXML
    private Label PriceAddParLabel;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }

   


    @FXML
    private void CompNamAddParTB(ActionEvent event) 
    {
        
    
    }

    @FXML
    private void InHouse(ActionEvent event) 
    {
        Outsourced = false;
        CompNamAddParLabel.setText("Machine ID");
        CompNamAddParTB.setPromptText("Machine ID");
    }

    @FXML
    private void Outsourced(ActionEvent event) 
    {
        Outsourced = true;
        CompNamAddParLabel.setText("Company Name");
        CompNamAddParTB.setPromptText("Company Name");
    }

    @FXML
    private void SavePar(ActionEvent event) throws IOException
    {
       int newID = 0;
       for(Part part : Inventory.getAllParts())
       {
           if(part.getParID() > newID)
               
               newID = part.getParID();
               
       }
        
        IDAddParTB.setText(String.valueOf(++newID));
        String name = NameAddParTB.getText();
        double price = Double.parseDouble(PriceAddParTB.getText());
        int inv = Integer.parseInt(InvAddParTB.getText());
        int max = Integer.parseInt(MaxAddParTB.getText());
        int min = Integer.parseInt(MinAddParTB.getText());
        
        try
        {
            if (min > max)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("PROBLEM!");
                alert.setHeaderText(null);
                alert.setContentText("Minimum Value Cannot Exceed Maximum Value... Please Try Again.");
               
                Optional<ButtonType> answer = alert.showAndWait();
                if(answer.get() == ButtonType.OK)
                {
                    returnMainView(event);
                }
               
            }
            else if (inv > max || inv < min)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setHeaderText(null);
                alert.setContentText("Value Entered Does Not Exist In Inventory... Please Try Again.");
                
                Optional<ButtonType> answer = alert.showAndWait();
                if(answer.get() == ButtonType.OK)
                {
                    returnMainView(event);
                }
            }
            else
            {
                if(InHouseRadioButton.isSelected())
                {
                    int machineID = Integer.parseInt(CompNamAddParTB.getText());
                    InHouse addPart = new InHouse(newID, name, price, inv, min, max, machineID);
                    
                    Inventory.addPart(addPart);
                }
                if(OutsourcedRadioButton.isSelected())
                {
                    String compName = CompNamAddParTB.getText();
                    Outsourced addPart = new Outsourced(newID, name, price, inv, min, max, compName);
                
                    Inventory.addPart(addPart);
                }
                
                returnMainView(event);
            
                /*
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("Main.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();
                */
            }
            
        }
        catch (Exception e)
        {
            System.out.println("ERROR!");
        }
    }
    

    private void CancelPar(ActionEvent event) throws IOException 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Canceling...");
        alert.setHeaderText(null);
        alert.setContentText("Continue?");
        
        Optional<ButtonType> answer = alert.showAndWait();
       
        if(answer.get() == ButtonType.OK)
        {
            returnMainView(event);
        }
        else
        {
            System.out.println("Canceled.");
        }
    }
    
    private void returnMainView(ActionEvent event) throws IOException
    {
        Stage stage; //THIS ONE!! Nov. 25 8:32
        Parent root;
        if(event.getSource() == CancelParButton)
        {
            stage = (Stage) CancelParButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        }
        else
        {
            stage = (Stage) SaveParButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
      
}


