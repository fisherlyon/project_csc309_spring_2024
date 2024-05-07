package project_csc309_spring_2024;

import java.awt.*;
import javax.swing.*;

/**
 * A panel that acts as the start screen.
 * It displays the Math Madness logo and 
 * acts as a transition to the level screen.
 * 
 * @author
 */

public class StartPanel extends JPanel {

    public StartPanel() {
        setBackground(Color.BLACK);
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image logo = new ImageIcon(getClass().getResource("/math-madness2.png")).getImage();
        g.drawImage(logo, 400, 0, 400, 400, this);
    }

}
