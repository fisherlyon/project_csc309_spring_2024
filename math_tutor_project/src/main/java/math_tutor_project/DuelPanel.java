package math_tutor_project;

import javax.swing.*;
import java.awt.*;

public class DuelPanel extends JPanel {
    
    public DuelPanel() {
        setBackground(Color.BLACK);
        JLabel tempLabel = new JLabel("DUEL PANEL");
        tempLabel.setForeground(Color.WHITE); // Set text color
        tempLabel.setOpaque(true); // Make the label opaque
        tempLabel.setBackground(Color.BLACK); // Set background color
        add(tempLabel);
    }
} 