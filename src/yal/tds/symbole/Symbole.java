package yal.tds.symbole;

import yal.arbre.expressions.ExpressionArithmetique;

public abstract class Symbole {

    public abstract int getDeplacement();
    public  abstract int getNumBloc();
    public abstract ExpressionArithmetique getTaille();
    public abstract boolean tableau();

}
