package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel implements ActionListener {

    private final Image earth; 

    public MapPanel() {
        earth = new ImageIcon(getClass().getResource("/earth.png")).getImage();
        setLayout(null);
        setPreferredSize(new Dimension(earth.getWidth(null), earth.getHeight(null)));
        addButton(70, 40, 55, 25, "x");
        addButton(270, 50, 55, 25, "-");
        addButton(100, 150, 55, 25, "+");
        addButton(230, 300, 55, 25, "/");
        GenericButton selectButton = new GenericButton("SELECT SCENE", 300, 500, 150, 40, Color.black, Color.white, Color.lightGray);
        selectButton.addActionListener(this);
        add(selectButton);
    }

    private void addButton(int x, int y, int width, int height, String text) {
        GenericButton button = new GenericButton(text, x, y, width, height, Color.black, Color.white, Color.lightGray);
        button.addActionListener(this);
        add(button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(earth, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            GameData.getInstance().doButtonAction(this, (Button) e.getSource());
        }    
        if (e.getSource() instanceof GenericButton) {
            GenericButton button = (GenericButton) e.getSource();
            LevelPanel lp = GameData.getInstance().getLevelPanel();
            switch (button.getText()) {
                case "x": 
                    lp.setBackgroundImage("/moon.png");
                    break;
                case "-":
                    lp.setBackgroundImage("/northPole.png");
                    break;
                case "+":
                    lp.setBackgroundImage("/csc309.png");
                    break;
                case "/":
                    lp.setBackgroundImage("/brazil.png");
                    break;
            }

            if (button.getText() == "SELECT SCENE") {
                JFrame main = GameData.getInstance().getMain();
                //TODO
            }
        }    
    }
}
