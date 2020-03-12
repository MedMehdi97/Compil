package yal.tds.entree;

public abstract class Entree {
    protected String nom;   // nom de l'entree
    protected int noLigne; // numéro de ligne

    /**
     * Costructeur de la classe Entree
     * @param n Nom de l'entree
     * @param l Numero de ligne de l'entree
     */
    public Entree(String n, int l) {
        this.nom = n;
        this.noLigne= l;
    }

    public int getLigne() {
        return this.noLigne;
    }
    public String getNom() {
        return this.nom;
    }
    public abstract int entreeType();
    public abstract int getNbParam();
}
