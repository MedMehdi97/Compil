package yal.tds.entree;

public class EntreeFonction extends Entree {
     int nbParam;
    /**
     * Costructeur de la classe Entree
     *
     * @param n Nom de la fonction
     * @param l Numero de ligne de la fonction
     */
    public EntreeFonction(String n, int l, int nbParam) {
        super(n, l);
        this.nbParam=nbParam;
    }
    //Retourne 1 pour le typeFonction
    @Override
    public int entreeType() {
        return 1;
    }

    @Override
    public int getNbParam() {
        return this.nbParam;
    }
}
