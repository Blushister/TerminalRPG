package Client.Joueur;

import Client.Objets.Objects;

import java.util.ArrayList;

public class Joueur {

    protected String nom;
    protected int vie;

    protected int experience;
    protected ArrayList<Objects> Inventory = new ArrayList<Objects>();

    public Joueur(String nom, int vie, int experience) {
        this.nom = nom;
        this.vie = vie;
        this.experience = experience;
    }

    // Getters

    public String getNom() {
        return this.nom;
    }

    public int getVie() {
        return this.vie;
    }

    public ArrayList<Objects> getInventory() {
        return this.Inventory;
    }

    public int getExperience() {
        return this.experience;
    }

    // Setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setInventory(ArrayList<Objects> inventory) {
        this.Inventory = inventory;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

}
