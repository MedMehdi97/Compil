package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("#Instruction Ecrire \n");
        code.append(this.exp.toMIPS());
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");   //recupération de la valeur à afficher de la pile
        code.append("move $a0, $v0\n"); // $a0 <- $v0
        code.append("li $v0, 1\n"); //code du print
        code.append("syscall\n");
        return code.toString();
    }

}
