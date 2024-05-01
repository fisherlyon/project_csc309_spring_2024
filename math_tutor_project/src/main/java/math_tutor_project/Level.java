package math_tutor_project;

public class Level {

    private ProblemGenerator problemGenerator;
    private int[] problem;
    private int target;
    private char mode;
    private int difficulty;
    public Level(char mode, int difficulty){
        this.mode = mode;
        this.difficulty = difficulty;
        problemGenerator = new ProblemGenerator(difficulty);
        nextProblem();

    }
    

    public void nextProblem(){
        problem = problemGenerator.generateProblemByOperator(mode);
        target = problemGenerator.solve(problem, mode);
    }

    public void setMode(char mode){
        this.mode = mode;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    
    public int getTarget() {
        return target;
    }


}
