package math_tutor_project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OpButtonTest {
    
    @Test
    void add_test() {
        OpButton button = new OpButton("+", 0, 0, 0, 0, null, null, null);
        Assertions.assertEquals(button.doOp(10, 10), 20);
    }

    @Test
    void sub_test() {
        OpButton button = new OpButton("-", 0, 0, 0, 0, null, null, null);
        Assertions.assertEquals(button.doOp(10, 10), 0);
    }

    @Test
    void mult_test() {
        OpButton button = new OpButton("*", 0, 0, 0, 0, null, null, null);
        Assertions.assertEquals(button.doOp(10, 10), 100);
    }

    @Test
    void div_test() {
        OpButton button = new OpButton("/", 0, 0, 0, 0, null, null, null);
        Assertions.assertEquals(button.doOp(10, 10), 1);
    }

    @Test
void div_by_zero_test() {
    OpButton button = new OpButton("/", 0, 0, 0, 0, null, null, null);
    Assertions.assertThrows(ArithmeticException.class, () -> {
        button.doOp(10, 0);
    });
}
}
