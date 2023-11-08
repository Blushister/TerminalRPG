package Client.Joueur.Ami;

import Client.Joueur.Enemi.Ennemi;
import Client.Menu.Menu;
import Client.Network.Manager;
import Client.Objets.Arme.Arme;
import Client.Objets.Armure.Armure;
import Client.Objets.Consommable.Consommable;
import Client.Objets.Objects;

import java.util.*;

public class Heros {
    boolean debug = false;
    Random rand = new Random();
    public String name;
    public int vie;
    private int vieMax = 100;
    public int endurance;
    public int mp;
    public int lvl;
    public int xp;
    public int force;
    public int intelligence;
    public int agilite;
    public int PositionY;
    public int PositionX;
    public int defense;
    boolean village = false;
    public int posY;
    public int souls = 100; // Money du jeux
    private boolean vivant = true;
    private boolean enDonjon = false;
    private boolean boutique = false;
    private boolean combat = false;
    public Arme weapon;
    public Armure armure;
    public ArrayList<Client.Objets.Objects> inventory = new ArrayList<>();
    Ennemi ennemi = new Ennemi( "Ennemi", 100, 10, 10, 100, 10);
    Consommable potion_endurance = new Consommable("Potion_d'endurance", 0, 1, 0, 0, 50, 0, 0, 0);
    Consommable potion_force = new Consommable("Potion_de_force", 0, 1, 0, 50, 0, 0, 0, 0);
    Consommable potion_intelligence = new Consommable("Potion_d'intelligence", 0, 1, 0, 0, 0, 50, 0, 0);
    Consommable potion_hp = new Consommable("Potion_de_vie", 0, 1, 50, 0, 0, 0, 0, 0);
    Consommable potion_mp = new Consommable("Potion_de_mana", 0, 1, 0, 0, 0, 0, 50, 0);

    Menu menu = new Menu("Start");
    Scanner sc = new Scanner(System.in);

    // Création du constructeur
    public Heros(String name, int vie, int endurance, int mp, int lvl, int xp, int force, int intelligence, int agilite, Arme weapon, Armure armure) {
        this.name = name;
        this.vie = vie;
        this.endurance = endurance;
        this.mp = mp;
        this.lvl = lvl;
        this.xp = xp;
        this.force = force;
        this.intelligence = intelligence;
        this.agilite = agilite;
        this.weapon = weapon;
        this.armure = armure;
    }

    // Affichage des caractéristiques du héros

    // Gain xp

    public void gainXp(int xp) {
        if (xp < 0) {
            return;
        }
        this.xp += xp;
        System.out.println("Vous avez gagné " + xp + " points d'expérience !");
        while (this.xp > this.lvl * 50) {
            this.xp = this.xp - (this.lvl * 50);
            this.lvl += 1;
            int select = rand.nextInt(3);
            System.out.println("Vous avez gagné un niveau !");
            this.vieMax += 10;
            menu.CaracSelector();
            int choixMenu = sc.nextInt();
            switch (choixMenu) {
                case 1:
                    this.setForce(this.getForce() + 1);
                    break;
                case 2:
                    this.setIntelligence(this.getIntelligence() + 1);
                    break;
                case 3:
                    this.setDefense(this.getDefense() + 1);
                    break;
                case 4 :
                    this.setAgilite(this.getAgilite() + 1);
                    break;
            }
            this.vie = this.vieMax;
        }
    }

    // Ajout d'items dans l'inventaire

    public void addItem(Object item) {
        this.inventory.add((Client.Objets.Objects) item);
    }

    // Boire une potion de vie

    public void boirePotionVie() {
        if (this.inventory.contains(potion_hp)) {
            this.vie += 50;
            this.inventory.remove(potion_hp);
            System.out.println("Vous avez bu une potion de vie !");
        } else {
            System.out.println("Vous n'avez pas de potion de vie !");
        }
    }

    // Achat

