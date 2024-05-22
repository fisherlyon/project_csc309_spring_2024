package project_csc309_spring_2024;

public class Level {

    private ProblemGenerator problemGenerator;
    private int target;
    private int difficulty;
    private Duel duel;
    public Level(Duel duel, int difficulty){
        this.difficulty = difficulty;
        this.duel = duel;
        problemGenerator = new ProblemGenerator(difficulty);
        nextProblem();

    }


    public void nextProblem(){
        target = problemGenerator.generateProblem();
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    
    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Duel getDuel() {
        return duel;
    }
}
