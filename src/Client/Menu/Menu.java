package Client.Menu;

import Client.Joueur.Ami.Heros;

public class Menu {
    // Classe objet menu

    // Attributs

    private String nom;

    // Constructeur

    public Menu(String nom) {
        this.nom = nom;
    }

    // Getters

    public String getNom() {
        return this.nom;
    }

    // Setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Methods

    public void afficherMenuBoutique() {
        System.out.println("Boutique");
        System.out.println("1. Acheter");
        System.out.println("2. Vendre");
        System.out.println("3. Quitter");
    }

    public void afficherMenuCombat() {
        System.out.println("Combat");
        System.out.println("1. Attaquer");
        System.out.println("2. Se soigner");
        System.out.println("3. Fuir");
    }

    public void afficherMenuJeu() {
        System.out.println("Jeu");
        System.out.println("1. Commencer une partie");
        System.out.println("2. Charger une partie");
        System.out.println("3. Quitter");
    }

    public void afficherMenuPersonnage() {
        System.out.println("Personnage");
        System.out.println("1. Créer un personnage");
        System.out.println("2. Supprimer un personnage");
        System.out.println("3. Quitter");
    }

    public void afficherMenuPrincipal() {
        System.out.println("Menu principal");
        System.out.println("1. Jeu");
        System.out.println("2. Personnage");
        System.out.println("3. Boutique");
        System.out.println("4. Quitter");
    }

    public void CaracSelector() {
        System.out.println("Montée de niveau :");
        System.out.println("1. Ajouter 1 en force");
        System.out.println("2. Ajouter 1 en Inteligence");
        System.out.println("3. Ajouter 1 en Défense");
        System.out.println("4. Ajouter 1 en Agilité");
    }

    public void afficherStatistiques(Heros heros) {
        System.out.println("Statistiques");

        System.out.println("Nom : " + heros.getNom());
        System.out.println("Niveau : " + heros.getNiveau());
        System.out.println("Vie : " + heros.getVie());
        System.out.println("Force : " + heros.getForce());
        System.out.println("Agilité : " + heros.getAgilite());
        System.out.println("Intelligence : " + heros.getIntelligence());

    }

    public void afficherMenu() {
        System.out.print("Menu : ");
        System.out.println("1: Combattre 2: Se deplacer, 3: Inventaire, 4: Statistiques, 5: Quitter");

    }

    public void menuInventory() {
        System.out.println("Inventaire");
        System.out.println("1: Consommer potion, 2: Equiper / Desequiper arme, 3: Equiper / Desequiper armure, 4: Quitter");
    }

    public void afficherMenuVillage() {
        System.out.println("Village");
        System.out.println("1: Boutique, 2: Inventaire, 3: Statistiques, 4: Se déplacer, 5 : Se reposer, 6: Quitter");

    }

    public void MenuDeplacement() {
        System.out.println("Menu");
        System.out.println("1: Aller a Gauche, 2: Aller a Droite, 3: Aller en Haut, 4: Aller en Bas, 5: Quitter");
    }

    public void MenuLancement() {
        System.out.println("Menu");
        System.out.println("1: Nouvelle partie, 2: Charger partie, 3: Quitter");
    }
}
