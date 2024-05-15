package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel that displays the world map with a series
 * of buttons that activate different level previews
 * on LevelPanel.
 * 
 * @author Fisher and Leo
 */
public class MapPanel extends JPanel implements ActionListener {

    private final Image earth; 

    public MapPanel() {
        earth = new ImageIcon(getClass().getResource("/earth.png")).getImage();
        setLayout(null);
        setPreferredSize(new Dimension(earth.getWidth(null), earth.getHeight(null)));
        addButton(70, 40,80, 25, "Moon");
        addButton(270, 50, 110, 25, "North Pole");
        addButton(100, 150, 100, 25, "CSC 309");
        addButton(230, 300, 80, 25, "Brazil");
    }

    private void addButton(int x, int y, int width, int height, String text) {
        GenericButton button = new GenericButton(text, x, y, width, height, Color.black, Color.white, Color.lightGray, 15);
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
            GameData.getInstance().setSceneSelectionString(button.getText());
        }    
    }
}
