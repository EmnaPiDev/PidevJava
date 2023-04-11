/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import tn.esprit.entities.categorie_produit;
import tn.esprit.entities.produit;


import tn.esprit.services.ProduitService;


/**
 *
 * @author Admin
 */
public class Pdpidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       categorie_produit c= new categorie_produit(); 
       c.setId(7);
       
       produit p = new produit(75,"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa");
       ProduitService ps = new ProduitService();
        ps.ajouter(p);
             // ps.supprimer(p);
         
         //categorie_produit cat = new categorie_produit(14,"test","test");
         
         
         //CategorieService c = new CategorieService();
         //System.out.println( c.afficherCat());
         //c.supprimerCat(cat);
         //c.ajouterCat(cat);
    }
    
}
