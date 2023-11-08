import Client.Joueur.Ami.Heros;
import Client.Joueur.Enemi.Vilain;
import Client.Maps.Donjon.Difficulty;
import Client.Maps.Donjon.Donjon;
import Client.Maps.Maps;
import Client.Menu.Affichage.Affichage;
import Client.Menu.Choix;
import Client.Menu.Menu;
import Client.Network.Manager;
import Client.Objets.Arme.Arme;
import Client.Objets.Armure.Armure;
import Client.Objets.Instancte;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean debug = false;
        Arme arme = new Arme("Epee en bois", 10, 10, 10, 1);
        Arme excalibur = new Arme("Excalibur", 100, 10, 100, 1);
        Armure armure = new Armure("Tunic en cuir", 10, 10, 10, 1);
        Vilain vilain = new Vilain("Gobelin", 20, 10, 10, 100);
        Menu menu = new Menu("Start");
        Donjon donjon = new Donjon(5, Difficulty.EASY);
        Scanner scanner = new Scanner(System.in);
        Choix choix = new Choix();
        Instancte instance = new Instancte();
        Affichage affichage = new Affichage();
        Maps maps = new Maps();

        System.out.println("Que voulez-vous faire ?");

        Heros heros = choix.MenuLoad(maps, instance, arme, donjon, debug);

        while (true) {
            if (heros.getVillage()) {
                menu.afficherMenuVillage();
                int choixMenu = scanner.nextInt();
                switch (choixMenu) {
                    case 1:
                        choix.choixMenuBoutique();
                        heros.boutique();
                        break;
                    case 2:
                        instance.AfficherInventaire(heros, heros.inventory, menu);
                        break;
                    case 3:
                        affichage.afficherStats(heros);
                        break;
                    case 4:
                        System.out.println("Vous avez choisi de vous deplacer");
                        menu.MenuDeplacement();
                        int choixDeplacement = scanner.nextInt();
                        switch (choixDeplacement) {
                            case 1:
                                maps.allerGauche(heros, instance, vilain, donjon);
                                break;
                            case 2:
                                maps.allerDroite(heros, instance, vilain, donjon);
                                break;
                            case 3:
                                maps.allerHaut(heros, instance, vilain, donjon);
                                break;
                            case 4:
                                maps.allerBas(heros, instance, vilain, donjon);
                                break;
                            case 5:
                                System.out.println("Vous avez choisi de quitter");
                                break;
                        }
                        break;
                    case 5:
                        System.out.println("Vous avez choisi de vous reposer");
                        heros.seReposer();
                        break;
                    case 6:
                        System.out.println("Vous avez choisi de quitter");
                        break;
                }
            } else {
                menu.afficherMenu();
                int choixMenu = scanner.nextInt();
                switch (choixMenu) {
                    case 1:
                        vilain.updateMonster(heros);
                        instance.InstanceCombat(heros, vilain);
                        break;
                    case 2:
                        System.out.println("Vous avez choisi de vous deplacer");
                        menu.MenuDeplacement();
                        int choixDeplacement = scanner.nextInt();
                        switch (choixDeplacement) {
                            case 1:
                                maps.allerGauche(heros, instance, vilain, donjon);
                                maps.afficherMap();
                                break;
                            case 2:
                                maps.allerDroite(heros, instance, vilain, donjon);
                                maps.afficherMap();
                                break;
                            case 3:
                                maps.allerHaut(heros, instance, vilain, donjon);
                                maps.afficherMap();
                                break;
                            case 4:
                                maps.allerBas(heros, instance, vilain, donjon);
                                maps.afficherMap();
                                break;
                            case 5:
                                System.out.println("Vous avez choisi de quitter");
                                break;
                            default:
                                System.out.println("Vous avez choisi une mauvaise option");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("Vous avez choisi d'aller à l'inventaire");
                        instance.AfficherInventaire(heros, heros.inventory, menu);
                        break;
                    case 5:
                        System.out.println("Vous avez choisi de quitter");
                        System.exit(0);
                        break;
                    case 4:
                        System.out.println("Vous avez choisi de voir vos statistiques");
                        affichage.afficherStats(heros);
                        break;
                    case 1475963:
                        System.out.println("Menu de debug");
                        System.out.println("1. Ajouter des niveaux");
                        System.out.println("2. Ajouter des points de vie");
                        System.out.println("3. Ajouter de la force");
                        System.out.println("4. Ajouter de l'agilité");
                        System.out.println("5. Ajouter de l'intelligence");
                        System.out.println("6. Afficher la map");
                        System.out.println("7. Sauvegarder");
                        int choixDebug = scanner.nextInt();
                        switch (choixDebug) {
                            case 1:
                                System.out.println("Combien de niveaux voulez-vous ajouter ?");
                                int niveau = scanner.nextInt();
                                heros.setNiveau(heros.getNiveau() + niveau);
                                break;
                            case 2:
                                System.out.println("Combien de points de vie voulez-vous ajouter ?");
                                int vie = scanner.nextInt();
                                heros.setVie(heros.getVie() + vie);
                                break;
                            case 3:
                                System.out.println("Combien de force voulez-vous ajouter ?");
                                int force = scanner.nextInt();
                                heros.setForce(heros.getForce() + force);
                                break;
                            case 4:
                                System.out.println("Combien d'agilité voulez-vous ajouter ?");
                                int agilite = scanner.nextInt();
                                heros.setAgilite(heros.getAgilite() + agilite);
                                break;
                            case 5:
                                System.out.println("Combien d'intelligence voulez-vous ajouter ?");
                                int intelligence = scanner.nextInt();
                                heros.setIntelligence(heros.getIntelligence() + intelligence);
                                break;
                            case 6:
                                maps.afficherMap();
                                break;
                            case 7:
                                instance.UpdateSave(heros, arme);
                                break;
                            case 8:
                                affichage.afficherStatsDebug(heros);
                                break;
                            case 9:
                                instance.AjouterPotionSoin(heros);
                        }
                    default:
                        System.out.println("Choix invalide");
                }
            }
        }
    }
}