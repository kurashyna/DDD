package Infra;

import Domain.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;
public class PanierRepositoryInMemory implements IPanierRepository{

    private final Map<UUID,Panier> paniers;

    public PanierRepositoryInMemory(){
        this.paniers = new HashMap<UUID, Panier>();
    }

    @Override
    public void addPanier(Panier panier) {
        if (paniers.containsKey(panier.getId())){
            throw new IllegalStateException("Panier déjà existant");
        } else {
            paniers.put(panier.getId(),panier);
        }
    }

    @Override
    public void updatePanier(Panier panier) {
        if (paniers.containsKey(panier.getId())){
            paniers.put(panier.getId(),panier);
        } else {
            throw new IllegalStateException("Panier non existant");
        }
    }

    @Override
    public void removePanier(Panier panier) {
        if (paniers.containsKey(panier.getId())){
            paniers.remove(panier.getId());
        } else {
            throw new IllegalStateException("Panier non existant");
        }
    }

    @Override
    public Panier getPanierById(UUID id) {
        return paniers.get(id);
    }
}
