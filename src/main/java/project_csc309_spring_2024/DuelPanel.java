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
    private int prevUserHealth;
    private int prevCpuHealth;


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


    public void setBackgroundImage(Image image) {
        if (image != null) {
            this.backgroundImage = image;
            repaint();
        } else {
            throw new IllegalArgumentException("Image could not be loaded");
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.black);
        g.fillRect(15, 28, 200, 38);
        g.setColor(Color.black);
        g.fillRect(365, 28, 200, 38);

        if (userPlayer != null) {
            g.drawImage(userPlayer.getPlayerOne(), userPlayer.getplayerX(), userPlayer.getplayerY(), this);
        }

        if (cpuPlayer != null) {
            g.drawImage(cpuPlayer.getPlayerTwo(), cpuPlayer.getplayerX(), cpuPlayer.getplayerY(), this);
        }
        if (playerHealth != null) {
            g.drawImage(playerHealth.getPlayerhealthbar(), playerHealth.getUserHealthBarX(), playerHealth.getUserHealthBarY(), this);
        }
        if (cpuHealth != null) {
            g.drawImage(cpuHealth.getCpuhealthbar(), cpuHealth.getCpuHealthBarX(), cpuHealth.getCpuHealthBarY(), this);
        }

        int userHealth = userPlayer.getHealth();
        int cpuHealth = cpuPlayer.getHealth();

        int maxHealthBarWidth = 200;
        int healthBarHeight = 38;

        int playerBarWidth = Math.min(maxHealthBarWidth, (20 * (100 - userHealth)) / 10);
        int cpuBarWidth = Math.min(maxHealthBarWidth, (20 * (100 - cpuHealth)) / 10);

        int playerHealthX = 210 - playerBarWidth;
        int cpuHealthX = 560 - cpuBarWidth;

        g.setColor(Color.black);
        g.fillRect(playerHealthX, 28, playerBarWidth, healthBarHeight); // Player Health Bar
        g.fillRect(cpuHealthX, 28, cpuBarWidth, healthBarHeight); // CPU Health Bar

        prevUserHealth = userHealth;
        prevCpuHealth = cpuHealth;
        repaint();


    }
}