    public void boutique() {

        Scanner scan = new Scanner(System.in);
        int choix0 = scan.nextInt();
        switch (choix0) {
            case 1:
                System.out.println("Bienvenue dans la boutique !");
                System.out.println("Vous avez " + this.souls + " souls");
                System.out.println("Que voulez-vous acheter ?");
                System.out.println("1 - Potion de vie (50 souls)");
                System.out.println("2 - Potion de mana (50 souls)");
                System.out.println("3 - Potion de force (50 souls)");
                System.out.println("4 - Potion d'intelligence (50 souls)");
                System.out.println("5 - Potion d'endurance (50 souls)");
                int choix = scan.nextInt();
                if (choix == 1) {
                    if (this.souls >= 50) {
                        this.souls -= 50;
                        this.inventory.add(potion_hp);
                        System.out.println("Vous avez acheté une potion de vie !");
                    } else {
                        System.out.println("Vous n'avez pas assez de souls !");
                    }
                } else if (choix == 2) {
                    if (this.souls >= 50) {
                        this.souls -= 50;
                        this.inventory.add(potion_mp);
                        System.out.println("Vous avez acheté une potion de mana !");
                    } else {
                        System.out.println("Vous n'avez pas assez de souls !");
                    }
                } else if (choix == 3) {
                    if (this.souls >= 50) {
                        this.souls -= 50;
                        this.inventory.add(potion_force);
                        System.out.println("Vous avez acheté une potion de force !");
                    } else {
                        System.out.println("Vous n'avez pas assez de souls !");
                    }
                } else if (choix == 4) {
                    if (this.souls >= 50) {
                        this.souls -= 50;
                        this.inventory.add(potion_intelligence);
                        System.out.println("Vous avez acheté une potion d'intelligence !");
                    } else {
                        System.out.println("Vous n'avez pas assez de souls !");
                    }
                } else if (choix == 5) {
                    if (this.souls >= 50) {
                        this.souls -= 50;
                        this.inventory.add(potion_endurance);
                        System.out.println("Vous avez acheté une potion d'endurance !");
                    } else {
                        System.out.println("Vous n'avez pas assez de souls !");
                    }
                } else {
                    System.out.println("Choix invalide !");
                }
                break;
            case 2:
                System.out.println("Bienvenue dand le coté vente de la boutique !");
                System.out.println("Vous avez " + this.souls + " souls");
                System.out.println("Que voulez-vous vendre ?");
                for (int i = 0; i < this.inventory.size(); i++) {
                    System.out.println(i + " : " + this.inventory.get(i).getNoms() + " x" + this.inventory.get(i).getQuantite() + " (" + this.inventory.get(i).getValeur() + " souls)");
                }
                int choix2 = scan.nextInt();
                if (choix2 >= 0 && choix2 < this.inventory.size()) {
                    if (this.inventory.get(choix2).getQuantite() <= 0) {
                        System.out.println("Vous ne pouvez pas vendre un objets que vous n'avez pas");
                    } else {
                        this.inventory.get(choix2).setQuantite(this.inventory.get(choix2).getQuantite() - 1);
                        this.souls += this.inventory.get(choix2).getValeur();
                        System.out.println("Vous avez vendu " + this.inventory.get(choix2).getNoms() + " !");
                    }
                } else {
                    System.out.println("Choix invalide !");
                }
        }
    }

    // Boire une potion de mana

    public void boirePotionMana() {
        if (this.inventory.contains(potion_mp)) {
            this.mp += 50;
            this.inventory.remove(potion_mp);
            System.out.println("Vous avez bu une potion de mana !");
        } else {
            System.out.println("Vous n'avez pas de potion de mana !");
        }
    }

    // Boire une potion d'endurance

    public void boirePotionEndurance() {
        if (this.inventory.contains(potion_endurance)) {
            this.endurance += 50;
            this.inventory.remove(potion_endurance);
            System.out.println("Vous avez bu une potion d'endurance !");
        } else {
            System.out.println("Vous n'avez pas de potion d'endurance !");
        }
    }

    // Se reposer

    public void seReposer() {
        this.endurance = 100;
        this.vie = this.getVieMax();
        System.out.println("Vous vous êtes reposé !");
    }

    // Combatre

