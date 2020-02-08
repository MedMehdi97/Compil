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
        StringBuilder string = new StringBuilder("");
        // Géneration du code pour les deux expressions gauche et droite
        string.append(this.expG.toMIPS());
        string.append(this.expD.toMIPS());
        // Récupération des résultats des expressions dans $v0 et $t8
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $t8, 0($sp)\n");
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        // opérateur "et" ou "ou" : on a deux expressions logiques (0 et 1)
        if (this.oper.equals("et")) {
            string.append("and $v0, $v0, $t8\n");

        }
        else if (this.oper.equals("ou")) {
            string.append("or $v0, $v0, $t8\n");
        }
        else {
            // Sinon, on a deux expressions à comparer le resultat est enregistrer en 0 ou 1
            switch (this.oper) {
                case "<":
                    string.append("bge $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");
                    break;
                case ">":
                    string.append("ble $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");
                    break;
                case "==":
                    string.append("bne $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");
                    break;
                case "!=":
                    string.append("beq $v0, $t8, condf"+Tds.getInstance().getCptBranchement()+"\n");

            }
            //cas de condition vraie
            string.append("li $v0, 1\n");
            string.append("b fincond"+Tds.getInstance().getCptBranchement()+"\n\n");
            string.append("condf"+Tds.getInstance().getCptBranchement()+": \n");
            //cas de condition fausse
            string.append("li $v0, 0\n\n");
            string.append("fincond"+Tds.getInstance().getCptBranchement()+": \n\n");
            Tds.getInstance().addCptBranchement();

        }
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }
}
