package yal.arbre.expressions;

import yal.exceptions.FonctionRecursiveException;
import yal.tds.Tds;
import yal.tds.entree.EntreeFonction;
import yal.tds.symbole.Symbole;

import java.util.ArrayList;

public class AppelFonction extends ExpressionArithmetique {
     String nom;
     ArrayList<ExpressionArithmetique> param;
    public AppelFonction(int n, String nom, ArrayList<ExpressionArithmetique> param) {
        super(n);
        this.nom=nom;
        this.param=param;
    }

    @Override
    public void verifier() {
        Symbole s= Tds.getInstance().identifierMain(new EntreeFonction(this.nom,this.noLigne,this.param.size()));
        //Vérifier si la fonction n'appelle pas elle même
        if(s.getNumBloc()==Tds.getInstance().getNumBlocTableLocale()){
            Tds.getInstance().ajouterException(new FonctionRecursiveException(this.noLigne," Appel recursive de la Fonction " + this.nom ));
        }
        for (ExpressionArithmetique e:this.param) {
            e.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("#Appel de fonction \n");
        //Empilement des paramètres de la fonction
        for (ExpressionArithmetique e:this.param) {
            code.append(e.toMIPS());
        }
        //Appell de la fonction nom+nbParam
        code.append("jal " + this.nom+this.param.size()+ "\n");
        //Mise à jour de l'appel cas d'une fonction avec paramètres
        if (this.param.size()!=0) {
            //Recupérer le résultat de la fonction dans $v0
            code.append("addi $sp, $sp, 4\n");
            code.append("lw $v0, 0($sp)\n");
            //Depiler les paramètres de la fonction
            code.append("addi $sp, $sp, "+this.param.size()*4+"\n");
            //Empiler à nouveau le resultat de retour de la fonction
            code.append("sw $v0, ($sp)\n");
            code.append("addi $sp,$sp, -4\n");
        }
        return code.toString();
    }
}
