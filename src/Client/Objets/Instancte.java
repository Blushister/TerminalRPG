package Client.Objets;

import Client.Joueur.Ami.Heros;
import Client.Joueur.Enemi.Vilain;
import Client.Maps.Donjon.Difficulty;
import Client.Maps.Donjon.Donjon;
import Client.Maps.Maps;
import Client.Menu.Choix;
import Client.Menu.Menu;
import Client.Network.Manager;
import Client.Objets.Arme.Arme;
import Client.Objets.Armure.Armure;
import Client.Objets.Consommable.Consommable;

import java.util.ArrayList;
import java.util.Scanner;

public class Instancte {
    Consommable Potion_de_vie = new Consommable("Potion_de_vie", 1, 10, 1, 1, 1, 1,1,1);
    Consommable Potion_de_mana = new Consommable("Potion_de_mana", 1, 10, 1, 1, 1, 1,1,1);
    Consommable  Potion_de_force = new Consommable("Potion_de_force", 1, 10, 1, 1, 1, 1,1,1);
    Consommable Potion_d_intelligence = new Consommable("Potion_d_intelligence", 1, 10, 1, 1, 1, 1,1,1);
    Consommable Potion_d_endurance = new Consommable("Potion_d_endurance", 1, 10, 1, 1, 1, 1,1,1);
    // Constructeur
    boolean debug = false;

    public Instancte() {

    }

    // Méthodes


