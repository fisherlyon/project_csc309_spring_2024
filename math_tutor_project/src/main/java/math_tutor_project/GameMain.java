package math_tutor_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMain extends JFrame implements ActionListener {

    public GameMain() {
        
      setLayout(new GridLayout(1, 3));

      String[] ops = { "+", "-", "*", "/" };
      MathPanel mathPanel = new MathPanel();
      for (String op : ops) {
        OpButton button = new OpButton(op);
          button.setBackground(Color.gray);
          button.setForeground(Color.black);
//          button.setBorder(BorderFactory.createEmptyBorder());
          button.setFocusPainted(false);
        button.addActionListener(this);
        mathPanel.add(button);
      }

      PlayerHealth phealth = new PlayerHealth(15, 25);
      CpuHealth chealth = new CpuHealth(365, 25);

      UserPlayer player = new UserPlayer(75, 200, "player 1", 100);
      CpuPlayer cpu = new CpuPlayer(325, 200,  100);
      DuelPanel duelPanel = new DuelPanel(player, cpu, chealth, phealth);

      UserPlayer player2 = new UserPlayer(75, 200, "player 1", 100);
      CpuPlayer cpu2 = new CpuPlayer(325, 200,  100);
      MapLevel mapLevel = new MapLevel(duelPanel);

      add(duelPanel);
      add(mathPanel);
      //add(mapLevel);

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
      }
    }
  }
