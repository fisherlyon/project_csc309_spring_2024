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

    public int generateProblem() {
        Random rand = new Random();
        int scaledLowerBound = 1 - ((difficulty / 10) * 2);
        int scaledUpperBound = 15 + ((difficulty / 10) * 2);
        return rand.nextInt(scaledLowerBound, scaledUpperBound);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
