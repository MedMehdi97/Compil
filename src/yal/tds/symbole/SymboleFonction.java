package yal.tds.symbole;

import yal.arbre.expressions.ExpressionArithmetique;

public class SymboleFonction extends Symbole {
    private int numBloc;

    public SymboleFonction(int numBloc){
        this.numBloc=numBloc;
    }
    @Override
    public int getNumBloc() {
        return this.numBloc;
    }

    @Override
    public ExpressionArithmetique getTaille() {
        return null;
    }

    @Override
    public boolean tableau() {
        return false;
    }


    @Override
    public int getDeplacement() {
        return 0;
    }
}
