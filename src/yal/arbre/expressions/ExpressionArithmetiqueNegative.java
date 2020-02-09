package yal.arbre.expressions;

public class ExpressionArithmetiqueNegative extends ExpressionArithmetique {

    ExpressionArithmetique exp;

    public ExpressionArithmetiqueNegative(int n, ExpressionArithmetique exp) {
        super(n);
        this.exp=exp;
    }

    @Override
    public void verifier() {
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("");
        // Géneration du code de l'expression
        code.append(this.exp.toMIPS());
        // Négation de l'expression en faisant l'opération exp-2*exp
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        code.append("li $t8, 2\n");
        code.append("mult $v0, $t8\n");
        code.append("mflo $t8\n");   //$t8<-$v0*2
        code.append("lw $v0, 0($sp)\n");
        code.append("sub $v0, $v0, $t8\n"); //$v0<-$v0-2*$v0
        //empiler le resultat dans la pile
        code.append("sw $v0, 0($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        return code.toString();
    }
}
