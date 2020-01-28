package yal.test;

import yal.arbre.expressions.ConstanteEntiere;

import static org.junit.jupiter.api.Assertions.*;

class ConstanteEntiereTest {
    ConstanteEntiere constanteTest=new ConstanteEntiere("2",1);
    ConstanteEntiere constanteTest2=new ConstanteEntiere("0",22);
    @org.junit.jupiter.api.Test
    void toMIPS() {
        StringBuilder code= new StringBuilder();
        code.append("li $v0, 2\n");
        code.append("sw $v0, 0($sp)\n");
        code.append("addi $sp, $sp, -4\n");

        StringBuilder code1= new StringBuilder();
        code1.append("li $v0, 0\n");
        code1.append("sw $v0, 0($sp)\n");
        code1.append("addi $sp, $sp, -4\n");
        assert constanteTest.toMIPS().equals(code.toString()) : "Erreur dans la fonction toMips de la classe ConstanteEntière";
        assert constanteTest2.toMIPS().equals(code1.toString()) : "Erreur dans la fonction toMips de la classe ConstanteEntière 2";
    }
}