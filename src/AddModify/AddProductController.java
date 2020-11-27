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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucytran
 */
public class AddProductController implements Initializable {

    @FXML
    private Label AddProLabel;
    @FXML
    private Label MinAddProLabel;
    @FXML
    private Label IDAddProLabel;
    @FXML
    private Label NameAddProLabel;
    @FXML
    private Label InvAddProLabel;
    @FXML
    private Label PriceAddProLabel;
    @FXML
    private Label MaxAddProLabel;
    @FXML
    private TextField IDAddProTB;
    @FXML
    private TextField NameAddProTB;
    @FXML
    private TextField InvAddProTB;
    @FXML
    private TextField PriceAddProTB;
    @FXML
    private TextField MaxAddProTB;
    @FXML
    private TextField MinAddProTB;
    @FXML
    private Button AddProButton;
    @FXML
    private TextField SearchAddProTB;
    @FXML
    private TableView<Part> ParInvTbl;
    @FXML
    private Button SaveProButton;
    @FXML
    private TableView<Part> AssociatedParTbl;
    @FXML
    private TableColumn<Part, Integer> AssociatedParID;
    @FXML
    private TableColumn<Part, String> AssociatedParName;
    @FXML
    private TableColumn<Part, Integer> AssociatedInvLvl;
    @FXML
    private TableColumn<Part, Double> AssociatedParPrice;
    @FXML
    private Button RemoveParButton;
    @FXML
    private Button CancelProButton;
    @FXML
    private Button ProGoButton;
    
    public ObservableList<Part> lookupPart = FXCollections.observableArrayList();
    public ObservableList<Part> addPart = FXCollections.observableArrayList();

    public Product product;
    @FXML
    private TableColumn<Part, Integer> PartID;
    @FXML
    private TableColumn<Part, String> ParName;
    @FXML
    private TableColumn<Part, Integer> PartInvLvl;
    @FXML
    private TableColumn<Part, Double> ParPrice;
    
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    // Initializes the controller class //
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    





    @FXML
    private void SearchAddProTB(ActionEvent event) 
    {
        
    }

    @FXML
    private void IDAddProTB(ActionEvent event) {
    }

    @FXML
    private void NameAddProTB(ActionEvent event) {
    }

    @FXML
    private void InvAddProTB(ActionEvent event) {
    }

    @FXML
    private void PriceAddProTB(ActionEvent event) {
    }

    @FXML
    private void MaxAddProTB(ActionEvent event) {
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

    @FXML
    private void RemoveAssociatedPart(ActionEvent event) 
    {
        Part part = ParInvTbl.getSelectionModel().getSelectedItem();
        allParts.add(part);
        ParInvTbl.refresh();
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
            root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            //return to scene 
        }
    }

    @FXML
    private void SearchPro(ActionEvent event) 
    {
        String newP = SearchAddProTB.getText();
        ParInvTbl.getSelectionModel().select(Part.findPart(newP));
    }

    @FXML
    private void AddPro(ActionEvent event) 
    {
        Part part = ParInvTbl.getSelectionModel().getSelectedItem();
        allParts.add(part);
        AssociatedParTbl.refresh();
        
        
    }

    @FXML
    private void MinAddPro(ActionEvent event) {
    }

    
}
