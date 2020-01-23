package yal.arbre.expressions;

public class Idf extends Expression {
    private String nom;
    private String deplacement;
    protected Idf(int n) {
        super(n);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(String deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
