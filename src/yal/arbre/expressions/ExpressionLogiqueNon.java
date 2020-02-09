package yal.arbre.expressions;

import yal.tds.Tds;

public class ExpressionLogiqueNon extends ExpressionLogique {
    ExpressionLogique exp;

    /**
     * Constructeur de la classe ExpressionLogiqueNon
     * @param n  numéro de la ligne
     * @param exp  expression logique
     */
    public ExpressionLogiqueNon(int n, ExpressionLogique exp) {
        super(n);
        this.exp=exp;
    }

    @Override
    public void verifier() {
     this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("");
        //géneration du code de l'expression logique
        code.append(this.exp.toMIPS());
        //recupération du résultat depuis $v0
        code.append("addi $sp, $sp, 4 \n");
        code.append("lw $v0, 0($sp)\n");
        //branchement dans l'étiquette si $v0=0 (cas faux)
        code.append("beqz $v0, nonLog"+ Tds.getInstance().getCptNonLog()+"\n");
        //cas ou l'expression est vraie
        code.append("li $v0, 0\n");
        //branchement vers la fin
        code.append("b finNon"+Tds.getInstance().getCptNonLog()+"\n\n");
        code.append("nonLog"+Tds.getInstance().getCptNonLog()+":\n");
        code.append("li $v0, 1 \n");
        code.append("finNon"+Tds.getInstance().getCptNonLog()+":\n");
        //empiler le resultat
        code.append("sw $v0, 0($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        //incrémenter le compteur des branchements
         Tds.getInstance().addCptNonlog();
        return code.toString();
    }
}
