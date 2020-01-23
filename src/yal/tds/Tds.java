package yal.tds;

import yal.tds.entree.Entree;
import yal.tds.symbole.Symbole;

import java.util.HashMap;

public class Tds {
    private static Tds instance = new Tds();

    public static Tds getInstance() {
        return instance;
    }
    private Tds() {
        this.table = new HashMap<>();
    }

    private HashMap<Entree, Symbole> table;
    public void ajouter(Entree e, Symbole s){
         //Vérification de la double déclation à faire
        this.table.put(e, s);
    }
}
