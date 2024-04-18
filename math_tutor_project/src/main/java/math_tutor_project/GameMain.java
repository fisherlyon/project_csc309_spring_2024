package math_tutor_project;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {

    public GameMain() {
        
      setLayout(new GridLayout(1, 2));

      MathPanel mathPanel = new MathPanel();
      DuelPanel duelPanel = new DuelPanel();
      add(duelPanel);
      add(mathPanel);

      GameController controller = new GameController();
      mathPanel.addMouseListener(controller);
      mathPanel.addMouseMotionListener(controller);
      mathPanel.addComponentListener(controller);

      GameData.getInstance().addPropertyChangeListener(mathPanel);
    }
  
    public static void main(String[] args) {
  
      GameMain main = new GameMain();
      main.setTitle("Mathematical Madness");
      main.setSize(1200, 600);
      main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main.setVisible(true);
    }
  }
