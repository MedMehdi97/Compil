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
        if (this.exp instanceof Idf){
            code.append("lw $v0, "+ this.exp.toMIPS()+"\n");
        }
        else {
            code.append("li $v0, "+ this.exp.toMIPS()+"\n");
        }
        code.append("move $a0, $v0\n"); // $a0 <- $v0
        code.append("li $v0, 1\n"); //code du print
        code.append("syscall\n");
        return code.toString();
    }

}
