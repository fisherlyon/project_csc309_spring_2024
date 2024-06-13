package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A JPanel that displays the chosen
 * scene/level.
 * 
 * @author Fisher Lyon
 */
public class LevelPanel extends JPanel implements PropertyChangeListener {

    private Image backgroundImage = null;
    private CharacterManager characterManager;

    public LevelPanel() {
        this.characterManager = new CharacterManager();
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
            g.setFont(GameData.getInstance().getCustomFont().deriveFont(15f));  // use custom font here
            g.setColor(Color.white);
            g.drawString("Press buttons on the world map to display scene preview.", 40, 300);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("sceneButtonPressed".equals(evt.getPropertyName())) {
            String scene = (String) evt.getNewValue();
            switch (scene) {
                case "Moon":
                    setBackgroundImage("/moon.png");
                    GameData.getInstance().getMusicManager().playMusic("moon");
                    updateCpuPlayer("alien");
                    break;
                case "North Pole":
                    setBackgroundImage("/northPole.png");
                    GameData.getInstance().getMusicManager().playMusic("christmas");
                    updateCpuPlayer("elf");
                    break;
                case "CSC 309":
                    setBackgroundImage("/csc309.png");
                    GameData.getInstance().getMusicManager().playMusic("classroom");
                    updateCpuPlayer("jgs");
                    break;
                case "Brazil":
                    setBackgroundImage("/brazil.png");
                    GameData.getInstance().getMusicManager().playMusic("brazil");
                    updateCpuPlayer("gramps");
                    break;
            }
        }
    }

    private void updateCpuPlayer(String character) {
        CharacterImages characterImages = characterManager.getCharacterImages(character);
        if (characterImages != null) {
            GameData.getInstance().getCpuPlayer().setCharacterImages(characterImages);
        }
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }
}
