package yal.exceptions;

public class AnalyseSemantiqueException extends AnalyseException {
 
    public AnalyseSemantiqueException(int ligne, String m) {
        super("ERREUR SEMANTIQUE : ligne " + ligne + "\t" + m + "\n") ;
    }

}
