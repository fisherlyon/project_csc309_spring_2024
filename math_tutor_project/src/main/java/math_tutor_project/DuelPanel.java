package math_tutor_project;


import javax.swing.*;
import java.awt.*;
/**
 * ...
 *
 * @author Leo Rivera
 */
public class DuelPanel extends JPanel {
    private Image backgroundImage;
    private UserPlayer userPlayer;
    private CpuPlayer cpuPlayer;
    public DuelPanel(UserPlayer player, CpuPlayer cpu) {
        this.cpuPlayer = cpu;
        this.userPlayer = player;
        backgroundImage = new ImageIcon(getClass().getResource("/stage1.png")).getImage();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null)));
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }


    public void setBackgroundImage(String imagePath) {
        ImageIcon icon = new ImageIcon(DuelPanel.class.getResource(imagePath));
        if (icon.getImage() != null) {
            this.backgroundImage = icon.getImage();
            repaint(); // Trigger a repaint to show the new background
        } else {
            throw new IllegalArgumentException("Image could not be loaded: " + imagePath);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

        if (userPlayer != null) {
            g.drawImage(userPlayer.getImage(), userPlayer.getplayerX(), userPlayer.getplayerY(), this);
        }

        if (cpuPlayer != null) {
            g.drawImage(cpuPlayer.getImage(), cpuPlayer.getcpuX(), cpuPlayer.getcpuY(), this);
        }

        g.setColor(Color.BLACK);
        g.fillRect(15, 25, 200, 50);
        g.setColor(Color.BLACK);
        g.fillRect(365, 25, 200, 50);


        g.setColor(Color.BLACK);
        g.fillOval(240, 20, 100, 100);

        g.setColor(Color.RED);
        g.fillRect(25, 35, 180, 30);
        g.setColor(Color.RED);
        g.fillRect(375, 35, 180, 30);
    }
}
