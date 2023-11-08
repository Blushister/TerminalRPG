package Client.Objets;

public class Objects {

    String classe;
    protected String nom;
    int poids;
    int valeur;
    int quantite;
    int souls;


    public Objects(String classe, String nom, int poids, int valeur, int quantite, int souls) {
        this.classe = classe;
        this.nom = nom;
        this.poids = poids;
        this.valeur = valeur;
        this.quantite = quantite;
        this.souls = souls;
    }

    public String getNoms() {
        return this.nom;
    }

    public int getQuantite() {
        return this.quantite;
    }

    // Getters

    public String getClasse() {
        return this.classe;
    }

    public String getNom() {
        return this.nom;
    }

    public int getPoids() {
        return this.poids;
    }

    public int getValeur() {
        return this.valeur;
    }

    // Setters

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}
