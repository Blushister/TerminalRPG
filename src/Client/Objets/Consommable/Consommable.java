package Client.Objets.Consommable;

import Client.Objets.Objects;


public class Consommable extends Objects {
    public int hp;
    public int mp;
    public int endurance;
    public int force;
    public int intelligence;
    public int agilite;
    public Object potionVie;


    public Consommable(String nom, int souls, int poids, int hp, int mp, int endurance, int force, int intelligence, int agilite) {
        super("Consommable", nom, poids, 25, 1, souls);
        this.hp = hp;
        this.mp = mp;
        this.endurance = endurance;
        this.force = force;
        this.intelligence = intelligence;
        this.agilite = agilite;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMp() {
        return this.mp;
    }

    public int getEndurance() {
        return this.endurance;
    }

    public int getForce() {
        return this.force;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public int getAgilite() {
        return this.agilite;
    }
}

