package project_csc309_spring_2024;


/**
 * A class that does the math
 * for block merging.
 * 
 * @author Fisher
 */
public class MergeMath {

    public int doOperation(String operation, int x, int y) {
        try {
            switch (operation) {
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
