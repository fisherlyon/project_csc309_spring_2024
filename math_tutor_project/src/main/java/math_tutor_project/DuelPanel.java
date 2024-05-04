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

//        if (!GameController.getDecider()){
//            g.drawRect(18, 26, 20, 8);
//        }
//        else{
//            g.drawRect(368, 26, 20, 8);
//        }


    }
}
