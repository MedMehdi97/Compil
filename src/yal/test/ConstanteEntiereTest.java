package yal.test;

import yal.arbre.expressions.ConstanteEntiere;

import static org.junit.jupiter.api.Assertions.*;

class ConstanteEntiereTest {
    ConstanteEntiere constanteTest=new ConstanteEntiere("2",1);
    ConstanteEntiere constanteTest2=new ConstanteEntiere("0",22);
    @org.junit.jupiter.api.Test
    void toMIPS() {
        assert constanteTest.toMIPS().equals("2") : "Erreur dans la fonction toMips de la classe ConstanteEntière";
        assert constanteTest2.toMIPS().equals("0") : "Erreur dans la fonction toMips de la classe ConstanteEntière 2";
    }
}