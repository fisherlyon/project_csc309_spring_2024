package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

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
            repaint(); // Trigger a repaint to show the new background
        } else {
            throw new IllegalArgumentException("Image could not be loaded: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
