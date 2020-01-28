package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Ecrire extends Instruction {

    protected Expression exp ;

    /**
     * Constructeur de la classe Ecrire
     * @param e   l'expression à afficher
     * @param n   Numéro de la ligne
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    /**
     * Fonction verifier qui appelle la fonction verifier de l'expression à afficher
     */
    @Override
    public void verifier() {
        this.exp.verifier();
    }

    /**
     * Géneration du code de l'instruction écrire
     * @return
     */
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
