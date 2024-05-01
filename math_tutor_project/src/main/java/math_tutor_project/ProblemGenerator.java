package math_tutor_project;

import java.util.*;
import java.util.Random;

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

    public int[] generateProblemByOperator(char operator) {
        Random rand = new Random();
        int scaledLowerBound = 1 + (difficulty / 10);
        int scaledUpperBound = 2 + ((difficulty / 10) * 2);
        int range = scaledUpperBound - scaledLowerBound + 1;
        int numberOfTerms = rand.nextInt(range) + scaledLowerBound; // Adjusted line

        int[] result = new int[numberOfTerms];

        for (int i = 0; i < numberOfTerms; i++) {
            int termToAppend = rand.nextInt(30);
            switch (operator) {
                case '+':
                    result[i] = termToAppend;
                    break;
                default:
                    break;
            }
        }
        return result;
    }


    public int solve(int[] problem, char operator){
        int result = 0;
        for(int i=0; i < problem.length; i++){
            result += problem[i];
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
