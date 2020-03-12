package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Affectation extends Instruction {
    private Idf idf;
    private Expression exp;

    /**
     * Constructeur de la classe Affectation
     * @param n Numéro de la ligne
     * @param idf  Idf partie gauche de l'affectation
     * @param exp  Exp partie droite de l'affectation
     */
    public Affectation(int n, Idf idf, Expression exp) {
        super(n);
        this.idf=idf;
        this.exp=exp;
    }

    /**
     * Fonction vérifier qui appelle les fonctions verifier des deux parties de l'affectation
     */
    @Override
    public void verifier() {
        this.idf.verifier();
        this.exp.verifier();
    }

    /**
     * Fonction qui génere le code d'une affectation
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("#Affectation\n");
        code.append(this.exp.toMIPS());
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");   //recupération de la valeur à affecter depuis la pile
        code.append("sw $v0, " + this.idf.getDeplacement());
        //Variable global ou bien variable local
        if (this.idf.isMain()==true){code.append("($s7)\n");} else {code.append("($s2)\n");}
        return code.toString();
    }
}
