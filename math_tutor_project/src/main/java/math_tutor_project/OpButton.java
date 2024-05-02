package math_tutor_project;

import java.awt.Color;

/**
 * 
 * 
 * @author Fisher
 */
public class OpButton extends Button {

    public OpButton(String label, int x, int y, int w, int h, Color fc1, Color bc1, Color bc2) {
        super(label, x, y, w, h, fc1, bc2, bc2);
    }

    public int doOp(int x, int y) {
        try {
            switch (super.getLabel()) {
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
