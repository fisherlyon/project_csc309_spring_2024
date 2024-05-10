package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

/**
 * Blocks that have numbers that the player uses
 * to drag and merge with other blocks to solve
 * the provided math problem.
 * 
 * @author Fisher Lyon
 */
public abstract class Button extends JButton {

    private String label;
    private int x;
    private int y;
    private int w;
    private int h;
    private Color fc1;
    private Color bc1;
    private Color bc2;

    public Button(String label, int x, int y, int w, int h, Color fc1, Color bc1, Color bc2) {
        this.label = label;
        this.x = x - w / 2; // center
        this.y = y;
        this.w = w;
        this.h = h;
        this.fc1 = fc1;
        this.bc1 = bc1;
        this.bc2 = bc2;
        setDefaultColor();
        setButtonSpecs();

    }

    public void addSelf(JPanel panel) {
        panel.add(this);
    }

    public void setDefaultColor() {
        setBackground(bc1);
        setForeground(fc1);
    }

    public void setPressedColor() {
        setBackground(bc2);
    }

    private void setButtonSpecs() {
        setFont(new Font("Impact", 20, 20));
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setText(label);
        setBounds(this.x, this.y, this.w, this.h);
        setPreferredSize(new Dimension(this.w, this.h));
    }

    public String getLabel() { return this.label; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getW() { return this.w; }  
    public int getH() { return this.h; }
}
