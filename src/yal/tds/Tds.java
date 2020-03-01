package yal.tds;

import javafx.scene.control.Tab;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.FonctionNonDeclareeException;
import yal.exceptions.VariableNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.symbole.Symbole;
import yal.tds.symbole.SymboleVariable;

import java.util.ArrayList;
import java.util.HashMap;

public class Tds {
    private static Tds instance = new Tds();
    private  int cptDepl = 0;
    //Compteur de bloc
    private int cptBloc;
    private TableLocale tableRacine;
    private TableLocale tableLocaleCourante;
    private ArrayList<AnalyseSemantiqueException> listeException=new ArrayList<>();


    //Compteur des Branchement pour les expressions logiques
    private int cptBranchement=1;
    //Compteur de branchement pour l'instruction ecrire cas d'une expression logique
    private int cptEcrire=1;
    //Compteur de branchement pour les conditions
    private int cptCodition=1;
    //Compteur de branchement pour les négations d'expression logique
    private int cptNonLog=1;
    //Compteur de branchement pour les boucles
    private int cptBoucle=1;
    //Compteur de branchement pour la division par zéro
    private int cptDivZer=1;
    //Compteur de branchement pour les fonctions
    private int cptFonc=1;

    /**
     * Constructeur de la classe Tds
     */
    private Tds() {
        this.cptBloc=0;
        this.tableRacine=new TableLocale(this.cptBloc,null); //tableLocal du Main sans père
        this.tableLocaleCourante=this.tableRacine;
    }

    /**
     * Fonction d'entrée dans une bloc et création de sa table locale
     */
    public void entreeBloc(){
        this.cptBloc+=1;
        TableLocale tableLocale=new TableLocale(this.cptBloc,this.tableLocaleCourante);
        this.tableLocaleCourante.ajouterFils(tableLocale);
        this.tableLocaleCourante=tableLocale;
    }

    /**
     * Fonction d'entrée dans un bloc lors de l'analyse sémantique
     * @param numBloc
     * @return
     */
    public void entreeBloc(int numBloc){
        for (TableLocale table1 : this.tableLocaleCourante.getTablesFils()){
            if (table1.getNumBloc()==numBloc) {
                this.tableLocaleCourante=table1;
            }
        }
    }

    /**
     * Fonction sortie d'un bloc
     */
    public void sortieBloc(){
        this.tableLocaleCourante=this.tableLocaleCourante.getTablePere(); //retour au père
    }

    /**
     * Getteur de tableLocaleCourante
     * @return
     */
    public TableLocale getTableLocaleCourante() {
        return tableLocaleCourante;
    }



    /**
     * Fonction qui retourne la taille de la zone variable pour la table local courante
     * @return
     */
    public int getZoneVariableTableLocale(){
        return this.tableLocaleCourante.getTailleZoneVariable();
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
     * Permet d'ajouter une Entrée et un Symbole dans la Hashmap
     * @param e
     * @param s
     */
    public void ajouter(Entree e, Symbole s) {
        this.tableLocaleCourante.ajouter(e,s);
    }

    /**
     * Permet d'ajouter une entrée et un symbole dans la table local Main
     * @param e
     * @param s
     */
    public void ajouterMain(Entree e, Symbole s){
        this.tableRacine.ajouter(e,s);
    }

    /**
     * Obtenir le symbole à partir d'une Entrée
     * @param e
     * @return
     */
    public Symbole identifier (Entree e){
        return this.tableLocaleCourante.identifier(e);
    }

    /**
     * Fonction d'identification d'une variable déclarée dans le Main
     * @param e
     * @return
     */
    public Symbole identifierMain (Entree e){
        return this.tableRacine.identifier(e);
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


    /**
     * Getteur de cptBranchement
     * @return
     */
    public int getCptBranchement() {
        return cptBranchement;
    }

    /**
     *  Fonction qui incrémete le cptBranchement
     */
    public void addCptBranchement(){
        this.cptBranchement+=1;
    }

    /**
     * Getteur de cptEcrire
     * @return
     */
    public int getCptEcrire() {
        return cptEcrire;
    }

    /**
     *  Fonction qui incrémete le cptEcrire
     */
    public void addCptEcrire(){
        this.cptEcrire+=1;
    }

    /**
     *  getteur getCptCondition
     * @return
     */
    public int getCptCodition() {
        return cptCodition;
    }

    public void addCptCondition() {
        this.cptCodition+=1;
    }

    /**
     * getteur de cptNonLog
     * @return
     */
    public int getCptNonLog(){ return cptNonLog;}

    public void addCptNonlog(){
        this.cptNonLog+=1;
    }

    /**
     * retourne cptBoucle
     * @return
     */
    public int getCptBoucle() {
        return cptBoucle;
    }

    /**
     * Ajoute une exception à la liste
     * @param e AnalyseSemantiqueException
     */
    public void ajouterException(AnalyseSemantiqueException e){
        this.listeException.add(e);
    }

    /**
     * Incrémente cptBoucle
     */
    public void addCptBoucle() {
        this.cptBoucle+=1;
    }


    /**
     * Getteur de cptDivZero
     * @return
     */
    public int getCptDivZer(){
        return this.cptDivZer;
    }

    public void addCptDivZer() {
        this.cptDivZer+=1;
    }
    public void addCptFonc(){
        this.cptFonc+=1;
    }

    public int getCptFonc() {
        return cptFonc;
    }

    /**
     * Fonction qui retourne le numéro de bloc de la table local
     * @return
     */
    public int getNumBlocTableLocale(){
        return this.tableLocaleCourante.getNumBloc();
    }
}
