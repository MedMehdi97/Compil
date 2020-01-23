package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Declaration extends Instruction {
    private String nom;
    private int deplacement;

    protected Declaration(int n, String nom, int deplacement) {
        super(n);
        this.nom = nom;
        this.deplacement=deplacement;
    }

    @Override
    public void verifier(){
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder(" # reservation de l'espace pour une variable\n");
        code.append("addi $sp, $sp, -4\n"); //Plus tars il faudra calculer le deplacement
        return code.toString();
    }
}
