package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;
import yal.exceptions.TableauException;
import yal.tds.Tds;

public class Lire extends Instruction{
    protected Idf idf ;

    /**
     * Constructeur de la classe Lire
     * @param n   Numéro de la ligne
     * @param idf  Idf à lire
     */
    public Lire(int n, Idf idf) {
        super(n);
        this.idf=idf;
    }

    /**
     * fonction vérifier afin de controler si la variable est déclaré
     */
    @Override
    public void verifier() {
         this.idf.verifier();
         if (this.idf.isTab()){ //Déclenche une exception si l'idf est un tableau
             Tds.getInstance().ajouterException(new TableauException(this.noLigne,"Lecture Impossible Variable "+this.idf.getNom()+" est un tableau"));
         }
    }

    /**
     * Géneration du code pout la Lecture d'une variable
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("#Instruction Lecture\n");
        code.append("li $v0, 5\n");   // Code du read pour un entier
        code.append("syscall\n");     // recupération du résultat dans $v0
        code.append("#Empiler la variable\n");
        code.append("sw $v0, "+this.idf.getDeplacement()+"($s7)\n");
        return code.toString();
    }
}
