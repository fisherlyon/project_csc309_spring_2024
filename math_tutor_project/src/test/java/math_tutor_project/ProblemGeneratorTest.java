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

    public void TestProblemGenerationAddition1() {
        ProblemGenerator problemGenerator = new ProblemGenerator(0);

        // Result should be in this range
        int lowerBound = 1 * (1 + (problemGenerator.getDifficulty() / 100));
        int upperBound = 10 * (2 + ((problemGenerator.getDifficulty() / 100) * 2));

        int result = problemGenerator.generateProblemByOperator('+');
        assertTrue(result >= lowerBound && result <= upperBound);
    }

    public void TestProblemGenerationAddition2() {
        ProblemGenerator problemGenerator = new ProblemGenerator(100);

        // Result should be in this range
        int lowerBound = 1 * (1 + (problemGenerator.getDifficulty() / 100));
        int upperBound = 10 * (2 + ((problemGenerator.getDifficulty() / 100) * 2));

        int result = problemGenerator.generateProblemByOperator('+');
        assertTrue(result >= lowerBound && result <= upperBound);
    }
}
