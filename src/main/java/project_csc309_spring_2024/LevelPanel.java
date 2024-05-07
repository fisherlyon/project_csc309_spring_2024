package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that displays scene previews based on
 * button actions on the MapPanel.
 * 
 * @author Fisher and Leo
 */

public class LevelPanel extends JPanel {

    private Image backgroundImage = null;

    public LevelPanel() {
        setBackground(Color.black);
        GameData.getInstance().setLevelPanel(this);
    }

    public void setBackgroundImage(String imagePath) {
        ImageIcon icon = new ImageIcon(DuelPanel.class.getResource(imagePath));
        if (icon.getImage() != null) {
            backgroundImage = icon.getImage();
            repaint(); 
        } else {
            throw new IllegalArgumentException("Image could not be loaded: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.setFont(new Font("Impact", 20, 20));
            g.setColor(Color.white);
            g.drawString("Press buttons on the world map to display scene preview.", 60, 300);
        }
        
    }

    public Image getBackgroundImage() { return backgroundImage; }
}
