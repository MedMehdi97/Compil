package yal.arbre.expressions;

import yal.tds.Tds;
import yal.tds.entree.EntreeFonction;
import yal.tds.symbole.Symbole;

public class AppelFonction extends ExpressionArithmetique {
    public String nom;
    public AppelFonction(int n, String nom) {
        super(n);
        this.nom=nom;
    }

    @Override
    public void verifier() {
        Symbole s= Tds.getInstance().identifierMain(new EntreeFonction(this.nom,this.noLigne));
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder();
        //Appell de la fonction
        code.append("jal " + this.nom + "\n");
        return code.toString();
    }
}
