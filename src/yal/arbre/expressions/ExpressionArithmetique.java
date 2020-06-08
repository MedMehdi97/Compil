package yal.arbre.expressions;

import yal.exceptions.DivisionParZeroException;
import yal.tds.Tds;

public class ExpressionArithmetique extends Expression {
    boolean constante=false;
    /**
     * Constructeur d'une affectation (constante entière ou une variable)
     * @param n
     */
    protected ExpressionArithmetique(int n) {
        super(n);
    }
    /**
     *
     * Constructeur d'une expression
     * @param n Numéro de la ligne
     * @param expG opérand gauche
     * @param expD opérand droit
     * @param oper opérateur
     */
    public ExpressionArithmetique(int n, Expression expG, Expression expD, String oper) {
        super(n, expG, expD, oper);
    }

    @Override
    public void verifier() {
        this.expG.verifier();
        this.expD.verifier();
        //cas division par zéro
        if (this.oper.equals("/") && (this.expD.toString().equals("0"))){
            int cst=Integer.parseInt(this.expD.toString());
            if (cst==0) Tds.getInstance().ajouterException(new DivisionParZeroException(this.noLigne));
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("");
        // Géneration du code pour les deux expressions gauche et droite
        code.append(this.expG.toMIPS());
        code.append(this.expD.toMIPS());
        // Récupération des résultats des expressions dans $v0 et $t8
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $t8, 0($sp)\n");
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        switch (oper) { // opération entre les deux expressions
            case "+":
                code.append("add $v0, $v0, $t8\n");
                break;
            case "-":
                code.append("sub $v0, $v0, $t8\n");
                break;
            case "*":
                code.append("mult $v0, $t8\n");
                code.append("mflo $v0\n");
                break;
            case "/":
                code.append("beq $t8, 0, divZero"+Tds.getInstance().getCptDivZer()+"\n");
                code.append("div $v0, $t8\n");
                //cas ou l'expression droite n'est pas égale à zéro
                code.append("mflo $v0\n\n");
                code.append("b finDivZero"+Tds.getInstance().getCptDivZer()+"\n");
                code.append("divZero"+Tds.getInstance().getCptDivZer()+":\n");
                code.append("li $v0, 4\n");
                code.append("la $a0, ErreurDivisionParZero\n");
                code.append("syscall\n");
                code.append("b end\n");
                code.append("finDivZero"+Tds.getInstance().getCptDivZer()+":\n");
                Tds.getInstance().addCptDivZer();
                break;
        }
        //empiler le resultat dans la pile
        code.append("sw $v0, 0($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        return code.toString();
    }
    public String toString(){
        return "";
    }
    public boolean isConstante(){
        return false;
    }

}
