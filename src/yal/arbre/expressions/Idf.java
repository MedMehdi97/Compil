package yal.arbre.expressions;

import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Idf extends Expression {
    private String nom;
    private int deplacement;
    public Idf(int n, String nom) {
        super(n);
        this.nom=nom;
    }


    @Override
    public void verifier() {
        Symbole s = Tds.getInstance().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        this.deplacement = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("");
        code.append(this.deplacement+"($s7)"); //recupération du déplacement dans la pile
        return code.toString();
    }
}
