package Client.Network;

import Client.Joueur.Ami.Heros;
import Client.Objets.Arme.Arme;
import Client.Objets.Armure.Armure;
import Client.Objets.Consommable.Consommable;
import Client.Objets.Instancte;
import Client.Objets.Objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Manager {
    public static Connection Connector() throws Exception {

        Properties props = new Properties();
        try (InputStream in = Manager.class.getResourceAsStream("/conf.properties")) {
            if (in == null) {
                throw new FileNotFoundException("Fichier conf.properties non trouvé");
            }
            props.load(in);
        }

        Class.forName(props.getProperty("jdbc.driver.class"));

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }

    // Sauvegarder les données du joueur dans la base de données

    public static void saveDataPlayer(boolean debug, Heros heros) throws Exception {
        Connection connection = Connector();

        String sql = "INSERT INTO player (Id_joueur, nom_player, level, xp, hp, endurance, mp, p_force, inteligence, agilite, souls, J_PositionX, J_PositionY, Nom_arme, nom_armure) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setString(2, heros.getNom());
        statement.setInt(3, heros.lvl);
        statement.setInt(4, heros.xp);
        statement.setInt(5, heros.vie);
        statement.setInt(6, heros.endurance);
        statement.setInt(7, heros.mp);
        statement.setInt(8, heros.force);
        statement.setInt(9, heros.intelligence);
        statement.setInt(10, heros.agilite);
        statement.setInt(11, heros.souls);
        statement.setInt(12, heros.PositionX);
        statement.setInt(13, heros.PositionY);
        statement.setString(14, heros.weapon.getNomArme());
        statement.setString(15, heros.armure.getNoms());


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }
    }

    // Update les données du joueur dans la base de données

    public static void updateDataPlayer(boolean debug, Heros heros) throws Exception {
        Connection connection = Connector();

        String sql = "UPDATE player SET Id_joueur = ?, nom_player = ?, level = ?, xp = ?, hp = ?, endurance = ?, mp = ?, p_force = ?, inteligence = ?, agilite = ?, souls = ?, J_PositionX = ?, J_PositionY = ?, Nom_arme = ?, nom_armure = ? WHERE Id_joueur = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setString(2, heros.name);
        statement.setInt(3, heros.lvl);
        statement.setInt(4, heros.xp);
        statement.setInt(5, heros.vie);
        statement.setInt(6, heros.endurance);
        statement.setInt(7, heros.mp);
        statement.setInt(8, heros.force);
        statement.setInt(9, heros.intelligence);
        statement.setInt(10, heros.agilite);
        statement.setInt(11, heros.souls);
        statement.setInt(12, heros.PositionX);
        statement.setInt(13, heros.PositionY);
        statement.setString(14, heros.weapon.getNomArme());
        statement.setString(15, heros.armure.getNoms());
        statement.setInt(16, 1);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }
    }

    // Récupérer les données du joueur dans la base de données

    public static Heros getDataPlayer(boolean debug, Heros heros) throws Exception {
        if (debug)
            System.out.println("Récupération des données du joueur");
        Connection connection = Connector();

        String sql = "SELECT * FROM player WHERE Id_joueur = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);

        ResultSet result = statement.executeQuery();
        if (debug)
            System.out.println("Données récupérées avec succès");

        while (result.next()){
            heros.name = result.getString("nom_player");
            heros.lvl = result.getInt("level");
            heros.xp = result.getInt("xp");
            heros.vie = result.getInt("hp");
            heros.endurance = result.getInt("endurance");
            heros.mp = result.getInt("mp");
            heros.force = result.getInt("p_force");
            heros.intelligence = result.getInt("inteligence");
            heros.agilite = result.getInt("agilite");
            heros.souls = result.getInt("souls");
            heros.PositionX = result.getInt("J_PositionX");
            heros.PositionY = result.getInt("J_PositionY");

        }
        return heros;
    }

    // Faire la meme chose mais pour les armes

    public static void saveDataArme(boolean debug, Arme arme) throws Exception {
        Connection connection = Connector();

        String sql = "INSERT INTO weapon (Nom_arme, degats, poids, valeur) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, arme.getNomArme());
        statement.setInt(2, arme.getDegats());
        statement.setInt(3, arme.getPoids());
        statement.setInt(4, arme.getValeur());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }
    }

    public static void updateDataArme(boolean debug, Arme arme) throws Exception {
        Connection connection = Connector();

        String sql = "UPDATE weapon SET Nom_arme = ?, degats = ?, poids = ?, valeur = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, arme.getNomArme());
        statement.setInt(2, arme.getDegats());
        statement.setInt(3, arme.getPoids());
        statement.setInt(4, arme.getValeur());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }
    }

    public static void getDataArme(Arme arme) throws Exception {
        Connection connection = Connector();

        String sql = "SELECT * FROM weapon WHERE Nom_arme = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, arme.getNomArme());

        ResultSet result = statement.executeQuery();

        while (result.next()){
            arme.setNom(result.getString("Nom_arme"));
            arme.setDegats(result.getInt("degats"));
            arme.setPoids(result.getInt("poids"));
            arme.setValeur(result.getInt("valeur"));
        }
    }

    // Faire la meme chose mais pour l'invetaire

    public static void saveDataInventory(boolean debug, ArrayList<Objects> inventaire, Heros heros) throws Exception {
        Connection connection = Connector();

        String sql = "INSERT INTO Inventaire (Id_Inventaire, Id_joueur) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, 1);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }
    }

    public static void updateDataInventory(boolean debug, ArrayList<Objects> inventaire, Heros heros) throws Exception {
        Connection connection = Connector();

        String sql = "UPDATE inventaire SET Id_Inventaire = ?, Id_joueur = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, 1);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }
    }

    // Sauvegarder ce que Contient l'inventaire

    public static void saveDataInventoryContent(boolean debug, ArrayList<Objects> inventaire, Heros heros, Arme arme, Armure armure, Consommable consommable) throws Exception {
        Connection connection = Connector();

        String sql = "INSERT INTO Contient_Consommable (Id_Inventaire, Nom_consommable, quantite) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setString(2, consommable.getNom());
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i).getNom().equals(consommable.getNom())) {
                statement.setInt(3, inventaire.get(i).getQuantite());
            }
        }

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }

        sql = "INSERT INTO Contient_Arme (Nom_arme, Id_Inventaire, quantite) VALUES (?, ?, ?)";

        statement = connection.prepareStatement(sql);

        statement.setString(1, inventaire.get(2).getNom());
        statement.setInt(2, 1);
        statement.setInt(3, heros.weapon.getQuantite());

        rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }

        sql = "INSERT INTO Contient_Armure (Id_Inventaire, Nom_armure, quantite) VALUES (?, ?, ?)";

        statement = connection.prepareStatement(sql);

        statement.setInt(1, 1);
        statement.setString(2, inventaire.get(3).getNom());
        statement.setInt(3, heros.armure.getQuantite());

        rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }
    }

    public static void updateDataInventoryContent(boolean debug, ArrayList<Objects> inventaire, Heros heros, Arme arme, Armure armure, Consommable consommable) throws Exception {
        Connection connection = Connector();

        String sql = "UPDATE Contient_Consommable SET Id_Inventaire = ?, Nom_consommable = ?, quantite = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setString(2, consommable.getNom());
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i).getNom().equals(consommable.getNom())) {
                statement.setInt(3, inventaire.get(i).getQuantite());
            }
        }

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }

        sql = "UPDATE Contient_Arme SET Nom_arme = ?, Id_Inventaire = ?, quantite = ?";

        statement = connection.prepareStatement(sql);

        statement.setString(1, inventaire.get(2).getNom());
        statement.setInt(2, 1);
        statement.setInt(3, heros.weapon.getQuantite());

        rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }

        sql = "UPDATE Contient_Armure SET Id_Inventaire = ?, Nom_armure = ?, quantite = ?";

        statement = connection.prepareStatement(sql);

        statement.setInt(1, 1);
        statement.setString(2, inventaire.get(3).getNom());
        statement.setInt(3, heros.armure.getQuantite());

        rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }
    }
    // Faire la meme chose mais pour les consommables

    public static void saveDataConsommable(boolean debug, Consommable consommable, Heros heros) throws Exception {
        Connection connection = Connector();

        String sql = "INSERT INTO consommable (Nom_consommable, Souls, poids, hp, mp, endurance, p_force, intelligence, agilite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, consommable.getNoms());
        statement.setInt(2, consommable.getValeur());
        statement.setInt(3, consommable.getPoids());
        statement.setInt(4, consommable.getHp());
        statement.setInt(5, consommable.getMp());
        statement.setInt(6, consommable.getEndurance());
        statement.setInt(7, consommable.getForce());
        statement.setInt(8, consommable.getIntelligence());
        statement.setInt(9, consommable.getAgilite());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }
    }

    public static void updateDataConsommable(boolean debug, Consommable consommable, Heros heros) throws Exception {
        Connection connection = Connector();

        String sql = "UPDATE consommable SET Nom_consommable = ?, Souls = ?, poids = ?, hp = ?, mp = ?, endurance = ?, p_force = ?, intelligence = ?, agilite = ? WHERE Nom_consommable = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, consommable.getNoms());
        statement.setInt(2, consommable.getValeur());
        statement.setInt(3, consommable.getPoids());
        statement.setInt(4, consommable.getHp());
        statement.setInt(5, consommable.getMp());
        statement.setInt(6, consommable.getEndurance());
        statement.setInt(7, consommable.getForce());
        statement.setInt(8, consommable.getIntelligence());
        statement.setInt(9, consommable.getAgilite());
        statement.setString(10, consommable.getNoms());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }
    }

    // Delete All save

    public static void deleteAllSave(boolean debug) throws Exception {
        Connection connection = Connector();

        String sql = "DELETE FROM inventaire";
        String sql5 = "DELETE FROM consommable";
        String sql8 = "DELETE FROM weapon";
        String sql4 = "DELETE FROM armure";
        String sql2 = "DELETE FROM Contient_Consommable";
        String sql6 = "DELETE FROM Contient_Arme";
        String sql7 = "DELETE FROM Contient_Armure";
        String sql3 = "DELETE FROM Player";
        String sql1 = "DELETE FROM map";


        PreparedStatement statement2 = connection.prepareStatement(sql2);
        PreparedStatement statement = connection.prepareStatement(sql);
        PreparedStatement statement3 = connection.prepareStatement(sql3);
        PreparedStatement statement4 = connection.prepareStatement(sql4);
        PreparedStatement statement5 = connection.prepareStatement(sql5);
        PreparedStatement statement6 = connection.prepareStatement(sql6);
        PreparedStatement statement7 = connection.prepareStatement(sql7);
        PreparedStatement statement8 = connection.prepareStatement(sql8);
        PreparedStatement statement1 = connection.prepareStatement(sql1);

        int rowsDeleted2 = statement2.executeUpdate();
        int rowsDeleted = statement.executeUpdate();
        int rowsDeleted3 = statement3.executeUpdate();
        int rowsDeleted4 = statement4.executeUpdate();
        int rowsDeleted5 = statement5.executeUpdate();
        int rowsDeleted6 = statement6.executeUpdate();
        int rowsDeleted7 = statement7.executeUpdate();
        int rowsDeleted8 = statement8.executeUpdate();
        int rowsDeleted1 = statement1.executeUpdate();

        if (rowsDeleted > 0) {
            if (debug)
            System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted2 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted3 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted4 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted5 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted6 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted7 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted8 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
        if (rowsDeleted1 > 0) {
            if (debug)
                System.out.println("Donnée supprimée avec succès");
        }
    }

    // Save Village Position

    public static void saveVillagePosition(boolean debug, int x, int y, int i) throws Exception {
        Connection connection = Connector();

        String sql = "INSERT INTO map (PosX, PosY, Type) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, x);
        statement.setInt(2, y);
        statement.setString(3, "Village" + i);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }
    }

    // Update Village Position

    public static void updateVillagePosition(boolean debug, int x, int y, int i) throws Exception {
        Connection connection = Connector();

        String sql = "UPDATE map SET PosX = ?, PosY = ? WHERE Type = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, x);
        statement.setInt(2, y);
        statement.setString(3, "Village" + i);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }
    }

    // Get Village Position

    public static int[] getVillagePosition(boolean debug, int i) throws Exception {
        Connection connection = Connector();

        String sql = "SELECT PosX, PosY FROM map WHERE Type = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Village" + i);

        ResultSet result = statement.executeQuery();
        int[] position = new int[2];
        while (result.next()) {
            position[0] = result.getInt("PosX");
            position[1] = result.getInt("PosY");
        }
        if (debug)
            System.out.println("Donnée récupérée avec succès");
        return position;
    }

    // Save Armure

    public static void saveArmure(boolean debug, Armure armure) throws Exception {
        Connection connection = Connector();

        String sql = "INSERT INTO armure (nom_armure, defense, poids, valeur) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, armure.getNoms());
        statement.setInt(2, armure.getDefense());
        statement.setInt(3, armure.getPoids());
        statement.setInt(4, armure.getValeur());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            if (debug)
                System.out.println("Donnée sauvegardée avec succès");
        }
    }

    // Update Armure

    public static void updateArmure(boolean debug, Armure armure) throws Exception {
        Connection connection = Connector();

        String sql = "UPDATE armure SET nom_armure = ?, defense = ?, poids = ?, valeur = ? WHERE nom_armure = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, armure.getNoms());
        statement.setInt(2, armure.getDefense());
        statement.setInt(3, armure.getPoids());
        statement.setInt(4, armure.getValeur());
        statement.setString(5, armure.getNoms());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            if (debug)
                System.out.println("Donnée mise à jour avec succès");
        }
    }

    // Get Armure

    public static Armure getDateArmure(boolean debug, Armure armure) throws Exception {
        Connection connection = Connector();

        String sql = "SELECT * FROM armure WHERE nom_armure = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, armure.getNoms());

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            armure.setNoms(result.getString("nom_armure"));
            armure.setDefense(result.getInt("defense"));
            armure.setPoids(result.getInt("poids"));
            armure.setValeur(result.getInt("valeur"));
        }
        if (debug)
            System.out.println("Donnée récupérée avec succès");
        return armure;
    }

    // Inventory Content Consommable

    public static void saveInventoryContentConsommable(boolean debug, ArrayList<Objects> inventaire) throws Exception {
        Connection connection = Connector();
        for (int i = 0; i < inventaire.size(); i++) {
            if (inventaire.get(i).getClass().getSimpleName().equals("Consommable")) {
                String sql = "INSERT INTO Contient_Consommable (Id_Inventaire, Nom_consommable, quantite) VALUES (?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, 1);
                statement.setString(2, inventaire.get(i).getNoms());
                statement.setInt(3, inventaire.get(i).getQuantite());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    if (debug)
                        System.out.println("Donnée sauvegardée avec succès");
                }
            }
        }
    }

    // Inventory content Consommable Update

    public static void updateInventoryContentConsommable(boolean debug, Heros heros) throws Exception {
        Connection connection = Connector();
        for (int i = 0; i < heros.inventory.size(); i++) {
            if (heros.inventory.get(i).getClass().getSimpleName().equals("Consommable")) {
                String sql = "UPDATE Contient_Consommable SET quantite = ? WHERE Nom_consommable = ?";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, heros.inventory.get(i).getQuantite());
                statement.setString(2, heros.inventory.get(i).getNoms());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    if (debug)
                        System.out.println("Donnée mise à jour avec succès");
                }
            }
        }
    }

    // Inventory content Consommable Get

    public static void getInventoryContentConsommable(boolean debug, Instancte instance, Heros heros) throws Exception {
        Connection connection = Connector();

        String sql = "SELECT * FROM Contient_Consommable WHERE Id_Inventaire = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, 1);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            if (result.getString("Nom_consommable").equals("Potion_de_vie")) {
                instance.SetQuantiteConsommable(heros, "Potion_de_vie", result.getInt("quantite"));

            } else if (result.getString("Nom_consommable").equals("Potion_de_mana")) {
                instance.SetQuantiteConsommable(heros, "Potion_de_mana", result.getInt("quantite"));

            } else if (result.getString("Nom_consommable").equals("Potion_de_force")) {
                instance.SetQuantiteConsommable(heros, "Potion_de_force", result.getInt("quantite"));

            } else if (result.getString("Nom_consommable").equals("Potion_d_intelligence")) {
                instance.SetQuantiteConsommable(heros, "Potion_d'intelligence", result.getInt("quantite"));

            } else if (result.getString("Nom_consommable").equals("Potion_d_endurance")) {
                instance.SetQuantiteConsommable(heros, "Potion_d'endurance", result.getInt("quantite"));
            }
        }
        if (debug)
            System.out.println("Donnée récupérée avec succès");
    }
}