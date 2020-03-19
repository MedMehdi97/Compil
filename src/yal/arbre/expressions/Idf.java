package yal.arbre.expressions;

import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Idf extends ExpressionArithmetique {
    private String nom;
    private int deplacement;
    private boolean main; //Variable pour basculer entre $s7 et $s2 local et global

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
            //tester si on est dans le main ou dans un autre bloc
            if (Tds.getInstance().getNumBlocTableLocale()!=0) { this.main=false; } else {this.main=true;}
            if (s.getDeplacement()==-1 && Tds.getInstance().getNumBlocTableLocale()!=0){
                //vérifier si la variable est déclaré dans le bloc main si on est dans un autre bloc
                s=Tds.getInstance().identifierMain(new EntreeVariable(this.nom,this.noLigne));
                this.main=true; //cas ou on est dans un bloc mais la variable est globale
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
        code.append("lw $v0, " + this.deplacement); //chargement de la valeur dans $v0
        //ajouter $s7 si global ou $s2 si local
        if (main==true){code.append("($s7)\n");} else {code.append("($s2)\n");}
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

    /**
     * Fonction isMain retourne si la variable est golabl ou local
     * @return
     */
    public boolean isMain() {
        return main;
    }
}
