package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {
    protected Expression expG;
    protected Expression expD;
    protected String oper;

    /**
     * Constructeur d'une affectation (constante entière ou une variable)
     * @param n
     */
    protected Expression(int n) {
        super(n) ;
    }

    /**
     * Constructeur d'une expression
     * @param n Numéro de la ligne
     * @param expG opérand gauche
     * @param expD opérand droit
     * @param oper opérateur
     */
    public Expression(int n, Expression expG, Expression expD, String oper) {
        super(n);
        this.expG = expG;
        this.expD = expD;
        this.oper = oper;
    }

}
