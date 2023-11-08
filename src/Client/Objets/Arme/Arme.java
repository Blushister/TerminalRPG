package Client.Objets.Arme;

import Client.Objets.Objects;

public class Arme extends Objects {
    int degats;
    int poids;
    int valeur;

    public Arme(String nom, int degats, int poids, int valeur, int quantite) {
        super("Arme", nom, poids, valeur, quantite, 0);
        this.degats = degats;
    }

    // Getters

    public int getDegats() {
        return this.degats;
    }

    public int getPoids() {
        return this.poids;
    }

    public int getValeur() {
        return this.valeur;
    }
    // Recuperer nom de l'arme
    public String getNomArme() {
        return this.nom;
    }

    // Setters

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}

