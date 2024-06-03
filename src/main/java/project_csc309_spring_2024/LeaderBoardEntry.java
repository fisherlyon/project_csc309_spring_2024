package project_csc309_spring_2024;

public class LeaderBoardEntry {
    
    private int pos;
    private String name;
    private int score;

    public LeaderBoardEntry(int pos, String name, int score) {
        this.pos = pos;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.pos + "\t" + this.name + "\t" + this.score;
    }

    public int getPos() { return this.pos; }
    public void setPos(int newpos) { this.pos = newpos; }
    public String getName() { return this.name; }
    public int getScore() { return this.score; }
}
