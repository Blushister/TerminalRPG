package Client.Menu.Affichage;

import Client.Joueur.Ami.Heros;

public class Affichage {

    // Constructeur
    public Affichage() {

    }

    // Affichage
    public void afficherMenu() {
        System.out.println("1. Créer un personnage");
        System.out.println("2. Charger un personnage");
        System.out.println("3. Quitter");
    }

    public void afficherStats(Heros heros) {
        try {
            System.out.println("Nom : " + heros.getNom());
            System.out.println("Niveau : " + heros.getNiveau());
            System.out.println("Expérience : " + heros.getexperience());
            System.out.println("Endurance : " + heros.getEndurance());
            System.out.println("Vie : " + heros.getVie() + " / " + heros.getVieMax());
            System.out.println("Force : " + heros.getForce());
            System.out.println("Defense : " + heros.getDefense());
            System.out.println("Agilité : " + heros.getAgilite());
            System.out.println("Intelligence : " + heros.getIntelligence());
            System.out.println("Arme : " + heros.weapon.getNomArme());
            System.out.println("Armure : " + heros.armure.getNom());
            System.out.println("Dégats : " + heros.weapon.getDegats());
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }

    public void afficherStatsDebug(Heros heros) {
        try {
            System.out.println("Nom : " + heros.getNom());
            System.out.println("PositionX : " + heros.getPositionX());
            System.out.println("PositionY : " + heros.getPositionY());
            System.out.println("Niveau : " + heros.getNiveau());
            System.out.println("Expérience : " + heros.getexperience());
            System.out.println("Endurance : " + heros.getEndurance());
            System.out.println("Vie : " + heros.getVie() + " / " + heros.getVieMax());
            System.out.println("Force : " + heros.getForce());
            System.out.println("Defense : " + heros.getDefense());
            System.out.println("Agilité : " + heros.getAgilite());
            System.out.println("Intelligence : " + heros.getIntelligence());
            System.out.println("VieMax : " + heros.getVieMax());
            System.out.println("Arme : " + heros.weapon.getNomArme());
            System.out.println("Armure : " + heros.armure.getNom());
            System.out.println("Dégats : " + heros.weapon.getDegats());
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }
}
