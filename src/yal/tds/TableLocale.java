package yal.tds;

import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.FonctionNonDeclareeException;
import yal.exceptions.VariableNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.symbole.Symbole;
import yal.tds.symbole.SymboleVariable;

import java.util.ArrayList;
import java.util.HashMap;

public class TableLocale {

    private HashMap<Entree, Symbole> table;
    private int numBloc;
    private TableLocale tablePere;
    private ArrayList<TableLocale> tablesFils;
    private int cptDepl=0;
    private int nbRetour;

    /**
     *
     * @param n Numéro du bloc
     * @param tablePere tableAppellante
     */
    public TableLocale(int n, TableLocale tablePere){
        this.numBloc=n;
        this.table=new HashMap<>();
        this.tablesFils=new ArrayList<>();
        this.tablePere=tablePere;
        this.nbRetour=0;
    }

    /**
     * Ajouter une entrée dans la table locale
     * @param e Entree
     * @param s Symbole
     */
    public void ajouter(Entree e, Symbole s){
        for (Entree en: this.table.keySet()){
            if ((e.getNom().equals(en.getNom()))&&(e.entreeType()==en.entreeType())){
                //On test le type de l'entrée
                if (e.entreeType()==0) {
                    Tds.getInstance().ajouterException(new DoubleDeclarationException(e.getLigne(), " Variable " + e.getNom() + " doublement déclaré"));
                }else {
                    Tds.getInstance().ajouterException(new DoubleDeclarationException(e.getLigne(), " Fonction " + e.getNom() + " doublement déclaré"));
                }
            }
        }
        this.table.put(e,s);
        //Augmenter le compteur des déplacement si l'entrée est entier
        if (e.entreeType()==0) {this.cptDepl+=1;}
    }

    /**
     * Fonction qui permet d'identifier une entrée
     * @param e
     * @return
     */
    public Symbole identifier(Entree e){
        for (Entree en: table.keySet()){
            if ((en.getNom().equals(e.getNom()))&&(e.entreeType()==en.entreeType())){
                return table.get(en);
            }
        }
        if (e.entreeType()==0) {
            if (this.numBloc==0) {
                //cas ou on est dans le bloc Main la variable n'est pas déclarée
                Tds.getInstance().ajouterException(new VariableNonDeclareeException(e.getLigne(), "Variable " + e.getNom() + " non déclarée"));
            }
        }else {
            Tds.getInstance().ajouterException(new FonctionNonDeclareeException(e.getLigne(),"Fonction " + e.getNom() + " non déclarée"));
        }
        return new SymboleVariable(-1);
    }

    /**
     * Fonction pour ajouter une table locale fils
     * @param fils
     */
    public void ajouterFils(TableLocale fils){
        this.tablesFils.add(fils);
    }


    /**
     *
     * @return la table locale Appellante
     */
    public TableLocale getTablePere() {
        return this.tablePere;
    }

    /**
     * Permet de recupérer la taille de la zone variable afin de déclarer une variable
     * @return
     */
    public int getTailleZoneVariable() {
        return this.cptDepl*(-4);
    }

    /**
     * Retourne les tables fils
     * @return
     */
    public ArrayList<TableLocale> getTablesFils() {
        return this.tablesFils;
    }

    /**
     * Retourne le numéro de bloc
     * @return
     */
    public int getNumBloc() {
        return this.numBloc;
    }

    /**
     * Retourne le NbRetour
     * @return
     */
    public int getNbRetour(){
        return this.nbRetour;
    }

    /**
     * Incrémente le nombre de retour
     */
    public void addNbRetour(){
        this.nbRetour+=1;
    }

    /**
     * Fonction get CptDepl
     * @return nombre d'entrées de type entier ajouté dans la table
     */
    public int getCptDepl() {
        return this.cptDepl;
    }
}
