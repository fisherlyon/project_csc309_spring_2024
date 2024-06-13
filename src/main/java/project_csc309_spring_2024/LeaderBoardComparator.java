package project_csc309_spring_2024;

import java.util.Comparator;

/**
 * A comparator for comparing
 * two leaderboard entries. Comparisons
 * are made based on score.
 * 
 * @author Fisher Lyon
 */
public class LeaderBoardComparator implements Comparator<LeaderBoardEntry> {
    @Override
    public int compare(LeaderBoardEntry e1, LeaderBoardEntry e2) {
        return Integer.compare(e2.getScore(), e1.getScore());
    }
}
