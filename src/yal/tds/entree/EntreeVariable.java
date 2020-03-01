package yal.tds.entree;

import yal.tds.entree.Entree;

public class EntreeVariable extends Entree {
    /**
     * Costructeur de la classe Entree
     *
     * @param n Nom de l'entree
     * @param l Numero de ligne de l'entree
     */
    public EntreeVariable(String n, int l) {
        super(n, l);
    }
     //Retourne 0 pour le type variable
    @Override
    public int entreeType() {
        return 0;
    }
}
