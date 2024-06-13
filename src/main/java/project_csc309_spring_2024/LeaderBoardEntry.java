package project_csc309_spring_2024;

/**
 * A class to hold data for a 
 * leaderboard entry.
 * 
 * @author Fisher Lyon
 */
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LeaderBoardEntry that = (LeaderBoardEntry) obj;
        return pos == that.pos &&
            score == that.score &&
            (name != null ? name.equals(that.name) : that.name == null);
    }


    public int getPos() { return this.pos; }
    public void setPos(int newpos) { this.pos = newpos; }
    public String getName() { return this.name; }
    public int getScore() { return this.score; }
}
