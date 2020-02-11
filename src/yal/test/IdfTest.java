package yal.test;

import org.junit.jupiter.api.Test;
import yal.arbre.expressions.Idf;
import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.SymboleVariable;

import static org.junit.jupiter.api.Assertions.*;

class IdfTest {
    EntreeVariable e=new EntreeVariable("a",1);
    SymboleVariable s=new SymboleVariable(0);
    Idf idf=new Idf(1,"a");
    EntreeVariable e1=new EntreeVariable("b",2);
    SymboleVariable s1=new SymboleVariable(-4);
    Idf idf1=new Idf(2,"b");
    @Test
    void verifier() {
        Tds.getInstance().ajouter(e,s);
        idf.verifier();
        Tds.getInstance().ajouter(e1,s1);
        idf1.verifier();
        assert idf.getDeplacement() == 0 : "Erreur dans la fonction vérifier de la classe Idf";
        assert idf1.getDeplacement() == -4 : "Erreur dans la fonction vérifier de la classe Idf";
    }

    @Test
    void toMIPS() {
        Tds.getInstance().ajouter(e,s);
        idf.verifier();
        Tds.getInstance().ajouter(e1,s1);
        idf1.verifier();
        StringBuilder code=new StringBuilder("");
        code.append("lw $v0, 0($s7)\n");
        code.append("sw $v0, 0($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        assert idf.toMIPS().equals(code.toString()) : "Erreur dans la fonction toMIPS de la classe Idf";
        StringBuilder code1=new StringBuilder("");
        code1.append("lw $v0, -4($s7)\n");
        code1.append("sw $v0, 0($sp)\n");
        code1.append("addi $sp, $sp, -4\n");
        assert idf1.toMIPS().equals(code1.toString()) : "Erreur dans la fonction toMIPS de la classe Idf";
    }

}