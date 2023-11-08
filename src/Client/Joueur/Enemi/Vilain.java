package Client.Joueur.Enemi;
import Client.Joueur.Ami.Heros;
import Client.Joueur.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Vilain extends Joueur {
    int dmg;
    int def;
    private boolean vivant = true;

    public Vilain(String nom, int vie, int dmg, int def, int exp) {
        super(nom, vie, exp);
        this.dmg = dmg;
        this.def = def;
    }

    // Getters

    public String getNom() {
        return this.nom;
    }

    public int getVie() {
        return this.vie;
    }

    public int getDmg() {
        return this.dmg;
    }

    public int getDef() {
        return this.def;
    }

    public boolean getVivant() {
        return this.vivant;
    }

    // Setters

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setVivant(boolean vivant) {
        this.vivant = vivant;
    }

    public void attaquer(Heros heros) {
        heros.setVie(heros.getVie() - this.dmg);
    }

    public void mourrir(Heros heros) {
        this.vivant = false;
        heros.gainXp((int) (this.experience * heros.lvl * 0.5));

    }

    public void changename(String name) {
        this.setNom(name);
    }

    public static String getRandomName(ArrayList<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public void aleatoireMonster() {
        ArrayList<String> nom = new ArrayList<>();
        nom.add("Gobelin");
        nom.add("Slime");
        nom.add("Orc");
        nom.add("Troll");
        String randomName = getRandomName(nom);
        this.changename(randomName);

    }

    public void updateMonster(Heros heros) {
        this.aleatoireMonster();
        if (Objects.equals(this.getNom(), "Gobelin")) {
            this.setVie((int) (50 * heros.lvl * 0.5));
            this.setDmg(8);
            this.setExperience(25);
        } else if (Objects.equals(this.getNom(), "Slime")) {
            this.setVie((int) (25 * heros.lvl * 0.5));
            this.setDmg(6);
            this.setExperience(12);
        } else if (Objects.equals(this.getNom(), "Orc")) {
            this.setVie((int) (120 * heros.lvl * 0.5));
            this.setDmg(12);
            this.setExperience(47);
        } else if (Objects.equals(this.getNom(), "Troll")) {
            this.setVie((int) (140 * heros.lvl * 0.5));
            this.setDmg(15);
            this.setExperience(78);
        }
    }
}
