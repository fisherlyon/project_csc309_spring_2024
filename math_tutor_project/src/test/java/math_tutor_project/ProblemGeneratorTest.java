package math_tutor_project;

import static org.junit.Assert.*;
import org.junit.*;

public class ProblemGeneratorTest {
    @Test
    public void TestProblemGenerationArgumentException() {
        try {

            ProblemGenerator problemGenerator = new ProblemGenerator(-1);
            fail();
        } catch (IllegalArgumentException err) {
            assertEquals("Difficulty must be a positive integer", err.getMessage());
        }
    }

}
