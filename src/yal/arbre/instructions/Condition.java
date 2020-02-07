package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.ExpressionLogique;
import yal.tds.Tds;

public class Condition extends Instruction {

    protected ExpressionLogique exp;
    protected ArbreAbstrait blocalors;
    protected ArbreAbstrait blocSinon;

    /**
     *  Constructeur d'une condition (si alors)
     * @param n  numéro de ligne
     * @param exp  expression logic
     * @param blocalors   liste d'instructions
     */
    public Condition(int n, ExpressionLogique exp, ArbreAbstrait blocalors) {
        super(n);
        this.exp=exp;
        this.blocalors=blocalors;
    }

    public Condition(int n, ExpressionLogique exp, ArbreAbstrait blocalors, ArbreAbstrait blocSinon){
        super(n);
        this.exp=exp;
        this.blocalors=blocalors;
        this.blocSinon=blocSinon;
    }

    @Override
    public void verifier() {
         exp.verifier();
         blocalors.verifier();
         if (blocSinon != null){
             blocSinon.verifier();
         }
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("#Condition\n");
        // Appel de toMIPS de l'expression logique
        code.append(this.exp.toMIPS());
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        //vérification si la condition est fausse
        code.append("beqz $v0, sinon" + Tds.getInstance().getCptCodition() + "\n");
        //Appel de la fonction toMIPS pour le bloc alors
        code.append(blocalors.toMIPS());
        code.append("b finCond"+Tds.getInstance().getCptCodition()+"\n");
        code.append("sinon" + Tds.getInstance().getCptCodition() + ": \n");
        if(blocSinon != null) {
            code.append(this.blocSinon.toMIPS());
        }
        code.append("finCond"+Tds.getInstance().getCptCodition()+":");
        Tds.getInstance().addCptCondition();

        return code.toString();
    }
}
