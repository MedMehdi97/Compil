package yal.tds.entree;

public class EntreeFonction extends Entree {

    /**
     * Costructeur de la classe Entree
     *
     * @param n Nom de la fonction
     * @param l Numero de ligne de la fonction
     */
    public EntreeFonction(String n, int l) {
        super(n, l);
    }
    //Retourne 1 pour le typeFonction
    @Override
    public int entreeType() {
        return 1;
    }
}
