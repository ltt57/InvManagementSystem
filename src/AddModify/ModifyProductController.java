/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddModify;

import InvMngmSyst.Inventory;
import static InvMngmSyst.Inventory.allParts;
import InvMngmSyst.Part;
import InvMngmSyst.Product;
import com.sun.tools.javac.Main;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucytran
 */
public class ModifyProductController implements Initializable {

    @FXML
    private Label ModProLabel;
    @FXML
    private Label MinModProLabel;
    @FXML
    private VBox ModProLabelVBox;
    @FXML
    private Label IDModProLabel;
    @FXML
    private Label NameModProLabel;
    @FXML
    private Label InvModProLabel;
    @FXML
    private Label PriceModProLabel;
    @FXML
    private Label MaxModProLabel;
    @FXML
    private TextField MinModProTB;
    @FXML
    private VBox ModProTBVBox;
    @FXML
    private TextField IDModProTB;
    @FXML
    private TextField NameModProTB;
    @FXML
    private TextField InvModProTB;
    @FXML
    private TextField PriceModProTB;
    @FXML
    private TextField MaxModProTB;
    @FXML
    private TableColumn<Part, Integer> AssociatedParID;
    @FXML
    private TableColumn<Part, String> AssociatedParName;
    @FXML
    private TableColumn<Part, Integer> AssociatedInvLvl;
    @FXML
    private TableColumn<Part, Double> AssociatedParPrice;
    @FXML
    private Button AddProButton;
    @FXML
    private Button RemoveParButton;
    @FXML
    private Button SaveProButton;
    @FXML
    private Button CancelProButton;
    @FXML
    private TextField SearchModProTB;
    @FXML
    private Button ProGoButton;
    @FXML
    private TableView<Part> ParInvTbl;
    @FXML
    private TableColumn<Part, Integer> ModParID;
    @FXML
    private TableColumn<Part, String> ModParName;
    @FXML
    private TableColumn<Part, Integer> ModInvLvl;
    @FXML
    private TableColumn<Part, Double> ModPrice;
    @FXML
    private TableView<Part> AssociatedParTbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MinModProTB(ActionEvent event) {
    }

    @FXML
    private void IDModProTB(ActionEvent event) {
    }

    @FXML
    private void NameModProTB(ActionEvent event) {
    }

    @FXML
    private void InvModProTB(ActionEvent event) {
    }

    @FXML
    private void PriceModProTB(ActionEvent event) {
    }

    @FXML
    private void MaxModProTB(ActionEvent event) {
    }



    @FXML
    private void AddPro(ActionEvent event) 
    {
        Part part = ParInvTbl.getSelectionModel().getSelectedItem();
        allParts.add(part);
        AssociatedParTbl.refresh();
    }

    @FXML
    private void RemoveAssociatedPart(ActionEvent event) 
    {
        Part part = AssociatedParTbl.getSelectionModel().getSelectedItem();
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting...");
        alert.setHeaderText(null);
        alert.setContentText("Continue?");
        
        Optional<ButtonType> answer = alert.showAndWait();
       
        if(answer.get() == ButtonType.OK)
        {
            allParts.remove(part);
            ParInvTbl.refresh();
        }
        else
        {
            //return to scene put here
        }
    }


    @FXML
    private void CancelPro(ActionEvent event) throws IOException 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Canceling...");
        alert.setHeaderText(null);
        alert.setContentText("Continue?");
        
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK)
        {
            Stage stage;
            Parent root;
            stage = (Stage) CancelProButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //return to scene 
        }
    }


    @FXML
    private void SearchPro(ActionEvent event) 
    {
        String newP = SearchModProTB.getText();
        ParInvTbl.getSelectionModel().select(Part.findPart(newP));
        
    }


    @FXML
    private void SavePro(ActionEvent event) 
    {
        int ID = 0;
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getProID() > ID)
                ID = product.getProID();
        }
        ParInvTbl.setItems(allParts); 
        ParInvTbl.refresh();
    }
    
}
