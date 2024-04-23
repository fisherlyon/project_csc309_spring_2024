package math_tutor_project;

import java.util.*;

/**
 * 
 * @author Eric Berber
 */
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

}
