package Domain;

import java.util.Objects;

/**
 * (Entity)
 * Représente une ligne de commande
 * */
public class LigneDeCommande {
    private final Reference reference;
    private int quantite;

    /**
     * Constructeur de ligne de commande
     * @param reference Référence du produit
     * @param quantite Quantité du produit
     * */
    public LigneDeCommande(Reference reference, int quantite) {
        this.reference = reference;
        this.quantite = quantite;
    }

    public Reference getReference() {
        return reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return reference.getPrix() * quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LigneDeCommande that = (LigneDeCommande) o;
        return Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
