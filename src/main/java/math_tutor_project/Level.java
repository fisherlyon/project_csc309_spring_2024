package math_tutor_project;

public class Level {

    private ProblemGenerator problemGenerator;
    private int target;
    private int difficulty;
    public Level(int difficulty){
        this.difficulty = difficulty;
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


}
