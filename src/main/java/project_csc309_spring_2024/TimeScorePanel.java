package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The panel for the time attack game mode.
 * Includes a timer that counts down and restarts on a correct answer.
 * Also includes a score tracker.
 * 
 * @author Fisher
 */
public class TimeScorePanel extends JPanel implements ActionListener, PropertyChangeListener {

    private JLabel timerLabel;
    private JLabel scoreLabel;
    private Timer timer;
    private int timeLimit = 10;
    private int timeLeft;
    private int score;

    public TimeScorePanel() {
        setLayout(new GridLayout(1, 2));
        setBackground(new Color(76,0,164));
        score = 0;

        timerLabel = new JLabel("Time Remaining : " + timeLimit, SwingConstants.CENTER);
        scoreLabel = new JLabel("Score : " + score, SwingConstants.CENTER);
        timerLabel.setForeground(Color.white);
        scoreLabel.setForeground(Color.white);
        Font font = GameData.getInstance().getCustomFont();
        timerLabel.setFont(font.deriveFont(20f));
        scoreLabel.setFont(font.deriveFont(20f));

        add(timerLabel);
        add(scoreLabel);

        timeLeft = timeLimit;
        timer = new Timer(1000, this);

        GameData.getInstance().addPropertyChangeListener(this);
    }

    public void startTimer() {
        timeLeft = timeLimit;
        timerLabel.setText("Time Remaining: " + timeLeft);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (timeLeft > 1) {
            timeLeft--;
            timerLabel.setText("Time Remaining: " + timeLeft);
        } else {
            timeLeft = 0;
            timerLabel.setText("Time Remaining: " + timeLeft);
            timer.stop();
            GameData.getInstance().setTimeAttackScore(score);
            GameData.getInstance().setGameOver(true);
        }
        GameData.getInstance().getMusicManager().playMusic("time_attack");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("correctAnswer".equals(evt.getPropertyName())) {
            boolean correctAnswer = (boolean) evt.getNewValue();
            if (correctAnswer) {
                timeLeft = 11;
                score++;
                scoreLabel.setText("Score: " + score);
            } else {
                timeLeft-=2;
                timerLabel.setText("Time Remaining: " + timeLeft);
            }
        }
    }
}
