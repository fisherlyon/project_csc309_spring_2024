package project_csc309_spring_2024;

import java.util.Comparator;

public class LeaderBoardComparator implements Comparator<LeaderBoardEntry> {
    @Override
    public int compare(LeaderBoardEntry e1, LeaderBoardEntry e2) {
        return Integer.compare(e1.getPos(), e2.getPos());
    }
}
