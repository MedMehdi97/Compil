package yal.arbre;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait ab: programme) { // vérification sémantique de chaque arbre
            ab.verifier();
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuilder code=new StringBuilder("");
        //Génération du code Mips pour toute l'arbre
        for (ArbreAbstrait ab: programme){
            code.append(ab.toMIPS());
        }
        return code.toString();
    }

}
