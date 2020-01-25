package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Lire extends Instruction{
    protected Idf idf ;
    public Lire(int n, Idf idf) {
        super(n);
        this.idf=idf;
    }

    @Override
    public void verifier() {
         this.idf.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("#Instruction Lecture\n");
        code.append("li $v0, 5\n");   // Code du read pour un entier
        code.append("syscall\n");     // recupération du résultat dans $v0
        code.append("#Empiler la variable\n");
        code.append("sw $v0, "+this.idf.getDeplacement()+"($s7)\n");
        return code.toString();
    }
}
