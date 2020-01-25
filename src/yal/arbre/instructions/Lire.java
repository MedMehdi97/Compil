package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Lire extends Instruction{
    protected Expression exp ;
    public Lire(int n, Expression e) {
        super(n);
        exp = e ;
    }

    @Override
    public void verifier() {
         this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("#Instruction Lecture\n");
        code.append("li $v0, 5\n");   // Code du read pour un entier
        code.append("syscall\n");     // recupération du résultat dans $v0
        code.append("#Empiler la variable\n");
        code.append("sw $v0, "+exp.toMIPS()+"\n");
        return code.toString();
    }
}
