import Infra.*;
import Domain.*;

import java.util.UUID;
public class Main {
    public static void main(String[] args) {
        PanierRepositoryJSON panierRepositoryJSON = new PanierRepositoryJSON();
        Panier panier = new Panier();
        panier.addReference(new Reference("1234", "Pomme", "PommeRouge", 20), 2);
        panier.addReference(new Reference("1235", "Poire", "PoireJaune", 30), 3);
        UUID id = panier.getId();
        panierRepositoryJSON.addPanier(panier);
        Panier panier2 = panierRepositoryJSON.getPanierById(id);
        panier2.getLignesDeCommande().forEach(ligneDeCommande -> System.out.println(ligneDeCommande.getReference().getNom() + " - " + ligneDeCommande.getQuantite() + " - " + ligneDeCommande.getPrix()));
    }
}