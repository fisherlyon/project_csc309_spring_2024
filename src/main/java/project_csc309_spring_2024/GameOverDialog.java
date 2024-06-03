package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog box that appears when
 * the game is over and provides
 * escapability or replayability.
 * 
 * @author Fisher
 */
public class GameOverDialog {

    private JFrame parentFrame;

    public GameOverDialog(JFrame parentFrame, boolean isWinner) {

        this.parentFrame = parentFrame;

        String message1;
        int response1;

        if (GameData.getInstance().getGameMode().equals("Time Attack")) {
            message1 = "Play again?";
            String message2 = "Congrats! Your score was " +
            GameData.getInstance().getTimeAttackScore() +
            ".\nDo you wish to add your score to the leaderboard?";

            NameTextField ntf = new NameTextField(message2);
            response1 = JOptionPane.showConfirmDialog(parentFrame, ntf, "Save to Leaderboard", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response1 == JOptionPane.YES_OPTION) {
                String playerName = ntf.getName();
                if (!playerName.isEmpty()) {
                    int score = GameData.getInstance().getTimeAttackScore();
                    GameData.getInstance().getLeaderBoard().add(new LeaderBoardEntry(0, playerName, score));
                }
            }
        } else {
            if (isWinner) {
                message1 = "You won! Play again?";
            } else {
                message1 = "You lost! Play again?";
            }
        }

        int response2 = JOptionPane.showConfirmDialog(parentFrame, message1, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response2 == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        GameData.getInstance().getMusicManager().stopAllMusic();
        parentFrame.dispose();
        GameMain main = new GameMain();
        GameData.getInstance().recalculate();
        GameData.getInstance().setMainFrame(main);
        main.setTitle("Math Madness");
        main.setSize(1200, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }   
}
