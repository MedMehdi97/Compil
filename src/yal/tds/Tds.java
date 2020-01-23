package yal.tds;

import yal.exceptions.AnalyseSemantiqueException;
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
    public void ajouter(Entree e, Symbole s){
         //Vérification de la double déclation à faire
        if(!table.containsValue(e)){ //Si la table ne contient pas encore l'entrée alors
            try {
                this.table.put(e, s); //ajouter l'entrée et le symbole à la table
            } catch (AnalyseSemantiqueException ase)  {
                throw ase;
            }
        }
        else {
            throw new AnalyseSemantiqueException(e.getLigne(), " Double déclaration de la variable "+ e.getNom());
        }
    }

    /**
     * Obtenir le symbole à partir d'une Entrée
     * @param e
     * @return
     */
    public Symbole identifier (Entree e){
        return table.get(e);
    }

    /**
     * @return Le deplacement necessaire pour definir toutes nos variables.
     */
    public int getTailleZoneVariable() {
        return this.cptDepl;
    }
}
