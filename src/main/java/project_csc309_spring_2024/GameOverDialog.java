package project_csc309_spring_2024;

import javax.swing.*;

/**
 * A dialog box that appears when
 * the game is over and provides
 * escapability or replayability.
 * 
 * @authoer Fisher
 */
public class GameOverDialog {

    private JFrame parentFrame;
    
    public GameOverDialog(JFrame parentFrame, boolean isWinner) {
        
        this.parentFrame = parentFrame;
        
        String message;

        if (isWinner) {
            message = "You won! Play again?";
        } else {
            message = "You lost! Play again?";
        }

        int response = JOptionPane.showConfirmDialog(parentFrame, message, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (response == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        parentFrame.dispose();
        GameMain main = new GameMain();
        main.setTitle("Math Madness");
        main.setSize(1200, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
