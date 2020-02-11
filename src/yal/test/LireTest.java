package yal.test;

import org.junit.jupiter.api.Test;
import yal.arbre.expressions.Idf;
import yal.arbre.instructions.Lire;
import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.SymboleVariable;

import static org.junit.jupiter.api.Assertions.*;

class LireTest {
    Idf idf=new Idf(2,"b");
    EntreeVariable e=new EntreeVariable("b",2);
    SymboleVariable s=new SymboleVariable(-8);
    Lire lire=new Lire(3,idf);

    Idf idf1=new Idf(1,"c");
    EntreeVariable e1=new EntreeVariable("c",1);
    SymboleVariable s1=new SymboleVariable(-12);
    Lire lire1=new Lire(4,idf1);
    @Test
    void toMIPS() {
        Tds.getInstance().ajouter(e,s);
        idf.verifier();
        Tds.getInstance().ajouter(e1,s1);
        idf1.verifier();

        StringBuilder code = new StringBuilder("#Instruction Lecture\n");
        code.append("li $v0, 5\n");
        code.append("syscall\n");
        code.append("#Empiler la variable\n");
        code.append("sw $v0, -8($s7)\n");

        StringBuilder code1 = new StringBuilder("#Instruction Lecture\n");
        code1.append("li $v0, 5\n");
        code1.append("syscall\n");
        code1.append("#Empiler la variable\n");
        code1.append("sw $v0, -12($s7)\n");

        assert lire.toMIPS().equals(code.toString()) : "Erreur dans la fonction toMIPS de la classe Lire ";
        assert lire1.toMIPS().equals(code1.toString()) : "Erreur dans la fonction toMIPS de la classe Lire ";
    }
}