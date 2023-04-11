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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.categorie_produit;

import tn.esprit.entities.produit;
import tn.esprit.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<produit> tvProduct;
    @FXML
    private TableColumn<produit, Integer> tfID;
    @FXML
    private TableColumn<produit, String> txfNom;
    @FXML
    private TableColumn<produit, String> txfRef;
    @FXML
    private TableColumn<produit, String> tfPrix;
    @FXML
    private TableColumn<produit, String> tfColor;
    @FXML
    private TableColumn<produit, String> tfPoid;
    @FXML
    private TableColumn<produit, String> txDesc;
    @FXML
    private TextField IDproduct;
    @FXML
    private TextField NOMproduct;
    @FXML
    private TextField REFproduct;
    @FXML
    private TextField PRIXproduct;
    @FXML
    private TextField POIDproduct;
    @FXML
    private TextField COLORproduct;
    @FXML
    private TextField DESCproduct;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelet;
    @FXML
    private Button categorie;

   
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProduct();
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
 public ObservableList<produit> getProduitList(){
               ObservableList<produit> prod_liste = FXCollections.observableArrayList();
               Connection conn =getConnection();
               String query ="SELECT * FROM produit" ;
               Statement st;
               ResultSet rs;
               
         try {
             
             st = conn.createStatement();
             rs = st.executeQuery(query);
             //Object categorieObj = rs.getObject("categorie_id");

           //categorie_produit categorie = (categorie_produit) categorieObj;


             produit prod;
             while (rs.next()){
             prod=new produit(rs.getInt("id"),rs.getString("nom"),rs.getString("reference"),rs.getString("prix"),rs.getString("couleur"),rs.getString("poids"),rs.getString("description")
                     //,categorie
             );
             prod_liste.add(prod);
                     }
             }catch(Exception ex){
                 ex.printStackTrace();
             }
        return prod_liste ;
             
           };
           
       public void showProduct(){
           ObservableList<produit> list =getProduitList();
           tfID.setCellValueFactory(new PropertyValueFactory<produit,Integer>("id"));
           txfNom.setCellValueFactory(new PropertyValueFactory<produit,String>("nom"));
           txfRef.setCellValueFactory(new PropertyValueFactory<produit,String>("reference"));
           tfColor.setCellValueFactory(new PropertyValueFactory<produit,String>("couleur"));
           tfPoid.setCellValueFactory(new PropertyValueFactory<produit,String>("poids"));
           tfPrix.setCellValueFactory(new PropertyValueFactory<produit,String>("prix"));
           txDesc.setCellValueFactory(new PropertyValueFactory<produit,String>("description"));
           //tabimage.setCellValueFactory(new PropertyValueFactory<produit,String>("image"));
           //tabCat.setCellValueFactory(new PropertyValueFactory<categorie_produit,String>("categorie_id"));
           tvProduct.setItems(list);
       }
    
       private void insertRecord(){
       String query;
       String sql="insert into produit(id,nom,reference,prix,couleur,poids,description,image) values (?,?,?,?,?,?,?,?)";
       
        query = "INSERT INTO produit VALUES (" + IDproduct.getText() + ",'" + NOMproduct.getText() + "','" + REFproduct.getText()+"','" +PRIXproduct.getText()+ "','"+COLORproduct.getText()+"','" +POIDproduct.getText()+ "','" + DESCproduct.getText()+
                //txCat.getText()+
                "')";
      
       executeQuery(query);
       showProduct();
       }
     private void executeQuery(String query) {
       Connection conn =getConnection();
       Statement st;
       try {st=conn.createStatement();
           st.executeUpdate(query);
           }catch (Exception ex){ 
           ex.printStackTrace();}
    }
    @FXML
    private void handleButtonAction(ActionEvent event){
        System.out.println("You Clicked ");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String id = IDproduct.getText();
        String ref = REFproduct.getText();
        String poids = POIDproduct.getText();
         String couleur = COLORproduct.getText();
          String prix = PRIXproduct.getText();
        if(event.getSource() == btninsert)
        {
            insertRecord();
        } 
                if(event.getSource() == btnupdate){
            if (id.isEmpty()| ref.isEmpty() | poids.isEmpty()| couleur.isEmpty()| prix.isEmpty()){
            alert.setTitle("Produit");
            alert.setContentText("Vous ne pouvez pas envoyer ce modele de produit avec une champ vide!!");
            alert.show();
         }else{
           updateRecord(); 
        }}
        
         if (event.getSource()==btndelet){
        delete();
        }
    }
    private void updateRecord(){
String query ="UPDATE produit SET id='"+IDproduct.getText()+ "',nom ='" + NOMproduct.getText()+"',reference ='" + REFproduct.getText()+ "',prix ='" + PRIXproduct.getText()+ "',couleur ='" + COLORproduct.getText()+ "',poids ='" + POIDproduct.getText()+ "',description ='" + DESCproduct.getText()+ "' WHERE id =" + IDproduct.getText();
    executeQuery(query);
    showProduct();
    }
    
    private void delete(){
    String query ="DELETE FROM produit WHERE id="+IDproduct.getText()+"";    
    executeQuery(query);
    showProduct();
    }

    @FXML
    private void AjoutCategorieRoot(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
        try {
           Parent root = loader.load();
            AfficherCategorieController ac = loader.getController();
            //ac.setL_nom(nom);
            //ac.setL_prenom(prenom);
            
            
            //ac.setProduitID(ps.afficher().toString());
            DESCproduct.getScene().setRoot(root); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    }
    
 
    

