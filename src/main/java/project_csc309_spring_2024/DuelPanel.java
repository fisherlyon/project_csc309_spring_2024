package project_csc309_spring_2024;


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
    private CpuHealth cpuHealth;
    private PlayerHealth playerHealth;


    public DuelPanel(UserPlayer player, CpuPlayer cpu, CpuHealth cpuHealth, PlayerHealth playerHealth) {
        this.cpuPlayer = cpu;
        this.userPlayer = player;
        this.cpuHealth = cpuHealth;
        this.playerHealth = playerHealth;
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
        g.setColor(Color.black);
        g.fillRect(15, 28, 200, 38);
        g.setColor(Color.black);
        g.fillRect(365, 28, 200, 38);

        if (userPlayer != null) {
            g.drawImage(userPlayer.getImage(), userPlayer.getplayerX(), userPlayer.getplayerY(), this);
        }

        if (cpuPlayer != null) {
            g.drawImage(cpuPlayer.getImage(), cpuPlayer.getcpuX(), cpuPlayer.getcpuY(), this);
        }
        if (playerHealth != null) {
            g.drawImage(playerHealth.getPlayerhealthbar(), playerHealth.getUserHealthBarX(), playerHealth.getUserHealthBarY(), this);
        }
        if (cpuHealth != null) {
            g.drawImage(cpuHealth.getCpuhealthbar(), cpuHealth.getCpuHealthBarX(), cpuHealth.getCpuHealthBarY(), this);
        }

        // should increase by 20 width and decrease x by 20
        g.setColor(Color.blue);
        g.fillRect(210, 28, 5, 38);
        g.setColor(Color.blue);
        g.fillRect(558, 28, 5, 38);


    }
}