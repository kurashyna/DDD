package Domain;

import java.util.UUID;

/**
 * (Interface Repository)
 * Représente une interface Entrepôt de panier
 * */
public interface IPanierRepository {
    void addPanier(Panier panier);

    void updatePanier(Panier panier);

    void removePanier(Panier panier);

    Panier getPanierById(UUID id);
}