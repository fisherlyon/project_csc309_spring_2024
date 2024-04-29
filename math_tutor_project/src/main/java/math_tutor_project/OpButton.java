package math_tutor_project;

import javax.swing.*;

public class OpButton extends JButton {
    
    private String op;

    public OpButton(String op) {
        this.op = op;
        this.setText(op);
    }

    public String getOp() { return this.op; }

    public int doOp(int x, int y) {
        try {
            switch (op) {
                case "+" : return x + y;
                case "-" : return x - y;
                case "*" : return x * y;
                case "/" : return x / y;
            }
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Div by zero");
        }
        return -1;
    }
}
