package main.java.math_tutor_project;

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

    private Image trash;

    public MathPanel() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/trash.png"));
        trash = icon.getImage();
        // Better to check for image dimensions to see if it loaded correctly
        if (icon.getIconWidth() == -1 || icon.getIconHeight() == -1) {
            System.out.println("Error loading trash image.");
            trash = null;
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameData.getInstance().getAnswerBox().draw(g);

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
        if (trash != null) {
            // Use actual size of the image, or a fixed size as desired
            int x = 0; // Left side of the panel
            int y = getHeight() - trash.getHeight(null); // Bottom of the panel
            g.drawImage(trash, x, y, this);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
