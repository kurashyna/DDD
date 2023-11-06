package Domain;

import java.util.Objects;
import java.util.regex.*;
/**
 * (Value Object)
 * Représente une référence de produit
 * */
public class Reference {
    private final String ref;
    private final String nom;
    private final String description;
    private final int prix;

    /**
     * Constructeur de référence
     * @param ref Référence du produit
     * @param nom Nom du produit
     * @param description Description du produit
     * @param prix Prix du produit
     * */
    public Reference(String ref, String nom, String description, int prix) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]{0,20}"); // 20 char max alphanumérique
        Matcher m = p.matcher(ref);
        if (!m.matches()) {
            throw new IllegalArgumentException("Ref doit contenir 20 char max");
        }
        this.ref = ref;

        p = Pattern.compile("\\w{0,20}"); // 20 char max
        m = p.matcher(nom);
        if (!m.matches()) {
            throw new IllegalArgumentException("Nom doit contenir 20 char max");
        }
        this.nom = nom;

        p = Pattern.compile("\\w{0,200}"); // 200 char max
        m = p.matcher(description);
        if (!m.matches()) {
            throw new IllegalArgumentException("Description doit contenir 200 char max");
        }
        this.description = description;

        p = Pattern.compile("\\d*"); // Entier positif
        m = p.matcher(String.valueOf(prix));
        if (!m.matches()) {
            throw new IllegalArgumentException("Prix doit être un entier positif");
        }
        this.prix = prix;
    }

    public String getRef() {
        return ref;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getPrix() {
        return prix;
    }


}
