package Client.Objets.Misc;


import Client.Objets.Objects;

public class Misc extends Objects {


    public Misc(String name, int souls, int poids) {
        super("Misc", name, poids, 0, 1, souls);
    }

    // Getters

    public String getClasse() {
        return this.getClasse();
    }

    public String getNom() {
        return this.getNoms();
    }

    public int getPoid() {
        return this.getPoids();
    }

    public int getValeur() {
        return this.getValeur();
    }

    // Setters

    public void setClasse(String classe) {
        this.setClasse(classe);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoids(int poids) {
        this.setPoids(poids);
    }

    public void setValeur(int valeur) {
        this.setValeur(valeur);
    }
}
