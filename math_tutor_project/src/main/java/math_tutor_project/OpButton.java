package math_tutor_project;

/**
 * 
 * 
 * @author Fisher
 */
public class OpButton extends Button {
    


    public OpButton(String label, int x, int y, int w, int h) {
        super(label, x, y, w, h);
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
