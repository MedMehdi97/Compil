package yal.test;

import org.junit.jupiter.api.Test;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.instructions.Ecrire;

import static org.junit.jupiter.api.Assertions.*;

class EcrireTest {
    ConstanteEntiere constante=new ConstanteEntiere("2",1);
    Ecrire ecrireTest=new Ecrire(constante,1);
    ConstanteEntiere constante2=new ConstanteEntiere("4",1);
    Ecrire ecrireTest2=new Ecrire(constante2,1);
    @Test
    void verifier() {
    }

    @Test
    void toMIPS() {
        assert ecrireTest.toMIPS().equals("#Instruction Ecrire \nli $v0, 1\nli $a0, 2 \nsyscall\n") : "Erreur dans la fonction toMips de écrire";
        assert ecrireTest2.toMIPS().equals("#Instruction Ecrire \nli $v0, 1\nli $a0, 4 \nsyscall\n") : "Erreur dans la fonction toMips de écrire";
    }
}