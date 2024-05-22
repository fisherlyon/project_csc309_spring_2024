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
    JPanel playScreen = new JPanel();

    LevelPanel levelPanel;
    DuelPanel duelPanel;
    ModePanel modePanel;

    Font customFont;

    String host = "unix4.csc.calpoly.edu";
    int port = 3091;
    Client client = new Client(host, port);
    public GameMain() {
        GameData.getInstance().addPropertyChangeListener(this);
        GameData data = GameData.getInstance();
        loadCustomFont();

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
        continueButton.setButtonColor(Color.orange, Color.white);
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
        CpuPlayer cpu = new CpuPlayer(325, 200, 100);

        Duel computerDuel = new Duel(player, cpu);
        Level level = new Level(computerDuel, 0);
        data.setLevel(level);
        duelPanel = new DuelPanel(data.getLevel().getDuel());

        // ---- CREATE : Gameplay Screen
        playScreen.setLayout(new GridLayout(1, 2));
        playScreen.add(duelPanel);

        MathPanel mathPanel = new MathPanel();
        playScreen.add(mathPanel);
        add(startScreen);

        GameController controller = new GameController(player, cpu);
        mathPanel.addMouseListener(controller);
        mathPanel.addMouseMotionListener(controller);

        GameData.getInstance().addPropertyChangeListener(mathPanel);
    }

    private void loadCustomFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Minecraftia-Regular.ttf");
            if (is == null) {
                System.out.println("Font file not found");
                return;
            }

            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            System.out.println("Font loaded successfully");
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
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
                    getContentPane().add(modePanel);
                    break;
                case "CONTINUE":
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
                    }
                    duelPanel.setDuel(GameData.getInstance().getLevel().getDuel());
                    duelPanel.setBackgroundImage(levelPanel.getBackgroundImage());
                    getContentPane().add(playScreen);
                    break;
                default:
                    break;
            }
            revalidate();
            repaint();
        }
    }
}
