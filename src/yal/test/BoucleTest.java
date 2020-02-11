package yal.test;

import org.junit.jupiter.api.Test;
import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.ExpressionLogique;
import yal.arbre.instructions.Affectation;
import yal.arbre.instructions.Boucle;
import yal.arbre.instructions.Ecrire;
import yal.tds.Tds;

import static org.junit.jupiter.api.Assertions.*;

class BoucleTest {
     ExpressionLogique expL=new ExpressionLogique(2,new ConstanteEntiere("1",2),new ConstanteEntiere("2",2),"<");
     BlocDInstructions listeInstructions=new BlocDInstructions(3);
     Ecrire ec=new Ecrire(new ConstanteEntiere("2",3),3);
    @Test
    void toMIPS() {
        this.listeInstructions.ajouter(ec);
        Boucle boucle=new Boucle(3,expL,listeInstructions);
        StringBuilder code=new StringBuilder("");
        code.append("# Boucle Tant que \n" +
                "tantque1:\n" +
                "li $v0, 1\n" +
                "sw $v0, 0($sp)\n" +
                "addi $sp, $sp, -4\n" +
                "li $v0, 2\n" +
                "sw $v0, 0($sp)\n" +
                "addi $sp, $sp, -4\n" +
                "addi $sp, $sp, 4\n" +
                "lw $t8, 0($sp)\n" +
                "addi $sp, $sp, 4\n" +
                "lw $v0, 0($sp)\n" +
                "bge $v0, $t8, condf1\n" +
                "li $v0, 1\n" +
                "b fincond1\n" +
                "\n" +
                "condf1: \n" +
                "li $v0, 0\n" +
                "\n" +
                "fincond1: \n" +
                "\n" +
                "sw $v0, 0($sp)\n" +
                "addi $sp, $sp, -4\n" +
                "addi $sp, $sp, 4\n" +
                "lw $v0, 0($sp)\n" +
                "beqz $v0, fintantque1\n" +
                "\n" +
                "#Instruction Ecrire \n" +
                "li $v0, 2\n" +
                "sw $v0, 0($sp)\n" +
                "addi $sp, $sp, -4\n" +
                "addi $sp, $sp, 4\n" +
                "lw $v0, 0($sp)\n" +
                "move $a0, $v0\n" +
                "li $v0, 1\n" +
                "syscall\n" +
                "\n" +
                "#Saut de ligne\n" +
                "li $v0, 4\n" +
                "la $a0, ln\n" +
                "syscall\n" +
                "b tantque1\n" +
                "fintantque1:");
        assert boucle.toMIPS().equalsIgnoreCase(code.toString()) : "Erreur dans la fonction toMIPS de la classe Boucle "+code.toString().length()+" "+boucle.toMIPS().length();
    }
}