package project_csc309_spring_2024;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeMathTest {

    MergeMath mm = new MergeMath();
    
    @Test
    void add_test() {
        Assertions.assertEquals(mm.doOperation("+", 10, 10), 20);
    }

    @Test
    void sub_test() {
        Assertions.assertEquals(mm.doOperation("-", 10, 10), 0);
    }

    @Test
    void mult_test() {
        Assertions.assertEquals(mm.doOperation("*", 10, 10), 100);
    }

    @Test
    void div_test() {
        Assertions.assertEquals(mm.doOperation("/", 10, 10), 1);
    }

    @Test
void div_by_zero_test() {
    Assertions.assertThrows(ArithmeticException.class, () -> {
        mm.doOperation("/", 10, 0);
    });
}
}
