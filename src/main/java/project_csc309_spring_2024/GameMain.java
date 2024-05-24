package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.InputStream;

public class GameMain extends JFrame implements ActionListener, PropertyChangeListener {

    JPanel startScreen = new JPanel();
    JPanel levelScreen = new JPanel();
    JPanel tutorModeScreen = new JPanel();
    JPanel timeAttackScreen = new JPanel();

    JPanel cpuPvpScreen = new JPanel();

    LevelPanel levelPanel;
    DuelPanel duelPanel;
    DuelPanel duelPanel1;
    ModePanel modePanel;
    TimeScorePanel timeScorePanel;

    String host = "unix4.csc.calpoly.edu";
    int port = 3091;
    Client client = new Client(host, port);

    Font customFont;
    AudioPlayer audioPlayer;


    public GameMain() {
        GameData.getInstance().addPropertyChangeListener(this);
        GameData data = GameData.getInstance();
//        GameData data1 = GameData.getInstance();




        audioPlayer = new AudioPlayer();
        customFont = FontLoader.loadCustomFont("/fonts/Minecraftia-Regular.ttf", 12f);
        SoundLoader.loadSounds(audioPlayer);

        GameData.getInstance().setCustomFont(customFont);
        GameData.getInstance().setAudioPlayer(audioPlayer);


        // ---- CREATE : Start Screen
        startScreen.setLayout(new GridLayout(1, 1));
        StartPanel startPanel = new StartPanel();
        Button playButton = new Button("PRESS TO START", 600, 400, 200, 60);
        playButton.setButtonColor(Color.blue, Color.white);
        playButton.addSelf(startPanel);
        playButton.setFont(customFont.deriveFont(12f));
        playButton.addActionListener(this);
        startScreen.add(startPanel);

        // ---- CREATE : Mode Select Screen
        modePanel = new ModePanel();
        Button continueButton = new Button("CONTINUE", 300, 390, 150, 40);
        continueButton.setButtonColor(Color.white, Color.black);
        continueButton.setFont(customFont.deriveFont(12f));
        continueButton.addSelf(modePanel.getButtonSidePanel());
        continueButton.addActionListener(this);

        // ---- CREATE : Level Select Screen
        levelScreen.setLayout(null);
        levelPanel = new LevelPanel();
        levelPanel.setBounds(0, 0, 600, 600);
        levelScreen.add(levelPanel);
        MapPanel mapPanel = new MapPanel();
        mapPanel.setBounds(600, 0, 600, 500);
        levelScreen.add(mapPanel);
        Button selectButton = new Button("SELECT SCENE", 300, 450, 150, 40);
        selectButton.addSelf(mapPanel);
        selectButton.setFont(customFont.deriveFont(12f));
        selectButton.addActionListener(this);
        WeatherPanel weatherPanel = new WeatherPanel();
        weatherPanel.setBounds(600, 500, 600, 100);
        levelScreen.add(weatherPanel);

        // ---- CREATE : Default Duel
        UserPlayer player = new UserPlayer(75, 200, "player 1", 100);
        player.setLocalPlayer();
        CpuPlayer cpu = new CpuPlayer(325, 200, 100);

        Duel computerDuel = new Duel(player, cpu);
        Level level = new Level(computerDuel, 0);
        data.setLevel(level);
        duelPanel = new DuelPanel(data.getLevel().getDuel());

//        duelPanel1 = new DuelPanel(data1.getLevel().getDuel());

        // ---- CREATE : Tutor Mode Screen
        tutorModeScreen.setLayout(new GridLayout(1, 2));
        tutorModeScreen.add(duelPanel);

        GameData.getInstance().recalculate();
        MathPanel mathPanel = new MathPanel();
        tutorModeScreen.add(mathPanel);
        add(startScreen);

        GameController controller = new GameController(player, cpu);
        mathPanel.addMouseListener(controller);
        mathPanel.addMouseMotionListener(controller);

        GameData.getInstance().addPropertyChangeListener(mathPanel);

        // ---- CREATE : Time Attack Screen
        timeAttackScreen.setLayout(new GridLayout(1, 2));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        MathPanel mathPanelTT = new MathPanel();
        mathPanelTT.remove(GameData.getInstance().getTutor());
        mathPanelTT.addMouseListener(controller);
        mathPanelTT.addMouseMotionListener(controller);
        GameData.getInstance().addPropertyChangeListener(mathPanelTT);
        timeScorePanel = new TimeScorePanel();
        leftPanel.add(timeScorePanel);
        /// add other thing
        timeAttackScreen.add(leftPanel);
        timeAttackScreen.add(mathPanelTT);

        // ---- CREATE : Cpu Pvp Screen
//        cpuPvpScreen.setLayout(new GridLayout(1, 2));
//        cpuPvpScreen.add(duelPanel1);
//        GameData.getInstance().recalculate();
//        MathPanel mathPanel1 = new MathPanel();
//        cpuPvpScreen.add(mathPanel1);
//        add(startScreen);
//        GameController controller1 = new GameController(player, cpu);
//        mathPanel1.addMouseListener(controller1);
//        mathPanel1.addMouseMotionListener(controller1);
//        GameData.getInstance().addPropertyChangeListener(mathPanel1);


    }


    public static void main(String[] args) {
        GameMain main = new GameMain();
        main.setTitle("Math Madness");
        main.setSize(1200, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("gameOver".equals(evt.getPropertyName())) {
            boolean isWinner = (boolean) evt.getNewValue();
            new GameOverDialog(this, isWinner);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            Button button = (Button) e.getSource();
            String label = button.getLabel();

            getContentPane().removeAll();

            switch (label) {
                case "PRESS TO START":
                    audioPlayer.play("select");
                    getContentPane().add(modePanel);
                    break;
                case "CONTINUE":
                    audioPlayer.play("select");
                    getContentPane().add(levelScreen);
                    break;
                case "SELECT SCENE":
                    if (GameData.getInstance().getGameMode().equals("Join PvP Game")) {
                        client.start();
                        while (!client.isReady()) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } else if (GameData.getInstance().getGameMode().equals("Tutor Mode")) {
                        getContentPane().add(tutorModeScreen);
                    } else if (GameData.getInstance().getGameMode().equals("Time Attack")) {
                        getContentPane().add(timeAttackScreen);
                        timeScorePanel.startTimer();
                    }
//                    else if (GameData.getInstance().getGameMode().equals("CPU PvP")){
//                        getContentPane().add(cpuPvpScreen);
//                        duelPanel1.setDuel(GameData.getInstance().getLevel().getDuel());
//                        duelPanel1.setBackgroundImage(levelPanel.getBackgroundImage());
////                        timeScorePanel.startTimer();
//                    }
                    audioPlayer.play("select");
                    duelPanel.setDuel(GameData.getInstance().getLevel().getDuel());
                    duelPanel.setBackgroundImage(levelPanel.getBackgroundImage());
                    break;
                default:
                    break;
            }
            revalidate();
            repaint();
        }
    }
}
