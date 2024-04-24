package main.java.math_tutor_project;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 
 * @author Eric Berber
 */
@RunWith(Enclosed.class)
public class ProblemGenerator {

    private int difficulty;

    public ProblemGenerator(int difficulty) {
        if (difficulty < 0) {
            throw new IllegalArgumentException("Difficulty must be a positive integer");
        }
        this.difficulty = difficulty;
    }

    public int generateProblemByOperator(char operator) {
        Random rand = new Random();
        int result = 1;
        int scaledLowerBound = 1 + (difficulty / 10);
        int scaledUpperBound = 2 + ((difficulty / 10) * 2);
        int numberOfTerms = rand.nextInt(scaledLowerBound, scaledUpperBound);

        for (int i = 0; i < numberOfTerms; i++) {
            int termToAppend = rand.nextInt(1, 10);
            switch (operator) {
                case '+':
                    result += termToAppend;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public static class ProblemGeneratorTest {
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
}
