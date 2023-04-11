/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.categorie_produit;
import tn.esprit.entities.produit;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author Admin
 */
public class ProduitService implements PoduitInterface<produit>{
 Connection cnx;
 private List<categorie_produit> categories;
     public ProduitService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(produit p) {
        System.out.println(p);
        String sql="insert into produit(id,nom,reference,prix,couleur,poids,description,image) values (?,?,?,?,?,?,?,?)";
        //categorie_id
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.setString(2, p.getNom());
            ste.setString(3, p.getReference());
            ste.setString(4, p.getPrix());
            ste.setString(5, p.getCouleur());
            ste.setString(6, p.getPoids());
            ste.setString(7, p.getDescription());
            ste.setString(8, p.getImage());
            //ste.setInt(9, p.getCategorie().getId());
            
            ste.executeUpdate();
            System.out.println("Produit Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    @Override
    public List<produit> afficher() {
        List<produit> produits = new ArrayList<>();
        String sql="select * from produit";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                produit p = new produit(rs.getInt("id"),rs.getString("nom"),rs.getString("reference"),
                        rs.getString("prix"), rs.getString("couleur"),rs.getString("poids"),rs.getString("description"),rs.getString("image") );
                produits.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return produits;
    }
     public List<categorie_produit> getAllCategories(){
          
    return categories;
    }
    
    

    @Override
    public void supprimer(produit p) {
String sql ="delete from produit where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }

    @Override
    public void modifier(produit p) {
   String query = "UPDATE produit SET nom = '" + p.getNom()+ "', prix = '" +
                p.getPrix()+ "', description = '" + p.getDescription() + "' WHERE id = " + p.getId()+ "";;    
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Produit Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    
    }
    
    
    
    
 
}
