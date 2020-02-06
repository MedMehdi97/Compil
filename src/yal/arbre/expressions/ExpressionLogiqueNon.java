package yal.arbre.expressions;

public class ExpressionLogiqueNon extends Expression {
    ExpressionLogique exp;
    protected ExpressionLogiqueNon(int n, ExpressionLogique exp) {
        super(n);
        this.exp=exp;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
