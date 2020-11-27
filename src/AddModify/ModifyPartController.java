/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddModify;

import InvMngmSyst.InHouse;
import InvMngmSyst.Inventory;
import static InvMngmSyst.Inventory.getAllParts;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucytran
 */
public class ModifyPartController implements Initializable {

    @FXML
    private Label ModifyParLabel;
    @FXML
    private RadioButton InHouseRadioButton;
    @FXML
    private RadioButton OutsourcedRadioButton;
    @FXML
    private VBox ModParLabelVBox;
    @FXML
    private Label IDModParLabel;
    @FXML
    private Label NameModParLabel;
    @FXML
    private Label InvModParLabel;
    @FXML
    private Label PriceModParLabel;
    @FXML
    private Label MaxModParLabel;
    @FXML
    private Label CompNamModParLabel;
    @FXML
    private Label MinModParLabel;
    @FXML
    private VBox ModParTextVBox;
    @FXML
    private TextField NameModParTB;
    @FXML
    private TextField InvModParTB;
    @FXML
    private TextField PriceModParTB;
    @FXML
    private TextField MaxModParTB;
    @FXML
    private TextField CompNamModParTB;
    @FXML
    private Button SaveParButton;
    @FXML
    private Button CancelParButton;
    @FXML
    private TextField MinModParTB;

    private boolean Outsourced;
   
    
    
    @FXML
    private TextField IDModParTB;
    
    Part findPart;
    int findIndex;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
       

        
     
          
    }    

    @FXML
    private void InHouseRadioButton(ActionEvent event) 
    {
        Outsourced = false;
        OutsourcedRadioButton.setSelected(false);
        CompNamModParLabel.setText("Machine ID");
        CompNamModParTB.setPromptText("Machine ID");
        
    }

    @FXML
    private void OutsourcedRadioButton(ActionEvent event) 
    {
        Outsourced = true;
        InHouseRadioButton.setSelected(false);
        CompNamModParLabel.setText("Company Name");
        CompNamModParTB.setPromptText("Machine ID");
        
    }

    @FXML
    private void IDModParTB(ActionEvent event) {
    }

    @FXML
    private void NameModParTB(ActionEvent event) {
    }

    @FXML
    private void InvModParTB(ActionEvent event) {
    }

    @FXML
    private void PriceModParTB(ActionEvent event) {
    }

    @FXML
    private void MaxModParTB(ActionEvent event) {
    }

    @FXML
    private void CompNamModParTB(ActionEvent event) {
    }

    @FXML
    private void SavePar(ActionEvent event) throws IOException 
    {
        int newID = findPart.getParID();
        for(Part part : Inventory.getAllParts())
        {
            if(part.getParID() > newID)
                
                newID = part.getParID();
        }
        
        IDModParTB.setText(String.valueOf(++newID));
        String name = NameModParTB.getText();
        double price = Double.parseDouble(PriceModParTB.getText());
        int inv = Integer.parseInt(InvModParTB.getText());
        int max = Integer.parseInt(MaxModParTB.getText());
        int min = Integer.parseInt(MinModParTB.getText());

                if(InHouseRadioButton.isSelected())
                {
                    int machineID = Integer.parseInt(CompNamModParTB.getText());
                    InHouse inHousePar = new InHouse(newID, name, price, inv, min, max, machineID);
                    
                    Inventory.getAllParts().set(findIndex, inHousePar);
                }
                if(OutsourcedRadioButton.isSelected())
                {
                    String compName = CompNamModParTB.getText();
                    Outsourced outsourcedPar = new Outsourced(newID, name, price, inv, min, max, compName);
                
                    Inventory.getAllParts().set(findIndex, outsourcedPar);
                }

                returnMainView(event);
    }
    

    @FXML
    private void CancelPar(ActionEvent event) throws IOException 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Canceling...");
        alert.setHeaderText(null);
        alert.setContentText("Continue?");
        
        Optional<ButtonType> answer = alert.showAndWait();
       
        if(answer.get() == ButtonType.OK)
        {
            returnMainView(event);
        }
        
    }

    @FXML
    private void MinModParTB(ActionEvent event) {
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
            root = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } 
   
    public void setPart(Part part, int index)
    {
     
        
        findPart = part;
        findIndex = index;
   
        
        NameModParTB.setText(findPart.getName());
        InvModParTB.setText(Integer.toString(findPart.getInv()));
        PriceModParTB.setText(Double.toString(findPart.getPrice()));
        MaxModParTB.setText(Integer.toString(findPart.getMax()));
        MinModParTB.setText(Integer.toString(findPart.getMin()));

        if(part instanceof InHouse)
        {
          InHouseRadioButton.setSelected(true);
          CompNamModParLabel.setText("Machine ID");
          CompNamModParTB.setText(Integer.toString(findPart.getInv()));
        }
        else
        {
            OutsourcedRadioButton.setSelected(true);
            CompNamModParLabel.setText("Company Name");
            CompNamModParTB.setText(((Outsourced) getAllParts().get(findIndex)).getCompName());
        }
         
    }
}

