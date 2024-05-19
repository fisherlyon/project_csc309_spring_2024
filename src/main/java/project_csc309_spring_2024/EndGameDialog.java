package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameDialog extends JDialog {
    private JLabel messageLabel;
    private JButton playAgainButton;
    private JButton exitButton;

    public EndGameDialog(JFrame parent, boolean isWinner) {
    
        super(parent, "Game Over", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        messageLabel = new JLabel();
        if (isWinner) {
            messageLabel.setText("Congratulations! You win!");
        } else {
            messageLabel.setText("Sorry, you lose!");
        }
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(messageLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        playAgainButton = new JButton("Play Again");
        exitButton = new JButton("Exit");

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your play again logic here
                dispose(); // Close the dialog
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
