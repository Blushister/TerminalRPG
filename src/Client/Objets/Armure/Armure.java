package Client.Objets.Armure;

import Client.Objets.Objects;

public class Armure extends Objects {
    int defense;
    int poids;
    int valeur;

    public Armure(String nom, int defense, int poids, int valeur, int quantite) {
        super("Armure", nom, poids, valeur, quantite, 0);
        this.defense = defense;
        this.poids = poids;
        this.valeur = valeur;
    }

    // Getters

    public int getDefense() {
        return this.defense;
    }

    public int getPoids() {
        return this.poids;
    }

    public int getValeur() {
        return this.valeur;
    }

    // Setters

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setNoms(String nom_armure) {
        this.nom = nom_armure;
    }
}
