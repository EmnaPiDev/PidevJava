/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface CategorieInterface<C> {
    public void ajouterCat(C c);
    public void modifierCat(C c);
    public List<C> afficherCat();
    public void supprimerCat(C c);
}
