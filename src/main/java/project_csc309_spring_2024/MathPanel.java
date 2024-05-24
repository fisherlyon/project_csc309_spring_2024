package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel that displays the Blocks,
 * Trash Bin, OpButtons, and a couple
 * of helpful messages.
 * 
 * @author Fisher
 */
public class MathPanel extends JPanel implements PropertyChangeListener, ActionListener {

    private Button pressedButton = null;
    boolean buttonPressed = false;

    private Font customFont;
    private AudioPlayer audioPlayer;
    private boolean audioPlayed;  // Add a flag to track audio playback
    private Block previousSelectedBlock = null;  // Track the previously selected block

    public MathPanel() {
        setBackground(new Color(250,240,230));
        this.customFont = GameData.getInstance().getCustomFont();
        this.audioPlayer = GameData.getInstance().getAudioPlayer();
        this.audioPlayed = false;  // Initialize the flag
        setLayout(null);
        add(GameData.getInstance().getTutor());
        String[] ops = { "+", "-", "*", "/" };
        for (int i = 0; i < ops.length; i++) {
            Button button = new Button(ops[i], i * 70 + 180, 10, 55, 55);
            button.addActionListener(this);
            button.setButtonColor(new Color(210,169,147), Color.black);
            button.setPressedColor(new Color(175,124,116));
            button.addSelf(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameData.getInstance().getAnswerBox().draw(g);
        GameData.getInstance().getTrashBin().paintComponent(g);
        
        int targetAnswer = GameData.getInstance().getLevel().getTarget();
        g.setFont(customFont.deriveFont(40f)); // Use custom font here
        g.drawString(Integer.toString(targetAnswer), 25, 80);

        for (Block block : GameData.getInstance().getLockedBlocks()) {
            if (!block.isSelected()) {
                block.draw(g);
            }
        }
        for (Block block : GameData.getInstance().getUnlockedBlocks()) {
            if (!block.isSelected()) {
                block.draw(g);
            }
        }

        Block selectedBlock = GameData.getInstance().getSelectedBlock();
        if (selectedBlock != null && selectedBlock != previousSelectedBlock) {
            audioPlayer.play("select");
            audioPlayed = true;  // Set the flag to true after playing the audio
        } else if (selectedBlock == null && previousSelectedBlock != null) {
            audioPlayer.play("drop");
        }
        if (selectedBlock != null) {
            selectedBlock.draw(g);
        }

        previousSelectedBlock = selectedBlock;  // Update the previously selected block


        g.setColor(Color.black);
        g.setFont(customFont.deriveFont(12f)); // Use custom font here
        if (!buttonPressed) {
            g.drawString("Press buttons above to choose between different operations.", 50, 90);
        }

        if (GameData.getInstance().getAnswerBox().getAnswerBlock() == null) {
            g.drawString("Place your answer in here!", 210, 565);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            buttonPressed = true;
            Button button = (Button) e.getSource();

            if (pressedButton != null) {
                pressedButton.setToDefaultColor();
            }

            button.setToPressedColor();
            pressedButton = button;

            GameData.getInstance().setOperationString(button.getLabel());
            audioPlayer.play("select");
        }
    }
}
