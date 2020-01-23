package yal.arbre.expressions;

public class Idf extends Expression {
    private String nom;
    private String deplacement;
    protected Idf(int n) {
        super(n);
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
