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

    boolean buttonPressed = false;

    public MathPanel() {
        setLayout(null);
        String[] ops = { "+", "-", "*", "/" };
        for (int i = 0; i < ops.length; i++) {
            OpButton button = new OpButton(ops[i], i * 70 + 180, 10, 55, 55, Color.white, Color.darkGray, Color.gray, 20);
            button.addActionListener(this);
            button.addSelf(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameData.getInstance().getAnswerBox().draw(g);
        GameData.getInstance().getTrashBin().paintComponent(g);
        GameData.getInstance().getTutor().paintComponent(g);
        
        int targetAnswer = GameData.getInstance().getLevel().getTarget();
        g.setFont(new Font("Ariel", Font.PLAIN, 50));
        g.drawString(Integer.toString(targetAnswer), 25, 50);

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
        if (selectedBlock != null) {
            selectedBlock.draw(g);
        }

        g.setColor(Color.black);
        g.setFont(new Font("Impact", 15, 15));
        if (!buttonPressed) {
            g.drawString("Press buttons above to choose between different operations.", 80, 85);
        }

        if (GameData.getInstance().getAnswerBox().getAnswerBlock() == null) {
            g.drawString("Place your answer in here!", 215, 565);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            GameData.getInstance().doButtonAction(this, (Button) e.getSource());
        }
        if (e.getSource() instanceof OpButton) {
            buttonPressed = true;
            OpButton button = (OpButton) e.getSource();
            GameData.getInstance().setPressedButton(button);
        }
    }
}
