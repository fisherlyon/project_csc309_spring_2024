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
        for (NumBlock block : GameData.getInstance().getNumBlocks()) {
            block.draw(g);
        }
        for (NumBlock block : GameData.getInstance().getFreeBlocks()) {
            block.draw(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
