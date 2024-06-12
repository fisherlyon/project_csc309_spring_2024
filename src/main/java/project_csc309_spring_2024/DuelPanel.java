package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * A panel that displays the duel between players.
 *
 * @author Leo Rivera
 */
public class DuelPanel extends JPanel implements DuelListener, ActionListener {
    private Image backgroundImage;
    private Player userPlayer;
    private Player enemyPlayer;
    private CpuHealth enemyPlayerHealth;
    private PlayerHealth userPlayerHealth;
    private Duel duel;

    private Timer timer;
    private int timeRemaining = 10;
    private boolean timerStarted = false;
    private boolean gameEnded = false; // Flag to check if the game has ended
    private Random random = new Random(); // Create an instance of Random

    public DuelPanel(Duel duel) {
        userPlayerHealth = new PlayerHealth(15, 25);
        enemyPlayerHealth = new CpuHealth(365, 25);
        this.duel = duel;
        duel.addDuelListener(this);
        userPlayer = duel.getPlayer1();
        enemyPlayer = duel.getPlayer2();

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

        g.drawImage(userPlayer.getCurrentImage(), userPlayer.getplayerX(), userPlayer.getplayerY(), null);
        if (enemyPlayer instanceof UserPlayer && !((UserPlayer) enemyPlayer).isLocalPlayer()) {
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

        if (timer != null) {
            g.setFont(GameData.getInstance().getCustomFont().deriveFont(24f));
            g.setColor(Color.RED);
            g.drawString("CPU ATTACKS IN: " + timeRemaining, 170, 40);
        }

        repaint();
    }

    private void setupTimer() {
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameEnded) { // Prevent timer action if the game has ended
            if (timeRemaining > 0) {
                timeRemaining--;
            } else {
                duel.castAttack(enemyPlayer, userPlayer);
                timeRemaining = random.nextInt(10) + 1; // Generate a random number between 1 and 10
            }
            repaint();
        }
    }

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
        gameEnded = true; // Set the flag to indicate the game has ended
        if (winner instanceof UserPlayer) {
            if (((UserPlayer) winner).isLocalPlayer()) {
                if(GameData.getInstance().getGameMode().equals("Story Mode")){
                    GameMain main = (GameMain)GameData.getInstance().getMainFrame();
                    main.setLevelScreen();
                }
                else{
                    GameData.getInstance().setGameOver(true);
                }
            }
        }
        if (loser instanceof UserPlayer) {
            if (((UserPlayer) loser).isLocalPlayer()) {
                GameData.getInstance().setGameOver(false);
            }
        }
        if (timer != null) {
            timer.stop();
        }

        duel.reset();
    }

    public void setDuel(Duel duelToSet) {
        duel.getListeners().remove(this);

        duelToSet.addDuelListener(this);
        this.duel = duelToSet;
        userPlayer = duel.getPlayer1();
        enemyPlayer = duel.getPlayer2();

        String gameMode = GameData.getInstance().getGameMode();
        if ("CPU PvP".equals(gameMode) && !timerStarted) {
            setupTimer();
            timerStarted = true;
        }
    }
}
