/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;
import tn.esprit.entities.categorie_produit;

/**
 *
 * @author Admin
 */
public interface PoduitInterface<P> {
     public void ajouter(P p);
    public List<P> afficher();
    public void modifier(P p);
    public void supprimer(P p);
}
