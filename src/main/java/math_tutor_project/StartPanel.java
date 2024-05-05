package math_tutor_project;

import java.awt.*;
import javax.swing.*;

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