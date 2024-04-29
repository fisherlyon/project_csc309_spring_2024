package math_tutor_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMain extends JFrame implements ActionListener {

    JPanel startScreen = new JPanel();
    JPanel levelScreen = new JPanel();
    JPanel playScreen = new JPanel();

    public GameMain() {

        // ---- CREATE : Start Screen
        startScreen.setLayout(new GridLayout(1, 1));
        StartPanel startPanel = new StartPanel();
        StartButton playButton = new StartButton("PLAY", 600, 400, 200, 60);
        startPanel.addButton(playButton); // Remove coordinate parameters since we're using absolute positioning
        playButton.addActionListener(this);
        startScreen.add(startPanel);

        // ---- CREATE : Level Select Screen
        levelScreen.setLayout(new GridLayout(1, 2));
        //levelScreen.add(new MapLevel(null));
        //levelScrean.add(new LevelPanel());

        // ---- CREATE : Gameplay Screen
        UserPlayer player = new UserPlayer(75, 200, "player 1", 100);
        CpuPlayer cpu = new CpuPlayer(325, 200,  100);
        playScreen.add(new DuelPanel(player, cpu));
        playScreen.setLayout(new GridLayout(1, 2));
        String[] ops = { "+", "-", "*", "/" };
        MathPanel mathPanel = new MathPanel();
        for (String op : ops) {
            OpButton button = new OpButton(op);
            button.addActionListener(this);
            mathPanel.add(button);
        }
        playScreen.add(mathPanel);
        
        GameData.getInstance().display(this, startScreen, levelScreen, playScreen);
        
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
      
        if (e.getSource() instanceof OpButton) {
            OpButton button = (OpButton) e.getSource();
            GameData.getInstance().setPressedButton(button);
        } else if (e.getSource() instanceof StartButton) {
            StartButton button = (StartButton) e.getSource();
            if (button.getOption().equals("PLAY")) {
                GameData.getInstance().setDisplayScreen("PLAY");
                GameData.getInstance().display(this, startScreen, levelScreen, playScreen);
            }
        }
    }
}
