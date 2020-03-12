package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionArithmetique;
import yal.exceptions.RetourneException;
import yal.tds.Tds;

public class Retourne extends Instruction {
    protected ExpressionArithmetique exp;

    /**
     * Constructerur de la classe Retourne
     * @param n numéro de la ligne
     * @param exp Exression arithmétique de type entier
     */
    public Retourne(int n, ExpressionArithmetique exp) {
        super(n);
        this.exp=exp;
    }

    @Override
    public void verifier() {
        if (Tds.getInstance().getNumBlocTableLocale()==0){
            Tds.getInstance().ajouterException(new RetourneException(this.noLigne,"Instruction retourne dans le bloc Main"));
        }
        Tds.getInstance().getTableLocaleCourante().addNbRetour();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("#Retour de la fonction\n");
        //Géneration du code Mips de l'expression à retourner
        code.append(this.exp.toMIPS());
        //recupérer la valeur de retour dans $v0
        code.append("addi $sp,$sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        //Mise à jour de la pile et suppression des variables locals
        int nbDep=8+4*Tds.getInstance().getCptDep();
        code.append("addi $sp,$sp, "+nbDep+"\n");
        // Recupérer la base local précedente dans $t8
        code.append("lw $t8, 0($sp)\n");
        //recupérer l'adresse de retour
        code.append("addi $sp,$sp, 4\n");
        code.append("lw $ra,($sp)\n");
        //rénitaliser $s2
        code.append("move $s2, $t8\n");
        // Empiler la valeur de retour
        code.append("sw $v0, ($sp)\n");
        code.append("addi $sp,$sp, -4\n");
        // Retour à l'appel de la fonction
        code.append("jr $ra\n");
        return code.toString();
    }
}
