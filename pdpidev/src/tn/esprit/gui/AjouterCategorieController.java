/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tn.esprit.entities.categorie_produit;
import tn.esprit.services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField typeID;
    @FXML
    private TextField referenceID;
    @FXML
    private Button buttonID;
   @FXML
    private TableView<categorie_produit> tabcat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    @SuppressWarnings("empty-statement")
    private void ajouterCat(ActionEvent event) {
        
         String type=typeID.getText();
         String reference=referenceID.getText();
         
         
         //categorie_produit categorie = categoryCombo.getValue(); 
        categorie_produit cat=new categorie_produit(type,reference);
        
        CategorieService ps=new CategorieService();
        ps.ajouterCat(cat);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
        try {
            Parent root = loader.load();
            AfficherCategorieController ac = loader.getController();
           List<categorie_produit> categories = ps.afficherCat();
           if (!categories.isEmpty()) {
           int categoieID = categories.get(0).getId();
           ac.setCategoieID(ps.afficherCat().toString());
}
            referenceID.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    
    }
    
    
    void SupprimerCat(ActionEvent event) {
        CategorieService ps=new CategorieService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("Voulez vous vraiment supprimer cette categorie?");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent()&& result.get()==ButtonType.OK)
        {
            categorie_produit p = tabcat.getSelectionModel().getSelectedItem();
            ps.supprimerCat(p);
            
        }
       ps.afficherCat();
    }
    }
    

