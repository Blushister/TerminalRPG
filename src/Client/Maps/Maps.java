package Client.Maps;

import Client.Joueur.Ami.Heros;
import Client.Joueur.Enemi.Vilain;
import Client.Maps.Donjon.Donjon;
import Client.Network.Manager;
import Client.Objets.Instancte;

import java.util.Objects;

public class Maps {

    boolean debug = false;
    String[][] map = new String[30][30];
    // Map de 30 par 30

    // "~" = vide

    // | = mur cotée

    // - = mur haut et bas

    // ? = heros

    // ! = vilain



    // Constructeur

    public Maps() {

    }
    // Getters

    public String[][] getMap() {
        return this.map;
    }

    // Methods

    public String[][] creationMap() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == 0 || i == 29) {
                    this.map[i][j] = " ⸏ ";
                } else if (j == 0 || j == 29) {
                    this.map[i][j] = " | ";
                } else {
                    this.map[i][j] = " ▢ ";
                }
            }
        }
        return this.map;
    }
    public void afficherMap() {
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }

    public void setHerosPosition(Heros heros, int y, int x) {
        this.map = creationMap();
        this.map[y][x] = " ⦿ ";
        heros.setPositionX(x);
        heros.setPositionY(y);

    }
    public void setVillageRandomNb (int iteation) throws Exception {
        // Set village at random position3
        for (int i = 0; i < iteation; i++) {
            int x = (int) (Math.random() * 29);
            int y = (int) (Math.random() * 29);
            this.setVillagePosition(x, y);
            Manager.saveVillagePosition(debug, x, y, i);
        }
    }

    public void setVillagePosition (int x, int y) {
        // Set village at random position3
        if (Objects.equals(this.map[y][x], " ⸏ ") || Objects.equals(this.map[y][x], " | ")) {
            int x2= (int) (Math.random() * 29);
            int y2 = (int) (Math.random() * 29);
            this.setVillagePosition(x2, y2);
        } else {
            if (debug)
                System.out.println(x + " " + y);
            this.map[y][x] = " ✖ ";
        }
    }

    public void setDonjonPosition (int x, int y) {
        // Set village at random position3
        if (Objects.equals(this.map[y][x], " ⸏ ") || Objects.equals(this.map[y][x], " | ")) {
            int x2= (int) (Math.random() * 29);
            int y2 = (int) (Math.random() * 29);
            this.setDonjonPosition(x2, y2);
        } else {
            this.map[y][x] = " ☢ ";
        }
    }

    // Deplacement du heros

    public void allerGauche(Heros heros, Instancte instancte, Vilain vilain, Donjon donjon) {
        switch (this.map[heros.getPositionY()][heros.getPositionX() - 1]) {
            case " ▢ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ❎ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ✖ ";
                } else if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY()][heros.getPositionX() - 1] = " ⦿ ";
                heros.setPositionX(heros.getPositionX() - 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ✖ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY()][heros.getPositionX() - 1] = " ❎ ";
                heros.setPositionX(heros.getPositionX() - 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ☢ ":
                this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                this.map[heros.getPositionY()][heros.getPositionX() - 1] = " ☗ ";
                heros.setPositionX(heros.getPositionX() - 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
        }
    }

    public void allerDroite(Heros heros, Instancte instancte, Vilain vilain, Donjon donjon) {
        switch (this.map[heros.getPositionY()][heros.getPositionX() + 1]) {
            case " ▢ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ❎ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ✖ ";
                } else if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY()][heros.getPositionX() + 1] = " ⦿ ";
                heros.setPositionX(heros.getPositionX() + 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ✖ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY()][heros.getPositionX() + 1] = " ❎ ";
                heros.setPositionX(heros.getPositionX() + 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ☢ ":
                this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                this.map[heros.getPositionY()][heros.getPositionX() + 1] = " ☗ ";
                heros.setPositionX(heros.getPositionX() + 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
        }
    }

    public void allerHaut(Heros heros, Instancte instancte, Vilain vilain, Donjon donjon) {
        switch (this.map[heros.getPositionY() - 1][heros.getPositionX()]) {
            case " ▢ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ❎ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ✖ ";
                } else if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY() - 1][heros.getPositionX()] = " ⦿ ";
                heros.setPositionY(heros.getPositionY() - 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ✖ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY() - 1][heros.getPositionX()] = " ❎ ";
                heros.setPositionY(heros.getPositionY() - 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ☢ ":
                this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                this.map[heros.getPositionY() - 1][heros.getPositionX()] = " ☗ ";
                heros.setPositionY(heros.getPositionY() - 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
        }
    }

    public void allerBas(Heros heros, Instancte instancte, Vilain vilain, Donjon donjon) {
        switch (this.map[heros.getPositionY() + 1][heros.getPositionX()]) {
            case " ▢ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ❎ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ✖ ";
                } else if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY() + 1][heros.getPositionX()] = " ⦿ ";
                heros.setPositionY(heros.getPositionY() + 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ✖ ":
                if (this.map[heros.getPositionY()][heros.getPositionX()].equals(" ☗ ")) {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ☢ ";
                } else {
                    this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                }
                this.map[heros.getPositionY() + 1][heros.getPositionX()] = " ❎ ";
                heros.setPositionY(heros.getPositionY() + 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
            case " ☢ ":
                this.map[heros.getPositionY()][heros.getPositionX()] = " ▢ ";
                this.map[heros.getPositionY() + 1][heros.getPositionX()] = " ☗ ";
                heros.setPositionY(heros.getPositionY() + 1);
                instancte.eventMap(this.map, heros, vilain, donjon);
                break;
        }
    }
}