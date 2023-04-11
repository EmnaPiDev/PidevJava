/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import tn.esprit.entities.categorie_produit;
import tn.esprit.entities.produit;
import tn.esprit.services.ProduitService;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterProduitController implements Initializable {
      private ProduitService productService;
    @FXML
    private TextField referenceID;
    @FXML
    private TextField nomID;
    @FXML
    private TextField prixID;
    @FXML
    private TextField couleurID;
    @FXML
    private TextField poidID;
    @FXML
    private TextField descriptionID;
    @FXML
   // private ComboBox<categorie_produit> categoryCombo;
    //@FXML
    private TextField imageID;
    @FXML
    private Button ajouterID;
    @FXML
    private TextField ID;
    
       //private ObservableList<categorie_produit> categories;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        //productService = new ProduitService();
        //categories = FXCollections.observableArrayList(productService.getAllCategories());
        //categoryCombo.setItems(categories);
        //if (categories != null && !categories.isEmpty()) {
        //List<Integer> categoryIds = categories.stream().map((categorie_produit) -> categorie_produit.getId()).collect(Collectors.toList());
        //categoryCombo.setItems(FXCollections.observableArrayList(categoryIds));
    //} else {
      //  System.out.println("No categories found");
    //}
        
    }
   

    
    @FXML
    private void AddProduit(ActionEvent event) {
        int id=Integer.valueOf(ID.getText());
         String nom=nomID.getText();
         String reference=referenceID.getText();
         String prix=prixID.getText();
         String image=imageID.getText();
         String description=descriptionID.getText();
         String couleur=couleurID.getText();
         String poid=poidID.getText();
         
         //categorie_produit categorie = categoryCombo.getValue(); 
        
         
        produit prod=new produit(id,nom,reference,prix,image,description,couleur,poid);
        
        ProduitService ps=new ProduitService();
        ps.ajouter(prod);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
        try {
           Parent root = loader.load();
            AfficherProduitController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            poidID.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    
   
    
}
