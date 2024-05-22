package project_csc309_spring_2024;

public class Expression {
    private int term1;
    private int term2;
    private int res;
    private char op;

    public Expression(int term1, int term2, int res, char op) {
        this.term1 = term1;
        this.term2 = term2;
        this.res = res;
        this.op = op;
    }

    public char getOp() {
        return op;
    }

    public int getRes() {
        return res;
    }

    public int getTerm1() {
        return term1;
    }

    public int getTerm2() {
        return term2;
    }

    @Override
    public String toString() {
        return term1 + " " + Character.toString(op) + " " + term2 + "  = " + res;
    }
}