package Client.Menu;

import Client.Joueur.Ami.Heros;
import Client.Maps.Donjon.Donjon;
import Client.Maps.Maps;
import Client.Network.Manager;
import Client.Objets.Arme.Arme;
import Client.Objets.Armure.Armure;
import Client.Objets.Instancte;

import java.util.Scanner;

public class Choix {
    boolean debug = false;
    Heros heros;
    Armure armure = new Armure("Tunic en cuir", 10, 10, 10, 1);

    // constructeur

    public Choix() {

    }

    // methods

    public void choixMenuBoutique() {
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1 - Acheter");
        System.out.println("2 - Vendre");
    }

    public Heros choixCreationPersonnage() {
        System.out.println("1 : Créer un personnage");
        System.out.println("2 : Quitter");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                Arme arme = new Arme("Epee en bois", 10, 10, 10, 1);
                System.out.println("Création d'un personnage");

                System.out.print("Choisissez votre nom : ");
                String nom = sc.next();

                System.out.print("Choisissez votre classe : 1. Guerrier 2. Mage 3. Voleur : ");
                String classe = sc.next();

                switch (classe) {
                    case "1":
                        heros = new Heros(nom, 120, 120, 10, 1, 1,12,5,6, arme, armure);
                        heros.setVieMax(120);
                        break;
                    case "2":
                        heros = new Heros(nom, 90, 130, 100, 1, 1,10,13,4, arme, armure);
                        heros.setVieMax(90);
                        break;
                    case "3":
                        heros = new Heros(nom, 110, 130, 10, 1, 1,11,7,15, arme, armure);
                        heros.setVieMax(110);
                        break;
                    default:
                        System.out.println("Vous n'avez pas choisi de classe valide");
                        break;
                }
                return heros;
            case 2:
                System.out.println("Quitter");
                break;
            default:
                System.out.println("Choix invalide");
                break;
        }

        if (choix == 2) {
            System.out.println("Quitter");

        }
        return null;
    }

    // Menu donjon
    public static void choixMenuDonjon(Heros heros, Donjon donjon) {
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                System.out.println("Entrer dans le donjon");
                donjon.InstanceDonjon(heros);
                break;
            case 2:
                System.out.println("Quitter");
                break;
            default:
                System.out.println("Choix invalide");
                break;
        }
    }

    public Heros MenuLoad(Maps maps, Instancte instance, Arme arme, Donjon donjon, boolean debug) throws Exception {
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Charger une partie");
        System.out.println("3. Quitter");
        Scanner sc = new Scanner(System.in);
        int choix2 = 0;
        choix2 = sc.nextInt();
        switch (choix2) {
            case 1:
                if (debug) {
                    Heros heros = new Heros("Heros1", 1000, 100, 10, 1, 0,10,1,1, arme, armure);
                    Manager.deleteAllSave(debug);
                    instance.InventaireStart(heros.inventory);
                    maps.setHerosPosition(heros,15, 15);
                    maps.setVillageRandomNb(5);
                    instance.Save(heros, arme, maps, armure);
                    for (int i = 0; i < 5; i++) {
                        int[] position = Manager.getVillagePosition(debug, i);
                        maps.setVillagePosition(position[0], position[1]);
                    }
                    return heros;
                } else {
                    Manager.deleteAllSave(debug);
                    Heros heros = this.choixCreationPersonnage();
                    instance.InventaireStart(heros.inventory);
                    maps.setHerosPosition(heros,15, 15);
                    maps.setVillageRandomNb(5);
                    instance.Save(heros, arme, maps, armure);
                    for (int i = 0; i < 5; i++) {
                        int[] position = Manager.getVillagePosition(debug, i);
                        maps.setVillagePosition(position[0], position[1]);
                    }
                    return heros;

                }
            case 2:
                Heros herosDefault = new Heros("Heros1", 10000, 100, 10, 1, 0, 10, 1, 1, arme, armure);
                instance.InventaireStart(herosDefault.inventory);
                herosDefault = instance.ResumeSave(herosDefault, maps);
                return herosDefault;
            case 3:
                System.out.println("Vous quittez le jeu");
                // Systeme quitter
                System.exit(0);
                return null;
            default:
                System.out.println("Choix invalide");
                // Revenir au menu de selection
                this.MenuLoad(maps, instance, arme, donjon, debug);
                return null;
        }
    }
}
