package yal.exceptions;

public class DivisionParZeroException extends AnalyseSemantiqueException{
    public DivisionParZeroException(int ligne){
        super(ligne,"Division par zéro");
    }
}
