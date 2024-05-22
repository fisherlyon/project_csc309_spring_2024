package project_csc309_spring_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TutorTest {
    @Test
    public void TutorOperateTest1() {
        GameData data = GameData.getInstance();
        Tutor tutor = data.getTutor();
        int expected = 3;
        int res = tutor.operate(1, 2, '+');
        assertEquals(res, expected);

    }

    @Test
    public void TutorOperateTest2() {
        GameData data = GameData.getInstance();
        Tutor tutor = data.getTutor();
        int expected = -15;
        int res = tutor.operate(10, 25, '-');
        assertEquals(res, expected);

    }

    @Test
    public void TutorOperateTest3() {
        GameData data = GameData.getInstance();
        Tutor tutor = data.getTutor();
        int expected = 100;
        int res = tutor.operate(10, 10, '*');
        assertEquals(res, expected);

    }

    @Test
    public void TutorOperateTest4() {
        GameData data = GameData.getInstance();
        Tutor tutor = data.getTutor();
        int expected = -24;
        int res = tutor.operate(-48, 2, '/');
        assertEquals(res, expected);

    }

    @Test
    public void TutorHelpTest1() {

        GameData data = GameData.getInstance();
        Tutor tutor = data.getTutor();
        data.setLevel(new Level(null, 0));
        tutor.help();
        for (Expression expr : tutor.getGuide()) {
            assertEquals(tutor.operate(expr.getTerm1(), expr.getTerm2(), expr.getOp()), expr.getRes());
        }
    }

    @Test
    public void TutorHelpTest2() {

        GameData data = GameData.getInstance();
        Tutor tutor = data.getTutor();

        data.setLevel(new Level(null, 0));
        tutor.help();
        data.getLevel().setDifficulty(50);
        for (Expression expr : tutor.getGuide()) {
            assertEquals(tutor.operate(expr.getTerm1(), expr.getTerm2(), expr.getOp()), expr.getRes());
        }
    }

    @Test
    public void TutorHelpTest3() {

        GameData data = GameData.getInstance();
        Tutor tutor = data.getTutor();

        tutor.help();
        data.getLevel().setDifficulty(50);
        for (Expression expr : tutor.getGuide()) {
            assertEquals(tutor.operate(expr.getTerm1(), expr.getTerm2(), expr.getOp()), expr.getRes());
        }
    }
}
