package yal.tds;

import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.VariableNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.symbole.Symbole;

import java.util.HashMap;

public class Tds {
    private  int cptDepl = 0;
    private static Tds instance = new Tds();
    private HashMap<Entree, Symbole> table = new HashMap<Entree, Symbole>();

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
    public void ajouter(Entree e, Symbole s)throws DoubleDeclarationException {
        for (Entree e1: this.table.keySet()){
            if (e.getNom().equals(e1.getNom())){
                throw new DoubleDeclarationException(e.getLigne(),"Variable déja déclaré");
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
        throw new VariableNonDeclareeException(e.getLigne(), "Variable non déclarée");
    }

    /**
     * @return Le deplacement necessaire pour definir toutes nos variables.
     */
    public int getTailleZoneVariable() {
        return this.cptDepl*(-4);
    }


    public void afficheTable() {
        for (Entree e: this.table.keySet()){
            System.out.println(e.getNom());
        }
        for (Symbole s: this.table.values()){
            System.out.println(s.getDeplacement());
        }
    }
}
