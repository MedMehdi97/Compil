package yal.arbre.expressions;

import yal.tds.Tds;

public class ExpressionLogique extends Expression {
    /**
     * Constructeur d'une expression logique simple
     * @param n numéro de ligne
     */
    protected ExpressionLogique(int n) {
        super(n);
    }

    /**
     * Constructeur d'une expression
     * @param n Numéro de la ligne
     * @param expG opérand gauche
     * @param expD opérand droit
     * @param oper opérateur
     */
    public ExpressionLogique(int n, Expression expG, Expression expD, String oper) {
        super(n, expG, expD, oper);
    }

    @Override
    public void verifier() {
        this.expG.verifier();
        this.expD.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("");
        // Géneration du code pour les deux expressions gauche et droite
        code.append(this.expG.toMIPS());
        code.append(this.expD.toMIPS());
        // Récupération des résultats des expressions dans $v0 et $t8
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $t8, 0($sp)\n");
        code.append("addi $sp, $sp, 4\n");
        code.append("lw $v0, 0($sp)\n");
        // opérateur "et" ou "ou" : on a deux expressions logiques (0 et 1)
        if (this.oper.equals("et")) {
            code.append("and $v0, $v0, $t8\n");

        }
        else if (this.oper.equals("ou")) {
            code.append("or $v0, $v0, $t8\n");
        }
        else {
            // Sinon, on a deux expressions à comparer le resultat est enregistrer en 0 ou 1
            //test et branchement dans l'étiquette
            switch (this.oper) {
                case "<":
                    code.append("bge $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");
                    break;
                case ">":
                    code.append("ble $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");
                    break;
                case "==":
                    code.append("bne $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");
                    break;
                case "!=":
                    code.append("beq $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");

            }
            //cas de condition vraie
            code.append("li $v0, 1\n");
            code.append("b fincond"+Tds.getInstance().getCptBranchement()+"\n\n");
            code.append("condf"+Tds.getInstance().getCptBranchement()+": \n");
            //cas de condition fausse
            code.append("li $v0, 0\n\n");
            //étiquette de fin
            code.append("fincond"+Tds.getInstance().getCptBranchement()+": \n\n");
            //incrémenter le compteur d'étiquette
            Tds.getInstance().addCptBranchement();

        }
        //empiler le resultat
        code.append("sw $v0, 0($sp)\n");
        code.append("addi $sp, $sp, -4\n");
        return code.toString();
    }
}
