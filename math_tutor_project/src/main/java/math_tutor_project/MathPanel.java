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
        for (Block block : GameData.getInstance().getLockedBlocks()) {
            block.draw(g);
        }
        for (Block block : GameData.getInstance().getUnlockedBlocks()) {
            block.draw(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
