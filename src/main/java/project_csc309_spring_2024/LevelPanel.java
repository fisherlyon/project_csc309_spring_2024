package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A panel that displays scene previews based on
 * button actions on the MapPanel.
 * 
 * @author Fisher and Leo
 */
public class LevelPanel extends JPanel implements PropertyChangeListener {

    private Image backgroundImage = null;

    private Font customFont;

    public LevelPanel() {
        this.customFont = GameData.getInstance().getCustomFont();
        setBackground(Color.black);
        GameData.getInstance().addPropertyChangeListener(this);
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
            g.setFont(customFont.deriveFont(15f));  // use custom font here
            g.setColor(Color.white);
            g.drawString("Press buttons on the world map to display scene preview.", 60, 300);
        }
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("sceneButtonPressed".equals(evt.getPropertyName())) {
            switch ((String) evt.getNewValue()) {
                case "Moon":
                    setBackgroundImage("/moon.png");
                    break;
                case "North Pole":
                    setBackgroundImage("/northPole.png");
                    break;
                case "CSC 309":
                    setBackgroundImage("/csc309.png");
                    break;
                case "Brazil":
                    setBackgroundImage("/brazil.png");
                    break;
            }
        }
    }

    public Image getBackgroundImage() { return backgroundImage; }
}
