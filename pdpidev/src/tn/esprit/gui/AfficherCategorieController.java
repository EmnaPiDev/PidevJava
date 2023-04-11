/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.categorie_produit;
import tn.esprit.services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AfficherCategorieController implements Initializable {

    private TextField categoieID;
    private TableView<categorie_produit> tabcat;
    @FXML
    private TableColumn<categorie_produit, String> tabreference;
    @FXML
    private TableColumn<categorie_produit, String> tabtype;
    @FXML
    private TableView<categorie_produit> tvCat;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelet;
    @FXML
    private TableColumn<categorie_produit, Integer> tabid;
    @FXML
    private TextField idtextfield;
    @FXML
    private TextField idtype;
    @FXML
    private TextField typeref;
    @FXML
    private Button btninsert;
    public TextField getCategoieID() {
        return categoieID;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCategorie();
    } 

    public void setCategoieID(String categoieID) {
    this.categoieID.setText(categoieID);
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
 public Connection getConnection(){
        Connection conn;
        try {
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdpidev","root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error"+ex.getMessage());
            return null;
        }
        
        
        
};
               public ObservableList<categorie_produit> getCategorieList(){
               ObservableList<categorie_produit> cat_liste = FXCollections.observableArrayList();
               Connection conn =getConnection();
               String query ="SELECT * FROM categorie_produit" ;
               Statement st;
               ResultSet rs;
         try {
             st = conn.createStatement();
             rs = st.executeQuery(query);
             categorie_produit categorie;
             while (rs.next()){
             categorie=new categorie_produit(rs.getInt("id"),rs.getString("type"),rs.getString("reference"));
             cat_liste.add(categorie);
                     }
             }catch(Exception ex){
                 ex.printStackTrace();
             }
        return cat_liste ;
             
           };
           
       public void showCategorie(){
           ObservableList<categorie_produit> list =getCategorieList();
           tabtype.setCellValueFactory(new PropertyValueFactory<categorie_produit,String>("type"));
           tabreference.setCellValueFactory(new PropertyValueFactory<categorie_produit,String>("reference"));
           tabid.setCellValueFactory(new PropertyValueFactory<categorie_produit,Integer>("id"));
           tvCat.setItems(list);
       }
    
       private void insertRecord(){
String query = "INSERT INTO categorie_produit VALUES (" + idtextfield.getText() + ",'" + idtype.getText() + "','" + typeref.getText() + "')";
       executeQuery(query);
       showCategorie();
       }

    private void executeQuery(String query) {
       Connection conn =getConnection();
       Statement st;
       try {
           st=conn.createStatement();
           st.executeUpdate(query);
           
       }catch (Exception ex){ 
           ex.printStackTrace();
       }
    }

    @FXML
    private void handleButtonAction(ActionEvent event){
        
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println("You Clicked ");
        String id = idtextfield.getText();
        String type = idtype.getText();
        String ref = typeref.getText();
        if(event.getSource() == btninsert){
         if (id.isEmpty()| type.isEmpty() | ref.isEmpty()){
            alert.setTitle("Categorie");
            alert.setContentText("Voun ne pouvez pas envoyer ce modele de categorie avec une champ vide!!");
            alert.show();
         }else{
         insertRecord();
         }
        
    }
        if(event.getSource() == btnupdate){
            if (id.isEmpty()| type.isEmpty() | ref.isEmpty()){
            alert.setTitle("Categorie");
            alert.setContentText("Voun ne pouvez pas envoyer ce modele de categorie avec une champ vide!!");
            alert.show();
         }else{
           updateRecord(); 
        }}
         if(event.getSource() == btndelet){
           delete(); 
        }
    
    
    
    
    }
    private void updateRecord(){
String query ="UPDATE categorie_produit SET type='"+idtype.getText()+ "',reference ='" + typeref.getText()+ "' WHERE id =" + idtextfield.getText();
    executeQuery(query);
    showCategorie();
    }
    
    private void delete(){
    String query ="DELETE FROM categorie_produit WHERE id="+idtextfield.getText()+"";    
    executeQuery(query);
    showCategorie();
    }
    

    
    
    
    
    
    

   // @FXML
    //private void handleButtonAction(MouseEvent event) {
       // categorie_produit cat = tvCat.getSelectionModel().getSelectedItem();
       //idtype.setText(cat.getType());
       //typeref.setText(cat.getReference());
       //idtextfield.setText(""+cat.getId());
    //}

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void ajoutRoot(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
        try {
           Parent root = loader.load();
            AfficherProduitController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            typeref.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    }
    
       
    

