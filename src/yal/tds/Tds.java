package yal.tds;

import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.VariableNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.symbole.Symbole;
import yal.tds.symbole.SymboleVariable;

import java.util.ArrayList;
import java.util.HashMap;

public class Tds {
    private  int cptDepl = 0;
    private static Tds instance = new Tds();
    private HashMap<Entree, Symbole> table = new HashMap<Entree, Symbole>();
    private ArrayList<AnalyseSemantiqueException> listeException=new ArrayList<>();

    /**
     * Constructeur vide
     */
    private Tds() {
    }

    /**
     * Retourne la classe TDS
     * @return
     */
    public static Tds getInstance() {
        return instance;
    }

    /**
     * Getter du compteur
     * @return
     */
    public int getCptDepl() {
        return cptDepl;
    }

    /**
     * Setter du compteur
     * @param cptDepl
     */
    public void setCptDepl(int cptDepl) {
        this.cptDepl = cptDepl;
    }

    /**
     * Permet d'ajouter une Entrée et un Symbole dans la Hashmap
     * @param e
     * @param s
     */
    public void ajouter(Entree e, Symbole s) {
        for (Entree e1: this.table.keySet()){
            if (e.getNom().equals(e1.getNom())){
                 listeException.add(new DoubleDeclarationException(e.getLigne(),"Variable doublement déclaré"));
            }
        }
        this.table.put(e,s);
        this.cptDepl+=1;
    }

    /**
     * Obtenir le symbole à partir d'une Entrée
     * @param e
     * @return
     */
    public Symbole identifier (Entree e){
        for (Entree en: table.keySet()){
            if (en.getNom().equals(e.getNom())){
                return table.get(en);
            }
        }
        listeException.add(new VariableNonDeclareeException(e.getLigne(), "Variable non déclarée"));
        return new SymboleVariable(-1);
    }

    /**
     * @return Le deplacement necessaire pour definir toutes nos variables.
     */
    public int getTailleZoneVariable() {
        return this.cptDepl*(-4);
    }

    /**
     * Fonction qui déclenche les exceptions sémantiques sans l'arrêt de l'éxecution du programme
     */
    public void declencherException(){
        if (listeException.size()!=0) {
            for (AnalyseSemantiqueException e : listeException) {
                System.out.println(e.getMessage());
            }
            System.exit(0);
        }
    }

}