    // Methode de l'inventaire
    public void InventaireStart (ArrayList<Objects> inventaire) {
        inventaire.add(Potion_de_vie);
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i).getClass().getSimpleName().equals("Consommable")) {
                inventaire.get(i).setQuantite(0);
            }
        }
    }

    public void InventoryCheck (ArrayList<Objects> inventaire) {
        for (int i = 0; i < inventaire.size(); i++) {
            Objects obj = inventaire.get(i);
            if (obj.getQuantite() == 0) {
                inventaire.remove(obj);
            }
        }
        if (inventaire.isEmpty()) {
            System.out.println("l'inventaire est vide");
        }
    }

    public void AfficherInventaire (Heros heros, ArrayList<Objects> inventaire, Menu menu) {
        this.InventoryCheck(inventaire);
        for (int i = 0; i < inventaire.size(); i++) {
            System.out.println(inventaire.get(i).getNoms() + " x" + inventaire.get(i).getQuantite());
        }

        menu.menuInventory();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                System.out.println("Vous avez choisi de consommer une potion");
                this.afficherConsommable(heros, inventaire);
                break;
            case 2:
                System.out.println("Vous avez choisi de Equiper / Desequiper une arme");
                this.afficherArme(inventaire, heros);
                break;
            case 3:
                System.out.println("Vous avez choisi de Equiper / Desequiper une armure");
                this.afficherArmure(inventaire, heros);
                break;
        }
    }

    // Afficher que les Objets Consommables contenus dans l'inventaire avec getClass().getSimpleName())

    public ArrayList<Consommable> afficherConsommable(Heros heros, ArrayList<Objects> inventaire) {
        ArrayList<Consommable> consommable = new ArrayList<>();
        this.verifierInventaire(inventaire);
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i).getClass().getSimpleName().equals("Consommable")) {
                consommable.add((Consommable) inventaire.get(i));
            }
        }
        // Afficher les consommables
        for (int i = 0; i < consommable.size(); i++) {
            System.out.println(i + 1 + " : " + consommable.get(i).getNoms() + " x" + consommable.get(i).getQuantite());
        }

        // Choisir un consommable

        System.out.println("Choisissez un consommable");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                System.out.println("Vous avez choisi de consommer une potion de vie");
                this.boirePotionSoin(heros, consommable);
                break;
            case 2:
                System.out.println("Vous avez choisi de consommer une potion de force");
                break;
            case 3:
                System.out.println("Vous avez choisi de consommer une potion de vitesse");
                break;
            case 4:
                System.out.println("Vous avez choisi de consommer une potion de magie");
                break;
        }
        return consommable;
    }

    public void SetQuantiteConsommable(Heros heros, String research, int quantite) {
        for (int i = 0; i < heros.inventory.size(); i++) {
            if (heros.inventory.get(i).getClass().getSimpleName().equals("Consommable")) {
                if (heros.inventory.get(i).getNoms().equals(research)) {
                    heros.inventory.get(i).setQuantite(quantite);
                }
            }
        }
    }

    public void afficherArme(ArrayList<Objects> inventaire, Heros heros) {
        ArrayList<Arme> arme = new ArrayList<>();
        this.verifierInventaire(inventaire);
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i).getClass().getSimpleName().equals("Arme")) {
                arme.add((Arme) inventaire.get(i));
            }
        }
        // Afficher les Armes
        for (int i = 0; i < arme.size(); i++) {
            System.out.println(i + " : " + arme.get(i).getNoms() + " x" + arme.get(i).getQuantite());
        }

        // Menu equipé / desequipé

        Scanner sc = new Scanner(System.in);
        System.out.println("1 : Equiper, 2 : Desequiper");
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                System.out.println("Vous avez choisi de Equiper une arme");
                this.equiperArme(heros, arme);
                break;
            case 2:
                System.out.println("Vous avez choisi de Desequiper une arme");
                this.desequiperArme(heros, arme);
                break;
        }

    }

    public void afficherArmure(ArrayList<Objects> inventaire, Heros heros) {
        ArrayList<Armure> Armure = new ArrayList<>();
        this.verifierInventaire(inventaire);
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i).getClass().getSimpleName().equals("Armure")) {
                Armure.add((Armure) inventaire.get(i));
            }
        }
        // Afficher les Armes
        for (int i = 0; i < Armure.size(); i++) {
            System.out.println(i + " : " + Armure.get(i).getNoms() + " x" + Armure.get(i).getQuantite());
        }

        // Menu equipé / desequipé

        Scanner sc = new Scanner(System.in);
        System.out.println("1 : Equiper, 2 : Desequiper");
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                System.out.println("Vous avez choisi de Equiper une armure");
                this.equiperArmure(heros, Armure);
                break;
            case 2:
                System.out.println("Vous avez choisi de Desequiper une armure");
                this.desequiperArmure(heros, Armure);
                break;
        }

    }

    public void equiperArme(Heros heros, ArrayList<Arme> arme) {
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        if (arme.get(choix).getQuantite() < 0 && arme.get(choix).getQuantite() > -1) {
            System.out.println("Vous ne pouvez pas équiper cette arme");
        } else {
            System.out.println("Vous avez choisi de vous équiper de " + arme.get(choix).getNoms());
            if (heros.getWeapon() == null) {
                System.out.println("Vous n'avez pas d'arme équipée");
            } else {
                heros.getWeapon().setQuantite(heros.getWeapon().getQuantite() + 1);
                heros.setForce(heros.getForce() - heros.getWeapon().getDegats());
            }
            heros.setWeapon(arme.get(choix));
            System.out.println("Vous avez équipé " + heros.weapon.getNoms());
            arme.get(choix).setQuantite(arme.get(choix).getQuantite() - 1);
            heros.setForce(heros.getForce() + heros.getWeapon().getDegats());
        }
    }

    // Desequiper Arme

    public void desequiperArme(Heros heros, ArrayList<Arme> arme) {
        try {
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            if (!heros.inventory.contains(heros.weapon)) {
                System.out.println("Vous ne pouvez pas desequiper cette arme");
            } else {
                System.out.println("Vous avez choisi de vous desequiper de " + arme.get(choix).getNoms());
                heros.setWeapon(arme.get(choix));
                System.out.println("Vous avez desequipé " + heros.weapon.getNoms());
                arme.get(choix).setQuantite(arme.get(choix).getQuantite() + 1);
                heros.setForce(heros.getForce() - heros.getWeapon().getDegats());
                heros.setWeapon(null);
            }
        } catch (Exception e) {
            System.out.println("Vous n'avez pas d'arme équipée");
        }
    }

    // Equiper / Desequiper Armure

    public void equiperArmure(Heros heros, ArrayList<Armure> armure) {
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        if (armure.get(choix).getQuantite() < 0 && armure.get(choix).getQuantite() > -1) {
            System.out.println("Vous ne pouvez pas équiper cette armure");
        } else {
            System.out.println("Vous avez choisi de vous équiper de " + armure.get(choix).getNoms());
            if (heros.getArmure() == null) {
                System.out.println("Vous n'avez pas d'armure équipée");
            } else {
                heros.getArmure().setQuantite(heros.getArmure().getQuantite() + 1);
                heros.setDefense(heros.getDefense() - heros.getArmure().getDefense());
            }
            heros.setArmure(armure.get(choix));
            System.out.println("Vous avez équipé " + heros.armure.getNoms());
            armure.get(choix).setQuantite(armure.get(choix).getQuantite() - 1);
            heros.setDefense(heros.getDefense() + heros.getArmure().getDefense());
        }
    }

    public void desequiperArmure(Heros heros, ArrayList<Armure> armure) {
        try {
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            if (!heros.inventory.contains(heros.armure)) {
                System.out.println("Vous ne pouvez pas desequiper cette armure");
            } else {
                System.out.println("Vous avez choisi de vous desequiper de " + armure.get(choix).getNoms());
                heros.setArmure(armure.get(choix));
                System.out.println("Vous avez desequipé " + heros.armure.getNoms());
                armure.get(choix).setQuantite(armure.get(choix).getQuantite() + 1);
                heros.setDefense(heros.getDefense() - heros.getArmure().getDefense());
                heros.setArmure(null);
            }
        } catch (Exception e) {
            System.out.println("Vous n'avez pas d'armure équipée");
        }
    }

    public void verifierInventaire (ArrayList<Objects> inventaire) {
        for (int i = 0; i < inventaire.size(); i++) {
            for (int j = i + 1; j < inventaire.size(); j++) {
                if (inventaire.get(i).getNoms().equals(inventaire.get(j).getNoms())) {
                    inventaire.get(i).setQuantite(inventaire.get(i).getQuantite() + 1);
                    inventaire.remove(j);
                    j--;
                }
            }
        }
    }

    public ArrayList<Consommable> boirePotionSoin(Heros heros, ArrayList<Consommable> consommables) {
        for (int i = 0; i < consommables.size(); i++) {
            if (consommables.get(i).getQuantite() == 0 && consommables.get(i).getNoms().equals("Potion_de_vie")) {
                System.out.println("Vous n'avez plus de potion de soin");
                break;
            }
            if (consommables.get(i).getNoms().equals("Potion_de_vie")) {
                consommables.get(i).setQuantite(consommables.get(i).getQuantite() - 1);
                if (heros.getVie() + 50 > heros.getVieMax()) {
                    heros.setVie(heros.getVieMax());
                } else {
                    heros.setVie(heros.getVie() + 50);
                }
                System.out.println("Vous avez bu une potion de soins");
            }
        }


        return consommables;
    }

    // Instance des menus

    public void MenuInventaire (Heros heros, ArrayList<Objects> inventaire, Menu menu) {
        System.out.println("Inventaire");
        this.AfficherInventaire(heros, inventaire, menu);
    }

    // Instance
    public void InstanceCombat(Heros heros, Vilain vilain) {

        System.out.println("Vous avez rencontré un " + vilain.getNom() + " !");

        while (heros.getVie() > 0 && vilain.getVivant()) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Attaquer");
            System.out.println("2. Utiliser un objet");

            int choix = 0;
            Scanner sc = new Scanner(System.in);
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Vous attaquez le " + vilain.getNom() + " !");
                    try {
                        vilain.setVie((int) (vilain.getVie() - (heros.weapon.getDegats() + heros.getForce() * 0.5)));
                    } catch (Exception e) {
                        System.out.println("Vous n'avez pas d'arme équipée vous attaquez avec vos poings");
                        vilain.setVie((int) (vilain.getVie() - (heros.getForce() * 0.5)));
                    }
                    if (vilain.getVie() <= 0) {
                        System.out.println("L'ennemi est mort !");
                    } else {
                        System.out.println("Il lui reste " + vilain.getVie() + " points de vie.");
                    }
                    if (vilain.getVie() <= 0) {
                        System.out.println("Vous avez tué le " + vilain.getNom() + " !");
                        vilain.mourrir(heros);
                        break;
                    } else if (vilain.getVivant()) {
                        System.out.println("Le " + vilain.getNom() + " vous attaque !");
                        int calc = (int) (vilain.getDmg() * (0.5 * heros.lvl) - (0.2 * heros.getDefense()));
                        int degats = (int) (Math.random() * calc);
                        heros.setVie(heros.getVie() - degats);
                        System.out.println("Il vous reste " + heros.getVie() + " points de vie.");
                        break;
                    } else if (heros.getVie() <= 0) {
                        System.out.println("Vous avez été tué par le " + vilain.getNom() + " !");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Boire une potion de soin ?");
                    System.out.println("1. Oui");
                    System.out.println("2. Non");
                    int choix2 = 0;
                    Scanner sc2 = new Scanner(System.in);
                    choix2 = sc2.nextInt();
                    switch (choix2) {
                        case 1:
                            this.boirePotionSoin(heros, this.boirePotionSoin(heros, afficherConsommable(heros, heros.inventory)));
                            break;
                        case 2:
                            break;
                    }
                case 3:
                    System.out.println("Vous fuyez le combat.");
                    return;

            }
        }
    }

    public void eventMap(String maps[][], Heros heros, Vilain vilain, Donjon donjon) {
        System.out.println("Vous êtes sur la map " + heros.getPositionY() + " " + heros.getPositionX());
        System.out.println(maps[heros.getPositionY()][heros.getPositionX()]);
        if (java.util.Objects.equals(maps[heros.getPositionY()][heros.getPositionX()], " ❎ ")) {
            System.out.println("Vous êtes a un village");
            heros.setVillage(true);
        } else if (java.util.Objects.equals(maps[heros.getPositionY()][heros.getPositionX()], " ☗ ")) {
            System.out.println("Vous arriver a l'entrée d'un donjon");
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Entrer dans le donjon");
            System.out.println("2. Passer votre chemin");
            Choix.choixMenuDonjon(heros, donjon);
        } else {
            heros.setVillage(false);
            vilain.setVivant(true);
        }
    }


    // Slot Equipement

    public void equiperArme(Heros heros, Arme arme) {
        heros.setWeapon(arme);
        heros.setForce(heros.getForce() + arme.getDegats());
    }

    public Heros ResumeSave(Heros heros, Maps map) throws Exception {
        heros = Manager.getDataPlayer(debug, heros);
        map.setHerosPosition(heros, heros.getPositionY(), heros.getPositionX());
        Manager.getInventoryContentConsommable(debug,this, heros);
        for (int i = 0; i < 5; i++) {
            int[] position = Manager.getVillagePosition(debug, i);
            map.setVillagePosition(position[0], position[1]);

            }
        return heros;
        }

    public void SaveAllConsommable(Heros heros) throws Exception {
        heros.SaveConsommable(Potion_de_vie);
        heros.SaveConsommable(Potion_de_mana);
        heros.SaveConsommable(Potion_de_force);
        heros.SaveConsommable(Potion_d_intelligence);
        heros.SaveConsommable(Potion_d_endurance);
    }

    public void UpdateAllConsommable(Heros heros) throws Exception {
        heros.UpdateConsommable(Potion_de_vie);
        heros.UpdateConsommable(Potion_de_mana);
        heros.UpdateConsommable(Potion_de_force);
        heros.UpdateConsommable(Potion_d_intelligence);
        heros.UpdateConsommable(Potion_d_endurance);
    }

    public void Save(Heros heros, Arme arme, Maps maps, Armure armure) throws Exception {
        Manager.saveDataArme(debug, arme);
        Manager.saveArmure(debug, armure);
        Manager.saveDataPlayer(debug, heros);
        Manager.saveDataInventory(debug, heros.inventory, heros);
        this.SaveAllConsommable(heros);
        Manager.saveInventoryContentConsommable(debug, heros.inventory);
    }

    public void UpdateSave(Heros heros, Arme arme) throws Exception {
        Manager.updateDataArme(debug, arme);
        Manager.updateDataPlayer(debug, heros);
        Manager.updateDataInventory(debug, heros.inventory, heros);
        this.UpdateAllConsommable(heros);
        Manager.updateInventoryContentConsommable(debug, heros);
    }

    public void AjouterPotionSoin(Heros heros) {
        heros.inventory.add(Potion_de_vie);
    }

    public void AjouterPotionForce(Heros heros) {
        heros.inventory.add(Potion_de_force);
    }

    public void AjouterPotionVitesse(Heros heros) {
        heros.inventory.add(Potion_d_endurance);
    }

    public void AjouterPotionMagie(Heros heros) {
        heros.inventory.add(Potion_de_mana);
    }


}
