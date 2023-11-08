package Client.Joueur.Enemi;

import Client.Joueur.Ami.Heros;
import Client.Joueur.Joueur;
import Client.Objets.Misc.Misc;

public class Ennemi extends Joueur {
    public int vie = 10;
    public int atqmax = 10;
    public int xp = 100;
    public int souls = 20;
    private boolean vivant = true;
    Misc misc = new Misc("Detritus", 0, 1);
    public Misc loot = misc;

    public Ennemi(String nom, int vie, int experience, int atqmax, int xp, int souls) {
        super(nom, vie, experience);
        this.vie = vie;
        this.atqmax = atqmax;
        this.xp = xp;
        this.souls = souls;
    }

    // afficher les caractéristiques de l'ennemi

    public void afficherCaracteristiques() {
        System.out.println("Vie : " + this.vie);
        System.out.println("Attaque maximale : " + this.atqmax);
        System.out.println("XP : " + this.xp);
    }

    // Attaquer le héros

    public void attaquer(Heros heros) {
        int calc = (int) (this.atqmax * (0.5 * heros.lvl));
        int degats = (int) (Math.random() * calc);
        System.out.println("L'ennemi vous attaque et vous inflige " + degats + " points de dégâts !");
        heros.vie -= degats;
        if (heros.vie <= 0) {
            heros.heroMort();
        }
    }

    // Mourrir

    public void mourrir(Heros heros) {
        if (this.vivant) {
            System.out.println("L'ennemi est mort !");
            heros.souls += this.souls;
            System.out.println("Vous avez gagné " + this.souls + " souls !");
            heros.inventory.add(this.loot);
            System.out.println("Vous avez gagné " + this.loot.getNom() + " !");
            heros.gainXp(this.xp);
            this.vivant = false;
        }
    }

    // Getters et Setters

    public boolean getVivant() {
        return this.vivant;
    }

    public void setVivant(boolean vivant) {
        this.vivant = vivant;
    }
}

