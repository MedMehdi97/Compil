package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionLogique;
import yal.arbre.expressions.Idf;
import yal.tds.Tds;

public class Ecrire extends Instruction {

    protected Expression exp ;
    protected int c;

    /**
     * Constructeur de la classe Ecrire
     * @param e   l'expression à afficher
     * @param n   Numéro de la ligne
     */
    public Ecrire (Expression e, int n, int c) {
        super(n) ;
        this.c=c;
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
        if (c==1) {
            //cas d'expression arithmétique
            code.append(this.exp.toMIPS());
            code.append("addi $sp, $sp, 4\n");
            code.append("lw $v0, 0($sp)\n");   //recupération de la valeur à afficher de la pile
            code.append("move $a0, $v0\n"); // $a0 <- $v0
            code.append("li $v0, 1\n"); //code du print d'un entier
        }else {
            //cas d'expression logique
            code.append(this.exp.toMIPS());
            code.append("addi $sp, $sp, 4\n");
            code.append("lw $v0, 0($sp)\n");
            code.append("move $t8, $v0\n");   // $v0 <- $t8

            code.append("li $v0, 4\n");       // code du print d'une chaine de caractères
            // test si le resultat de l'expression logique est vraie ou faux
            code.append("beqz $t8, ecrire"+ Tds.getInstance().getCptEcrie() + "\n");
            // cas de l'expression est vraie
            code.append("la $a0, vrai\n");
            code.append("b finecrire"+Tds.getInstance().getCptEcrie()+"\n");
            // cas pi l'expression est fausse
            code.append("ecrire" + Tds.getInstance().getCptEcrie() + ": \n");
            code.append("la $a0, faux\n");
            code.append("finecrire"+Tds.getInstance().getCptEcrie()+":\n");
            Tds.getInstance().addCptEcrire();
        }
        code.append("syscall\n");
        //Génération du saut de ligne dans le MIPS
        code.append("\n#Saut de ligne\n");
        code.append("li $v0, 4\n"); // Code du print pour le retour à la ligne
        code.append("la $a0, ln\n");  // Saut à la ligne
        code.append("syscall\n\n");   // Appel du saut de ligne
        return code.toString();
    }

}
