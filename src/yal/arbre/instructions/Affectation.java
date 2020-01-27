package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Affectation extends Instruction {
    private Idf idf;
    private Expression exp;
    public Affectation(int n, Idf idf, Expression exp) {
        super(n);
        this.idf=idf;
        this.exp=exp;
    }

    @Override
    public void verifier() {
        this.idf.verifier();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("#Affectation\n");
        code.append(this.exp.toMIPS());
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");   //recupération de la valeur à affecter depuis la pile
        code.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");
        return code.toString();
    }
}
