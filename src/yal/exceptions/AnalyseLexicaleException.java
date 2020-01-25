package yal.exceptions;

public class AnalyseLexicaleException extends AnalyseException {
 
    public AnalyseLexicaleException(int ligne, int colonne, String m) {
        super("ERREUR LEXICALE :\tligne " + ligne + " colonne " + colonne + "\t" + m + " : caractère non reconnu \n") ;
    }

}
