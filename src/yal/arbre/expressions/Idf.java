package yal.arbre.expressions;

import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Idf extends ExpressionArithmetique {
    private String nom;
    private int deplacement;

    /**
     * Constructeur de la classe Idf
     * @param n Numéro de ligne
     * @param nom Nom
     */
    public Idf(int n, String nom) {
        super(n);
        this.nom=nom;
    }

    /**
     * fonction verifier controle si l'élement existe dans la tabl des symboles et initialise son deplacement
     */
    @Override
    public void verifier() {

            Symbole s = Tds.getInstance().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
            if (s.getDeplacement()==-1 && Tds.getInstance().getNumBlocTableLocale()!=0){
                //vérifier si la variable est déclaré dans le bloc main si on est dans un autre bloc
                s=Tds.getInstance().identifierMain(new EntreeVariable(this.nom,this.noLigne));
            }
            this.deplacement = s.getDeplacement();
    }

    /**
     * fonction qui gènere le code toMIPS afin d'utiliser Idf dans des instructions
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("");
        code.append("lw $v0, " + this.deplacement+"($s7)\n"); //chargement de la valeur dans $v0
        code.append("sw $v0, 0($sp)\n");  //stockage de la valeur dans la pile
        code.append("addi $sp, $sp, -4\n");
        return code.toString();
    }

    /**
     * fonction getDeplacement
     * @return
     */
    public int getDeplacement() {
        return deplacement;
    }
}
