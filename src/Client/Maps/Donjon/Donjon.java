package Client.Maps.Donjon;

import Client.Joueur.Ami.Heros;
import Client.Joueur.Enemi.Vilain;
import Client.Objets.Arme.Arme;
import Client.Objets.Instancte;
import Client.Objets.Objects;

import java.util.ArrayList;
import Client.Maps.Donjon.Difficulty;

public class Donjon {

    // MAP SYMBOLS = D

    private int Etage = 1;
    private int Etagemax;
    Difficulty difficulty;
    private ArrayList<Objects> rewards = new ArrayList<>();
    // Si difficulty = EASY alors 1 monstre
    Vilain enemi1 = new Vilain("Gardien du donjon", 2, 20, 10, 1000);
    Vilain enemi2 = new Vilain("Minotaurus", 20, 2, 10, 1000);
    Vilain enemi3 = new Vilain("Golem", 20, 2, 10, 1000);
    Vilain enemi4 = new Vilain("Dragon", 20, 2, 10, 1000);
    Arme price = new Arme("Durandal", 100, 10, 100, 1);


    ArrayList<Vilain> listmechant = new ArrayList<>();
    Instancte instance = new Instancte();

    // Constructeur

    public Donjon(int etagemax, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.Etagemax = etagemax;
    }

    // Getters

    public int getEtage() {
        return Etage;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public ArrayList<Objects> getRewards() {
        return rewards;
    }

    // Setters

    public void setEtage(int etage) {
        Etage = etage;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    } // TODO : Ajouter le systeme de difficulté

    public void setRewards(ArrayList<Objects> rewards) {
        this.rewards = rewards;
    }

    // Methods

    public void addReward(Objects reward) {
        this.rewards.add(reward);
    }

    public void removeReward(Objects reward) {
        this.rewards.remove(reward);
    }

    public void InstanceDonjon(Heros heros) {
        this.difficulty = Difficulty.EASY;
        this.listmechant.add(enemi1);
        this.listmechant.add(enemi2);
        this.listmechant.add(enemi3);
        this.listmechant.add(enemi4);
        int i = 0;
        while (heros.vie > 0) {
            if (this.getEtage() == this.Etagemax) {
                System.out.println("Vous avez gagné le donjon");
                heros.addItem(this.price);
                break;
            }
            System.out.println("Vous entrez dans le donjon");
            System.out.println("Vous êtes au niveau " + this.Etage + " sur : " + this.Etagemax);
            instance.InstanceCombat(heros, this.listmechant.get(i));
            i++;
            this.Etage++;
        }
        if (heros.getVie() <= 0) {
            System.out.println("Vous avez perdu le donjon");
            heros.setVie(heros.getVieMax());
        }
    }

}
