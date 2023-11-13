package Domain;

import java.util.ArrayList;
import java.util.UUID;

/**
 * (Aggregate)
 * Représente un panier
 * */
public class Panier {

    private final UUID id;
    private final ArrayList<LigneDeCommande> lignesDeCommande;
    private boolean isValide;


    public Panier(){
        this.id = UUID.randomUUID();
        this.lignesDeCommande = new ArrayList<>();
    }

    /**
     * Constructeur de panier
     * @param id Identifiant du panier
     * */
    public Panier(UUID id) {
        this.id = id;
        this.lignesDeCommande = new ArrayList<>();
    }

    public ArrayList<LigneDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public UUID getId() {
        return id;
    }

    public void getPrix(){
        int prix = 0;
        for (LigneDeCommande ligneDeCommande : lignesDeCommande) {
            prix += ligneDeCommande.getPrix();
        }
        System.out.println("Prix total : " + prix);
    }

    private void addLigneDeCommande(LigneDeCommande ligneDeCommande){
       for (LigneDeCommande ligneDeCommandeExistante : lignesDeCommande) {
              if (ligneDeCommandeExistante.getReference().equals(ligneDeCommande.getReference())) {
                System.out.println("Référence déjà présente, ajout de la quantité");
                ligneDeCommandeExistante.setQuantite(ligneDeCommandeExistante.getQuantite() + ligneDeCommande.getQuantite());
                return;
              }
       }
       lignesDeCommande.add(ligneDeCommande);
    }

    public void addReference(Reference reference, int quantite){
        if (isValide){
            throw new IllegalStateException("Panier validé, impossible d'ajouter une référence");
        }
        LigneDeCommande ligneDeCommande = new LigneDeCommande(reference, quantite);
        addLigneDeCommande(ligneDeCommande);
    }

    public void removeReference(Reference reference){
        if (isValide) {
           throw new IllegalStateException("Panier validé, impossible de supprimer une référence");
        }
        lignesDeCommande.removeIf(ligneDeCommande -> ligneDeCommande.getReference().equals(reference));
    }

    public void valider(){
        if (isValide) {
            throw new IllegalStateException("Panier déjà validé");
        }
        isValide = true;
    }
}
