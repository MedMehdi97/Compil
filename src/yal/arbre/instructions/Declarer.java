package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;
import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Declarer extends Instruction {
    private String nom;
    private EntreeVariable e;
    private int deplacement;

    public Declarer(int n, String nom, EntreeVariable e) {
        super(n);
        this.nom = nom;
        this.e=e;
    }

    @Override
    public void verifier(){
        Symbole s= Tds.getInstance().identifier(this.e);
        this.deplacement=s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder(" # reservation de l'espace pour une variable\n");
        code.append("addi $sp, $sp, -4\n"); //Plus tars il faudra calculer le deplacement
        return code.toString();
    }
}
