package math_tutor_project;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * ...
 * 
 * @author Fisher
 */
public class MathPanel extends JPanel implements PropertyChangeListener {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameData.getInstance().getAnswerBox().draw(g);
        GameData.getInstance().getTrashBin().paintComponent(g);
 
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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