    public void attaquer(Ennemi ennemi) {
        // si vie de enemy > 0

        Scanner sc = new Scanner(System.in);
        System.out.println("Comment voulez-vous attaquer ? (1 : Attaque cac, 2 : Attaque magic)");
        int choix = sc.nextInt();
        System.out.println("Tour du joueur");
        if (this.endurance > 0) {
            if (choix == 1) {
                float dmg = (float) (0.5 * this.force);
                System.out.println("Vous attaquez avec votre " + this.weapon.getNoms() + " et infligez " + dmg + " points de dégâts !");
                this.endurance -= 50;
                ennemi.vie -= dmg;
            } else if (choix == 2) {
                float dmg = (float) (0.25 * this.intelligence);
                System.out.println("Vous attaquez avec votre magie");
                this.endurance -= 50;
                ennemi.vie -= dmg;
            } else {
                System.out.println("Vous n'avez pas choisi une attaque valide !");
            }
        } else {
            System.out.println("Vous n'avez plus d'endurance !");
            System.out.println("Boire une potion d'endurance ? (1 : Oui, 2 : Non)");
            int choix2 = sc.nextInt();
            if (choix2 == 1) {
                this.boirePotionEndurance();
            } else {
                System.out.println("Vous avez perdu !");
            }

        }

    }

    // Systeme de combat

    public void combat(Ennemi ennemi) { // TODO : Refaire le systeme de combats
        if (!ennemi.getVivant()) {
            System.out.println("Vous avez déjà tué cet ennemi !");
        } else {
            while (this.vie > 0 && ennemi.vie > 0) {
                ennemi.afficherCaracteristiques();
                this.attaquer(ennemi);
                ennemi.attaquer(this);
            }
            if (this.vie <= 0) {
                System.out.println("Vous avez perdu !");
            } else {
                System.out.println("Vous avez gagné !");
                ennemi.mourrir(this);
            }
        }
    }

    // Check vivant

    public boolean checkVivant() {
        if (this.vivant) {
            return true;
        } else {
            return false;
        }
    }

    // Setters vivant

    public void setVivant(boolean vivant) {
        this.vivant = vivant;
    }

    // Hero mort

    public void heroMort() {
        System.out.println("Vous êtes mort !");
        System.out.println("Vous avez perdu " + this.souls + " souls");
        this.setVivant(false);
    }

    public void SaveConsommable(Consommable consommable) throws Exception {
        Manager.saveDataConsommable(debug, consommable, this);
    }

    public void UpdateConsommable(Consommable consommable) throws Exception {
        Manager.updateDataConsommable(debug, consommable, this);
    }

    // Getters

    public String getNom() {
        return this.name;
    }

    public int getVie() {
        return this.vie;
    }

    public int getMp() {
        return this.mp;
    }

    public int getEndurance() {
        return this.endurance;
    }

    public int getSouls() {
        return this.souls;
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

    public int getexperience() {
        return this.xp;
    }

    public int getNiveau() {
        return this.lvl;
    }


    public int getPositionX() {
        return this.PositionX;
    }

    public int getPositionY() {
        return this.PositionY;
    }

    public boolean getVillage() {
        return this.village;
    }

    public boolean getVivant() {
        return this.vivant;
    }

    public ArrayList<Objects> getInventory() {
        return this.inventory;
    }

    public boolean getenDungeon() {
        return this.enDonjon;
    } // TODO Implement this method

    public Arme getWeapon() {
        return this.weapon;
    }

    public int getVieMax() {
        return this.vieMax;
    }

    public Armure getArmure() {
        return this.armure;
    }

    public int getDefense() {
        return this.defense;
    }
    // Setters

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgilite(int agilite) {
        this.agilite = agilite;
    }

    public void setWeapon(Arme weapon) {
        this.weapon = weapon;
    }

    public void setVillage(boolean village) {
        this.village = village;
    }

    public void setPositionX(int positionX) {
        this.PositionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.PositionY = positionY;
    }

    public void setNiveau(int lvl) {
        this.lvl = lvl;
    }

    public void setExperience(int xp) {
        this.xp = xp;
    }

    public void setEnDonjon(boolean enDonjon) {
        this.enDonjon = enDonjon;
    }

    public void setVieMax(int vieMax) {
        this.vieMax = vieMax;
    }

    public void setArmure(Armure armure) {
        this.armure = armure;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

}
