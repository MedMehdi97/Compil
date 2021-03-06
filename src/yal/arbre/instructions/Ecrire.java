package yal.arbre.instructions;
import yal.arbre.expressions.ExpressionArithmetique;
import yal.arbre.expressions.ExpressionLogique;
import yal.exceptions.TableauException;
import yal.tds.Tds;

public class Ecrire extends Instruction {

    protected ExpressionArithmetique exp ;
    protected ExpressionLogique expL;

    /**
     * Constructeur de la classe Ecrire pour une expression arithmétique
     * @param e   l'expression arithmétique à afficher
     * @param n   Numéro de la ligne
     */
    public Ecrire (ExpressionArithmetique e, int n) {
        super(n) ;
        exp = e ;
        expL =null;
    }

    /**
     * Constructeur de la classe Ecrire pour une expression logique
     * @param e   l'expression logique à afficher
     * @param n   Numéro de la ligne
     */
    public Ecrire (ExpressionLogique e, int n){
        super(n);
        expL=e;
        exp=null;
    }


    /**
     * Fonction verifier qui appelle la fonction verifier de l'expression à afficher
     */
    @Override
    public void verifier() {
        if (exp!=null){ this.exp.verifier(); }else {this.expL.verifier();}
        if (this.exp.isTab()){ //Déclencher une erreur si l'expression est un tableau
            Tds.getInstance().ajouterException(new TableauException(this.noLigne,"Ecriture Impossible Variable "+this.exp.getNom()+" est un Tableau"));
        }
    }

    /**
     * Géneration du code de l'instruction écrire
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("\n#Instruction Ecrire \n");
        if (exp!=null) {
            //cas d'expression arithmétique
            code.append(this.exp.toMIPS());
            code.append("addi $sp, $sp, 4\n");
            code.append("lw $v0, 0($sp)\n");   //recupération de la valeur à afficher de la pile
            code.append("move $a0, $v0\n"); // $a0 <- $v0
            code.append("li $v0, 1\n"); //code du print d'un entier
        }else {
            //cas d'expression logique
            code.append(this.expL.toMIPS());
            code.append("addi $sp, $sp, 4\n");
            code.append("lw $v0, 0($sp)\n");
            code.append("move $t8, $v0\n");   // $v0 <- $t8
            code.append("li $v0, 4\n");       // code du print d'une chaine de caractères
            // test si le resultat de l'expression logique est vraie ou faux
            code.append("beqz $t8, ecrire"+ Tds.getInstance().getCptEcrire() + "\n");
            // cas de l'expression est vraie
            code.append("la $a0, vrai\n");
            code.append("b finecrire"+Tds.getInstance().getCptEcrire()+"\n\n");
            // cas pi l'expression est fausse
            code.append("ecrire" + Tds.getInstance().getCptEcrire() + ": \n");
            code.append("la $a0, faux\n\n");
            code.append("finecrire"+Tds.getInstance().getCptEcrire()+":\n");
            //incrémenter le compteur des branchements pour écrire
            Tds.getInstance().addCptEcrire();
        }
        code.append("syscall\n");
        //Génération du saut de ligne dans le MIPS
        code.append("\n#Saut de ligne\n");
        code.append("li $v0, 4\n"); // Code du print pour le retour à la ligne
        code.append("la $a0, ln\n");  // Saut à la ligne
        code.append("syscall\n");   // Appel du saut de ligne
        return code.toString();
    }

}
