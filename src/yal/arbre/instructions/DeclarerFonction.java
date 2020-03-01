package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.RetourneException;
import yal.tds.Tds;
import yal.tds.entree.EntreeFonction;
import yal.tds.symbole.Symbole;

public class DeclarerFonction extends Instruction {
    protected String nom;
    protected ArbreAbstrait listeInstructions;
    protected int numBloc;

    public DeclarerFonction(int n, String nom, ArbreAbstrait listeInstructions) {
        super(n);
        this.nom = nom;
        this.listeInstructions = listeInstructions;
    }

    @Override
    public void verifier() {
        Symbole s=Tds.getInstance().identifier(new EntreeFonction(this.nom,this.noLigne));
        this.numBloc=s.getNumBloc();
        //Entrer dans le bloc pour vérifier les instructions
        Tds.getInstance().entreeBloc(this.numBloc);
        this.listeInstructions.verifier();
        //vérifier si il existe une instructions retourne dans la fonction
        if (Tds.getInstance().getTableLocaleCourante().getNbRetour()==0){
            Tds.getInstance().ajouterException(new RetourneException(this.getNoLigne(),"Fonction "+this.nom+" sans instruction retourne"));
        }
        //sortir du bloc après la vérification
        Tds.getInstance().sortieBloc();
    }

    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder();
        code.append("#Declaration fonction\n");
        // Sauter la fonction lors de l'éxecution du programme
        code.append("j fonctionjmp"+ Tds.getInstance().getCptFonc() +"\n");
        // Entree dans le bloc de la fonction
        Tds.getInstance().entreeBloc(this.numBloc);
        code.append(this.nom  + ":\n");    // Etiquette de la fonction
        // Empiler l'adresse retour
        code.append("sw $ra, ($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        // Empiler la base local (cas ou on est dans une fonction)
        code.append("sw $s2, ($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        // Empiler Le numéro de bloc
        code.append("li $v0, " + this.numBloc + "\n");
        code.append("sw $v0, ($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        // Initialisation de la base locale
        code.append("move $s2, $sp\n");
        //Géneration du code des instructions de la fonction
        code.append(this.listeInstructions.toMIPS());
        //Sortie du bloc
        Tds.getInstance().sortieBloc();
        // Etiquette pour sauter la fonction
        code.append("fonctionjmp"+ Tds.getInstance().getCptFonc() +":\n");
        //Augmenter le compteur des étiquettes
        Tds.getInstance().addCptFonc();
        return code.toString();
    }
}
