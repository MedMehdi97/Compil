package yal.test;

import org.junit.jupiter.api.Test;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Idf;
import yal.arbre.instructions.Ecrire;
import yal.tds.Tds;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.SymboleVariable;

import static org.junit.jupiter.api.Assertions.*;

class EcrireTest {
    ConstanteEntiere constante=new ConstanteEntiere("2",1);
    //Ecrire ecrireTest=new Ecrire(constante,1);
    Idf idf=new Idf(1,"a");
    //Ecrire ecrireTest2=new Ecrire(idf,1);
    @Test
    void verifier() {
    }

    @Test
    void toMIPS() {
        EntreeVariable e=new EntreeVariable("a",1);
        SymboleVariable s=new SymboleVariable(-4);
        Tds.getInstance().ajouter(e,s);
        idf.verifier();

        StringBuilder code=new StringBuilder("#Instruction Ecrire \n");
        code.append(constante.toMIPS());
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        code.append("move $a0, $v0\n");
        code.append("li $v0, 1\n");
        code.append("syscall\n");

        StringBuilder code1=new StringBuilder("#Instruction Ecrire \n");
        code1.append(idf.toMIPS());
        code1.append("addi $sp, $sp, 4\n");
        code1.append("lw $v0, 0($sp)\n");
        code1.append("move $a0, $v0\n");
        code1.append("li $v0, 1\n");
        code1.append("syscall\n");
        //assert ecrireTest2.toMIPS().equals(code1.toString()) : "Erreur dans la fonction toMips de écrire";

    }
}