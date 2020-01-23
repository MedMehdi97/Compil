package yal.tds.symbole;

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
        return 0;
    }
}
