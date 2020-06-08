package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;
import yal.exceptions.TypeException;
import yal.tds.Tds;

public class Affectation extends Instruction {
    private Idf idf;
    private Expression exp;

    /**
     * Constructeur de la classe Affectation
     * @param n Numéro de la ligne
     * @param idf  Idf partie gauche de l'affectation
     * @param exp  Exp partie droite de l'affectation
     */
    public Affectation(int n, Idf idf, Expression exp) {
        super(n);
        this.idf=idf;
        this.exp=exp;
    }

    /**
     * Fonction vérifier qui appelle les fonctions verifier des deux parties de l'affectation
     */
    @Override
    public void verifier() {
        this.idf.verifier();
        this.exp.verifier();
        if (this.idf.isTab()!=this.exp.isTab()){
            Tds.getInstance().ajouterException(new TypeException(this.noLigne,"Affectation impossible Type non identique"));
        }
    }

    /**
     * Fonction qui génere le code d'une affectation
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("#Affectation\n");
        if (this.idf.isTab()==false) {  //Affectation d'une variable
            code.append(this.exp.toMIPS());
            code.append("addi $sp, $sp, 4\n");
            code.append("lw $v0, 0($sp)\n");   //recupération de la valeur à affecter depuis la pile
            code.append("sw $v0, " + this.idf.getDeplacement());
            //Variable global ou bien variable local
            if (this.idf.isMain() == true) {
                code.append("($s7)\n");
            } else {
                code.append("($s2)\n");
            }
        }else { //Affectation d'un tableau à un autre tableau
            int pasTab1, pasTab2;
            //Taille du tableau gauche
            code.append("lw $v0, "+this.idf.getDeplacement());
            if (this.idf.isMain()){
                code.append("($s7)\n");
            }else {
                code.append("($s2)\n");
            }
            //Taille du tableau droit
            code.append("lw $t8, "+this.exp.getDeplacement());
            if (this.idf.isMain()){
                code.append("($s7)\n");
            }else {
                code.append("($s2)\n");
            }
            //Vérifier si ils ont la même taille
            code.append("beq $v0, $t8, FinVerif"+Tds.getInstance().getCptVerifTab()+"\n");
            code.append("li $v0, 4\n");
            code.append("la $a0, ErreurAffectationTailleDesTableauxDifferente\n");
            code.append("syscall\n");
            code.append("b end\n");
            code.append("FinVerif"+Tds.getInstance().getCptVerifTab()+" :\n");
            Tds.getInstance().addCptVerifTab();
            //placement des registres $a1, $a2 sur les premiers élements des 2 tableaux
            if (this.idf.isDyn()){
                pasTab1=4;
                code.append("lw $a1, "+(this.idf.getDeplacement()-4)+"($s2)\n");
            }else {
                code.append("li $a1, "+(this.idf.getDeplacement()-4)+"\n");
                if (this.idf.isMain()){code.append("add $a1, $a1, $s7\n");}else {
                    code.append("add $a1, $a1, $s2\n");
                }
                pasTab1=-4;
            }
            if (this.exp.isDyn()){
                pasTab2=4;
                code.append("lw $a2, "+(this.exp.getDeplacement()-4)+"($s2)\n");
            }else {
                code.append("li $a2, "+(this.exp.getDeplacement()-4)+"\n");
                if (this.idf.isMain()){code.append("add $a2, $a2, $s7\n");}else {
                    code.append("add $a2, $a2, $s2\n");
                }
                pasTab2=-4;
            }
            //Boucle pour affecter les élements du tableau droit au tableau gauche
            code.append("AffectTab"+Tds.getInstance().getCptAffTab()+" :\n");
            //boucler tant que $v0 n'est pas nul il correspend à la taille au début
            code.append("beqz $v0, finAffect"+Tds.getInstance().getCptAffTab()+"\n");
            code.append("lw $t8, 0($a2)\n"); //recupérer l'élement du tableau droit
            code.append("sw $t8, 0($a1)\n");//placer l'élement au tableau gauche
            //avancer au prochain élement
            code.append("addi $a1, $a1, "+pasTab1+"\n");
            code.append("addi $a2, $a2, "+pasTab2+"\n");
            code.append("addi $v0, $v0, -1\n"); //Incrémenter $v0 à chaque itération
            code.append("b AffectTab"+Tds.getInstance().getCptAffTab()+"\n");
            code.append("finAffect"+Tds.getInstance().getCptAffTab()+" :\n");
            Tds.getInstance().addCptAffTab();
        }
        return code.toString();
    }
}
