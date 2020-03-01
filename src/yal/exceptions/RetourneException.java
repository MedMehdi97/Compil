package yal.exceptions;

public class RetourneException extends AnalyseSemantiqueException {
    public RetourneException(int ligne, String m) {
        super(ligne, m);
    }
}
