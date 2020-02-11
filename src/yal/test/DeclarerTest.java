package yal.test;

import org.junit.jupiter.api.Test;
import yal.arbre.instructions.Declarer;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.SymboleVariable;

import static org.junit.jupiter.api.Assertions.*;

class DeclarerTest {
    EntreeVariable e=new EntreeVariable("a",2);
    Declarer declarer=new Declarer(2,"a",e);
    @Test
    void toMIPS() {
        StringBuilder code=new StringBuilder("#reservation de l'espace pour une variable\n");
        code.append("addi $sp, $sp, -4\n");
        assert declarer.toMIPS().equals(code.toString()): "Erreur dans la fonction toMIPS de la classe Declarer";
    }
}