package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("#Instruction Ecrire \n");
        code.append("li $v0, 1\n");
        code.append("li $a0, "+this.exp.toMIPS()+" \n");
        code.append("syscall\n");
        return code.toString();
    }

}
