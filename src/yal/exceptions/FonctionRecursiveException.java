package yal.exceptions;

public class FonctionRecursiveException extends AnalyseSemantiqueException {
    public FonctionRecursiveException(int ligne, String m) {
        super(ligne, m);
    }
}
