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
public class CategorieService implements CategorieInterface<categorie_produit> {
    Connection cnx;
     public CategorieService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterCat(categorie_produit c) {
        String sql1="insert into categorie_produit(type,reference) values (?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql1);
            
            ste.setString(1, c.getType());
            ste.setString(2, c.getReference());
            
            ste.executeUpdate();
            System.out.println("Catégorie Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    @Override
    public List<categorie_produit> afficherCat() {
       List<categorie_produit> categories = new ArrayList<>();
        String sql="select * from categorie_produit";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                categorie_produit c = new categorie_produit(rs.getInt("id"),rs.getString("type"),rs.getString("reference") );
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return categories;
    }

    @Override
    public void supprimerCat(categorie_produit c) {
        String sql ="delete from categorie_produit where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
       


    @Override
    public void modifierCat(categorie_produit c) {
        String query = "UPDATE categorie_produit SET type = '" + c.getType()+ "', reference ='" +"";;
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("categorie Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    
    }
    
}
