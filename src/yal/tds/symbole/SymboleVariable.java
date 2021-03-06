package yal.tds.symbole;

import yal.arbre.expressions.ExpressionArithmetique;

public class SymboleVariable extends Symbole {
    private int dep;

    /**
     * Constructeur de la classe SymboleVariable
     * @param d
     */
    public SymboleVariable(int d) {
        this.dep = d;
    }


    @Override
    public int getDeplacement() {
        return this.dep;
    }

    @Override
    public int getNumBloc() {
        return -1;
    }

    @Override
    public ExpressionArithmetique getTaille() {
        return null;
    }

    public boolean tableau(){
        return false;
    }
}
