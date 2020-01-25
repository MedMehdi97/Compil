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
        code.append("lw $v0, " + this.deplacement+"($s7)\n"); //chargement de la valeur dans $v0
        code.append("sw $v0, 0($sp)\n");  //stockage de la valeur dans la pile
        code.append("addi $sp, $sp, -4\n");
        return code.toString();
    }

    public int getDeplacement() {
        return deplacement;
    }
}
