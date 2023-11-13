package Infra;
import Domain.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.io.File;
import org.json.*;

public class PanierRepositoryJSON implements IPanierRepository{

    @Override
    public void addPanier(Panier panier) {
        try {
            FileWriter file = null;
            file = new FileWriter("data/" + panier.getId() + ".json");
            JSONObject jsonObject = new JSONObject(panier);
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}

    @Override
    public void updatePanier(Panier panier) {
        try{
            File file = new File("data/" + panier.getId() + ".json");
            if (file.exists()){
                FileWriter fileWriter = new FileWriter(file);
                JSONObject jsonObject = new JSONObject(panier);
                fileWriter.write(jsonObject.toString());
            } else {
                throw new IllegalStateException("Panier non existant");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removePanier(Panier panier) {
        try{
            File file = new File("data/" + panier.getId() + ".json");
            if (file.exists()){
                file.delete();
            } else {
                throw new IllegalStateException("Panier non existant");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Panier getPanierById(UUID id) {
       try{
            File file = new File("data/" + id + ".json");
            if (file.exists()){
                Scanner scanner = new Scanner(file);
                String json = scanner.nextLine();

                JSONObject jsonObject = new JSONObject(json);

                Panier panier = new Panier(id);

                JSONArray jsonLignesDeCommande = jsonObject.getJSONArray("lignesDeCommande");

                jsonLignesDeCommande.forEach(ligneDeCommande -> {
                    JSONObject jsonLigneDeCommande = (JSONObject) ligneDeCommande;
                    JSONObject jsonReference = jsonLigneDeCommande.getJSONObject("reference");
                    Reference reference = new Reference(jsonReference.getString("ref"), jsonReference.getString("nom"), jsonReference.getString("description"), jsonReference.getInt("prix"));
                    panier.addReference(reference, jsonLigneDeCommande.getInt("quantite"));
                });
                return panier;
            } else {
                throw new IllegalStateException("Panier non existant");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
