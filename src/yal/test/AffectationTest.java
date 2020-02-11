package yal.test;

import org.junit.jupiter.api.Test;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;
import yal.arbre.instructions.Affectation;
import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.SymboleVariable;

import static org.junit.jupiter.api.Assertions.*;

class AffectationTest {
    Idf idf=new Idf(3,"a");
    Idf idf2=new Idf(4,"b");
    Expression ex=new ConstanteEntiere("2",3);
    Expression ex2=new Idf(4,"c");

    Affectation affectation=new Affectation(3,idf,ex);
    Affectation affectation2=new Affectation(3,idf2,ex2);
    @Test
    void toMIPS() {
        Tds.getInstance().ajouter(new EntreeVariable("a",3),new SymboleVariable(-4));
        Tds.getInstance().ajouter(new EntreeVariable("b",4),new SymboleVariable(-8));
        Tds.getInstance().ajouter(new EntreeVariable("c",4),new SymboleVariable(-12));
        idf.verifier();
        idf2.verifier();
        ex2.verifier();

        StringBuilder code = new StringBuilder("#Affectation\n");
        code.append(this.ex.toMIPS());
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        code.append("sw $v0, -4($s7)\n");

        StringBuilder code2 = new StringBuilder("#Affectation\n");
        code2.append(this.ex2.toMIPS());
        code2.append("addi $sp, $sp, 4\n");
        code2.append("lw $v0, 0($sp)\n");
        code2.append("sw $v0, -8($s7)\n");

        assert affectation.toMIPS().equals(code.toString()) : "Erreur dans la fonction toMIPS de la classe Affectation";
        assert affectation2.toMIPS().equals(code2.toString()) : "Erreur dans la fonction toMIPS de la classe Affectation";
    }
}