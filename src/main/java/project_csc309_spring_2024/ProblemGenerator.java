package project_csc309_spring_2024;

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

    public int generateProblem() {
        Random rand = new Random();
        int scaledLowerBound = 1 - ((difficulty / 10) * 2);
        int scaledUpperBound = 15 + ((difficulty / 10) * 2);
        // Compute the range size and then shift by the lower bound
        return scaledLowerBound + rand.nextInt(scaledUpperBound - scaledLowerBound);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
