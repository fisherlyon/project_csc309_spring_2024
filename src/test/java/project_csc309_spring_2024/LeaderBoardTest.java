package project_csc309_spring_2024;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class LeaderBoardTest {

    LeaderBoard lb;

    @Test
    public void testAccessS3() {
        Boolean works = false;
        try {
            lb = new LeaderBoard();
            lb.getLeaderboard();
            works = true;
        } catch (Exception e) {
            works = false;
        }
        Assertions.assertEquals(true, works);
    }

    @Test
    public void testRecalculatePosition() {
        ArrayList<LeaderBoardEntry> original = new ArrayList<>();
        original.add(new LeaderBoardEntry(3, "one", 100));
        original.add(new LeaderBoardEntry(1, "two", 50));
        original.add(new LeaderBoardEntry(2, "three", 10));

        ArrayList<LeaderBoardEntry> result = new ArrayList<>();
        result.add(new LeaderBoardEntry(1, "one", 100));
        result.add(new LeaderBoardEntry(2, "two", 50));
        result.add(new LeaderBoardEntry(3, "three", 10));

        lb = new LeaderBoard();
        lb.recalculatePositions(original);

        for (int i = 0; i < original.size(); i++) {
            Assertions.assertEquals(result.get(i), original.get(i));
        }
    }

    @Test
    public void testConvertToJSON() {
        ArrayList<LeaderBoardEntry> lbes = new ArrayList<>();
        lbes.add(new LeaderBoardEntry(1, "one", 100));
        lb = new LeaderBoard();

        String actual = lb.convertToJson(lbes);
        String extected = "[{\"score\":\"100\",\"pos\":\"1\",\"name\":\"one\"}]";

        Assertions.assertEquals(extected, actual);
    }

    @Test void testParse() {
        String jsonString = "[{\"score\":\"100\",\"pos\":\"1\",\"name\":\"one\"}]";
        lb = new LeaderBoard();
        ArrayList<LeaderBoardEntry> actual = lb.parse(jsonString);
        LeaderBoardEntry expected = new LeaderBoardEntry(1, "one", 100);

        Assertions.assertEquals(expected, actual.get(0));
    }
}
