package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


/**
 * WORK IN PROGRESS
 * 
 * @author Fisher
 */
public class TimeAttackPanel extends JPanel implements ActionListener {

    public TimeAttackPanel() {
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TimeAttackPanel());
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
