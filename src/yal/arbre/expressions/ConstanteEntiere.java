package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {
    /**
     * Constructeur de la classe ConstanteEntiere
     * @param texte
     * @param n
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        StringBuilder code= new StringBuilder();
        code.append("li $v0, "+ this.cste +"\n"); //chargement de la valeur dans $v0
        code.append("sw $v0, 0($sp)\n");    //ranger $v0 dans la pile
        code.append("addi $sp, $sp, -4\n");
        return code.toString();
    }

    @Override
    public boolean isConstante() {
        return true;
    }
}
