package math_tutor_project;

import javax.swing.*;

public class GameMain extends JFrame {

    public GameMain() {
    }
  
    public static void main(String[] args) {
  
      GameMain main = new GameMain();
      main.setTitle("Mathematical Madness");
      main.setSize(600, 400);
      main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main.setVisible(true);
    }
  }
