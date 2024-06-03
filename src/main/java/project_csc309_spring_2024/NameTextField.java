package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

public class NameTextField extends JPanel {

   private JTextField nameField;
    
    public NameTextField(String message) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel messageLabel = new JLabel("<html>" + message.replaceAll("\n", "<br>") + "</html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(messageLabel, constraints);

        JLabel nameLabel = new JLabel("Enter your name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(nameField, constraints);
    }

    public String getName() {
        return nameField.getText(); 
    }
}
