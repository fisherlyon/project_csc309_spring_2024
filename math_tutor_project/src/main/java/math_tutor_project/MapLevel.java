package math_tutor_project;

import javax.swing.*;
import java.awt.*;

public class MapLevel extends JPanel {
    private final Image Earth;
    private DuelPanel duelPanel; // Reference to DuelPanel

    public MapLevel(DuelPanel duelPanel) {
        this.duelPanel = duelPanel; // Store the reference to DuelPanel
        Earth = new ImageIcon(getClass().getResource("/earth.png")).getImage();
        setLayout(null);
        setPreferredSize(new Dimension(Earth.getWidth(null), Earth.getHeight(null)));
        addButton(70, 40, 50, 20, "x");
        addButton(270, 50, 50, 20, "-");
        addButton(100, 150, 50, 20, "+");
        addButton(230, 300, 50, 20, "/");
    }
    private void addButton(int x, int y, int width, int height, String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.white);
        button.setForeground(Color.black);
        button.setFocusPainted(false);
        button.setBounds(x, y, width, height);
        button.addActionListener(e -> {
            // Use the stored reference to change the background
            switch (text) {
                case "x":
                    duelPanel.setBackgroundImage("/moon.png");
                    break;
                case "-":
                    duelPanel.setBackgroundImage("/northPole.png");
                    break;
                case "+":
                    duelPanel.setBackgroundImage("/csc309.png");
                    break;
                case "/":
                    duelPanel.setBackgroundImage("/temples.png");
                    break;
            }
        });
        add(button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Earth, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
