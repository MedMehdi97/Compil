package yal.tds.symbole;

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
    public int getDeplacement() {
        return 0;
    }
}
