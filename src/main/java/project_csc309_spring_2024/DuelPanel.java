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
    private CpuHealth cpuHealth;
    private PlayerHealth playerHealth;
    private int prevUserHealth;
    private int prevCpuHealth;
    private Timer timer;
    private int timeRemaining = 5;
    private Duel duel;

    private Font customFont;

    private PlayerHealth phealth;
    private CpuHealth chealth;

    private UserPlayer player;
    private CpuPlayer cpu;

    private Image bobbyhit = new ImageIcon(getClass().getResource("/bobbydamaged.png")).getImage();
    private Image bobbyattack = new ImageIcon(getClass().getResource("/bobbyattack.png")).getImage();
    private Image grampshit = new ImageIcon(getClass().getResource("/grampsdamaged.png")).getImage();
    private Image grampsattack = new ImageIcon(getClass().getResource("/grampsattack.png")).getImage();

    public DuelPanel(Duel duel){
        player  = new UserPlayer(75, 200, "player 1", 100);
        cpu  = new CpuPlayer(325, 200, 100);
        phealth = new PlayerHealth(15, 25);
        chealth = new CpuHealth(365, 25);
        this.duel = duel;
        this.customFont = GameData.getInstance().getCustomFont();
        duel.addDuelListener(this);
        userPlayer = duel.getPlayer1();
        enemyPlayer = duel.getPlayer2();
        add(userPlayer);
        add(enemyPlayer);

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


        /* -------------------------------------------------------------------------- */
        /*                  Paint players in own class paintComponent                 */
        /* -------------------------------------------------------------------------- */

        g.drawImage(player.getPlayerOne(), 75, 200, this);
        g.drawImage(cpu.getPlayerTwo(), 325, 200, this);
        g.drawImage(phealth.getPlayerhealthbar(), 15, 25, this);
        g.drawImage(chealth.getCpuhealthbar(), 365, 25, this);


        if (playerHealth != null) {
            g.drawImage(playerHealth.getPlayerhealthbar(), playerHealth.getUserHealthBarX(), playerHealth.getUserHealthBarY(), this);
        }
        if (cpuHealth != null) {
            g.drawImage(cpuHealth.getCpuhealthbar(), cpuHealth.getCpuHealthBarX(), cpuHealth.getCpuHealthBarY(), this);
        }

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

        g.setFont(customFont.deriveFont(16f));
        g.setColor(Color.red);
        g.drawString("CPU ATTACK IN: " + timeRemaining, 10, 30);

        prevUserHealth = userHealth;
        prevCpuHealth = cpuHealth;
        repaint();


    }

    /* -------------------------------------------------------------------------- */
    /*           Handle events and  repaint in these methods below                */
    /* -------------------------------------------------------------------------- */

    @Override
    public void onPlayerAttack(Player attacker, Player attacked) {
        System.out.println("Player attack");
        if (attacker instanceof UserPlayer){
            Image tempGramps = cpu.getPlayerTwo();
            cpu.setPlayerTwo(grampsattack);
            Timer timer2 = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cpu.setPlayerTwo(tempGramps);
                }
            });

            timer2.setRepeats(false);
            timer2.start();


            Image temp = player.getPlayerOne();
            player.setPlayerOne(bobbyhit);
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.setPlayerOne(temp);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        else{
            Image tempBobby = player.getPlayerOne();
            player.setPlayerOne(bobbyattack);
            Timer timer2 = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.setPlayerOne(tempBobby);
                }
            });

            timer2.setRepeats(false);
            timer2.start();



            Image tempGramps = cpu.getPlayerTwo();
            cpu.setPlayerTwo(grampshit);
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cpu.setPlayerTwo(tempGramps);
                }
            });
            timer.setRepeats(false);
            timer.start();


        }



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

    public void setDuel(Duel duelToSet){
        duel.getListeners().remove(this);
        removeAll();

        duelToSet.addDuelListener(this);
        userPlayer = duel.getPlayer1();
        enemyPlayer = duel.getPlayer2();
        add(userPlayer);
        add(enemyPlayer);
    }
}