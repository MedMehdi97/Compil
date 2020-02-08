package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.ExpressionLogique;
import yal.tds.Tds;

public class Boucle extends  Instruction{
    protected ExpressionLogique exp;
    protected ArbreAbstrait listeInstructions;

    /**
     * Constructeur de la classe Boucle
     * @param n   numéro de ligne
     * @param exp  expression logique
     * @param listeInstructions liste d'instructions
     */
    public Boucle(int n, ExpressionLogique exp, ArbreAbstrait listeInstructions) {
        super(n);
        this.exp=exp;
        this.listeInstructions=listeInstructions;
    }

    @Override
    public void verifier() {
        this.exp.verifier();
        this.listeInstructions.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("");
        code.append("# Boucle Tant que \n");
        //étiquete du tant que
        code.append("tantque"+ Tds.getInstance().getCptBoucle()+":\n");
        //géneration du code Mips de la condition
        code.append(this.exp.toMIPS());
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        //branchement vers la fin si la condition est fausse
        code.append("beqz $v0, fintantque" + Tds.getInstance().getCptBoucle() + "\n");
        //géneration du code Mips des instructions
        code.append(this.listeInstructions.toMIPS());
        //branchement vers tantque
        code.append("b tantque" + Tds.getInstance().getCptBoucle() + "\n");
        //etiquete fintantque
        code.append("fintantque" +Tds.getInstance().getCptBoucle()  + ": \n");
        return code.toString();
    }
}
