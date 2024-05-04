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
        GenericButton playButton = new GenericButton("START", 600, 400, 200, 60, Color.white, Color.blue, Color.red);
        playButton.addSelf(startPanel);
        playButton.addActionListener(this);
        startScreen.add(startPanel);

        // ---- CREATE : Level Select Screen
        levelScreen.setLayout(new GridLayout(1, 2));
        //levelScreen.add(new MapLevel());
        //levelScrean.add(new LevelPanel());

        // ---- CREATE : Gameplay Screen
        PlayerHealth phealth = new PlayerHealth(15, 25);
        CpuHealth chealth = new CpuHealth(365, 25);
  
        UserPlayer player = new UserPlayer(75, 200, "player 1", 100);
        CpuPlayer cpu = new CpuPlayer(325, 200,  100);
        DuelPanel duelPanel = new DuelPanel(player, cpu, chealth, phealth);
        playScreen.add(duelPanel);
        //UserPlayer player2 = new UserPlayer(75, 200, "player 1", 100);
        //CpuPlayer cpu2 = new CpuPlayer(325, 200,  100);
        //MapLevel mapLevel = new MapLevel(duelPanel);
        String[] ops = { "+", "-", "*", "/" };
        MathPanel mathPanel = new MathPanel();
        for (int i = 0; i < ops.length; i++) {
            OpButton button = new OpButton(ops[i], i * 70 + 180, 10, 55, 55, Color.white, Color.darkGray, Color.gray);
            button.addActionListener(this);
            button.addSelf(mathPanel);
        }
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

        if (e.getSource() instanceof Button) {
            GameData.getInstance().doButtonAction(this, (Button) e.getSource());
        }
      
        if (e.getSource() instanceof GenericButton) {
            GenericButton button = (GenericButton) e.getSource();
            if (button.getLabel() == "START") {
                getContentPane().removeAll();
                getContentPane().add(levelScreen);
                revalidate(); 
            } else if (button.getLabel() == "PROCEED") {
                getContentPane().removeAll();
                getContentPane().add(playScreen);
                revalidate();
            }
        } 

        else if (e.getSource() instanceof OpButton) {
            OpButton button = (OpButton) e.getSource();
            GameData.getInstance().setPressedButton(button);
        }
    }
}
