package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;
import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Declarer extends Instruction {
    private String nom;
    private EntreeVariable e;
    private int deplacement;

    /**
     * Constructeur de la classe Declarer
     * @param n  Numéro de ligne
     * @param nom Nom de la variable à lire
     * @param e   Entree de la variable à lire
     */
    public Declarer(int n, String nom, EntreeVariable e) {
        super(n);
        this.nom = nom;
        this.e=e;
    }

    @Override
    public void verifier(){

    }

    /**
     * Fonction qui génere le code d'une déclaration de variable
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("#reservation de l'espace pour une variable\n");
        code.append("addi $sp, $sp, -4\n");
        return code.toString();
    }
}
