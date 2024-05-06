package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMain extends JFrame implements ActionListener {

    JPanel startScreen = new JPanel();
    JPanel levelScreen = new JPanel();
    JPanel playScreen = new JPanel();

    public GameMain() {
        GameData.getInstance().setMain(this);

        // ---- CREATE : Start Screen
        startScreen.setLayout(new GridLayout(1, 1));
        StartPanel startPanel = new StartPanel();
        GenericButton playButton = new GenericButton("PRESS TO START", 600, 400, 200, 60, Color.white, Color.blue, Color.red);
        playButton.addSelf(startPanel);
        playButton.addActionListener(this);
        startScreen.add(startPanel);

        // ---- CREATE : Level Select Screen
        levelScreen.setLayout(new GridLayout(1, 2));
        levelScreen.add(new LevelPanel());
        levelScreen.add(new MapPanel());

        // ---- CREATE : Gameplay Screen
        playScreen.setLayout(new GridLayout(1, 2));
        PlayerHealth phealth = new PlayerHealth(15, 25);
        CpuHealth chealth = new CpuHealth(365, 25);
  
        UserPlayer player = new UserPlayer(75, 200, "player 1", 100);
        CpuPlayer cpu = new CpuPlayer(325, 200,  100);
        DuelPanel duelPanel = new DuelPanel(player, cpu, chealth, phealth);
        playScreen.add(duelPanel);
        //UserPlayer player2 = new UserPlayer(75, 200, "player 1", 100);
        //CpuPlayer cpu2 = new CpuPlayer(325, 200,  100);
        //MapLevel mapLevel = new MapLevel(duelPanel);
        MathPanel mathPanel = new MathPanel();
 
        playScreen.add(mathPanel);
        
        add(startScreen);
        
        GameController controller = new GameController();
        mathPanel.addMouseListener(controller);
        mathPanel.addMouseMotionListener(controller);

      GameData.getInstance().addPropertyChangeListener(mathPanel);
    }

    public static void main(String[] args) {
        GameMain main = new GameMain();
        main.setTitle("Mathematical Madness");
        main.setSize(1200, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        if (e.getSource() instanceof GenericButton) {
            GenericButton button = (GenericButton) e.getSource();
            if (button.getLabel() == "PRESS TO START") {
                getContentPane().removeAll();
                getContentPane().add(levelScreen);
                revalidate(); 
            } else if (button.getLabel() == "SELECT SCENE") {
                getContentPane().removeAll();
                getContentPane().add(playScreen);
                revalidate();
            }
        } 
    }
}
