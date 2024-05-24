package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ...
 *
 * @author Leo Rivera
 */
public class DuelPanel extends JPanel implements DuelListener {
    private Image backgroundImage;
    private Player userPlayer;
    private Player enemyPlayer;
    private CpuHealth enemyPlayerHealth;
    private PlayerHealth userPlayerHealth;
    private Timer timer;
    private int timeRemaining = 5;
    private Duel duel;

    private Font customFont;

    public DuelPanel(Duel duel) {
        userPlayerHealth = new PlayerHealth(15, 25);
        enemyPlayerHealth = new CpuHealth(365, 25);
        this.duel = duel;
        this.customFont = GameData.getInstance().getCustomFont();
        duel.addDuelListener(this);
        userPlayer = duel.getPlayer1();
        enemyPlayer = duel.getPlayer2();

        backgroundImage = new ImageIcon(getClass().getResource("/stage1.png")).getImage();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null)));

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeRemaining > 0) {
                    timeRemaining--;
                } else {
                    timer.stop();
                }
                repaint();
            }
        });
        timer.start();

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

        g.drawImage(userPlayer.getCurrentImage(), userPlayer.getplayerX(), userPlayer.getplayerY(), null);
        if (enemyPlayer instanceof UserPlayer && !((UserPlayer)enemyPlayer).isLocalPlayer()) {
            Image enemyImage = enemyPlayer.getCurrentImage();
            g.drawImage(enemyImage, enemyPlayer.getplayerX() + enemyImage.getWidth(null), enemyPlayer.getplayerY(), -enemyImage.getWidth(null),
                    enemyImage.getHeight(null), null);

        } else {
            g.drawImage(enemyPlayer.getCurrentImage(), enemyPlayer.getplayerX(), enemyPlayer.getplayerY(), null);
        }

        Image userBar = userPlayerHealth.getPlayerhealthbar();
        Image enemyBar = enemyPlayerHealth.getCpuhealthbar();

        g.drawImage(userBar, userPlayerHealth.getUserHealthBarX(), userPlayerHealth.getUserHealthBarY(), null);
        g.drawImage(enemyBar, enemyPlayerHealth.getCpuHealthBarX(), enemyPlayerHealth.getCpuHealthBarY(), null);

        int userHealth = userPlayer.getHealth();
        int cpuHealth = enemyPlayer.getHealth();

        int maxHealthBarWidth = 200;
        int healthBarHeight = 38;

        int playerBarWidth = Math.min(maxHealthBarWidth, (20 * (100 - userHealth)) / 10);
        int cpuBarWidth = Math.min(maxHealthBarWidth, (20 * (100 - cpuHealth)) / 10);

        int playerHealthX = 210 - playerBarWidth;
        int cpuHealthX = 560 - cpuBarWidth;

        g.setColor(Color.black);
        g.fillRect(playerHealthX, 28, playerBarWidth, healthBarHeight); // Player Health Bar
        g.fillRect(cpuHealthX, 28, cpuBarWidth, healthBarHeight); // CPU Health Bar

        //g.setFont(customFont.deriveFont(16f));
        //g.setColor(Color.red);
        //g.drawString("CPU ATTACK IN: " + timeRemaining, 10, 30);
        repaint();

    }

    /* -------------------------------------------------------------------------- */
    /* Handle events and repaint in these methods below */
    /* -------------------------------------------------------------------------- */

    @Override
    public void onPlayerAttack(Player attacker, Player attacked) {
        System.out.println("Player attack");
        attacker.attackAnimation();
        attacked.damageAnimation();
        repaint();
    }

    @Override
    public void onDuelEnd(Player winner, Player loser) {
        System.out.println("Duel End");
        if (winner instanceof UserPlayer) {
            if (((UserPlayer) winner).isLocalPlayer()) {
                GameData.getInstance().setGameOver(true);
            }
        }
        if (loser instanceof UserPlayer) {
            if (((UserPlayer) loser).isLocalPlayer()) {
                GameData.getInstance().setGameOver(false);
            }
        }
    }

    public void setDuel(Duel duelToSet) {
        duel.getListeners().remove(this);

        duelToSet.addDuelListener(this);
        this.duel = duelToSet;
        userPlayer = duel.getPlayer1();
        enemyPlayer = duel.getPlayer2();
    }
}