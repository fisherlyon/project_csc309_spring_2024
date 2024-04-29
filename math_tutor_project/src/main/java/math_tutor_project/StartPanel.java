package math_tutor_project;

import java.awt.*;
import javax.swing.*;

public class StartPanel extends JPanel {

    public StartPanel() {
        setBackground(Color.BLACK);
        setLayout(null);
    }

    public void addButton(StartButton button) {
        button.setBackground(Color.white);
        button.setForeground(Color.black);
        button.setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        button.setPreferredSize(new Dimension(button.getWidth(), button.getHeight()));
        add(button);
    }
}
