package math_tutor_project;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * 
 * @author Fisher Lyon
 */

public abstract class Button extends JButton {

    private String label;
    private int x;
    private int y;
    private int w;
    private int h;

    public Button(String label, int x, int y, int w, int h) {
        this.label = label;
        this.x = x - w / 2; // center
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void addSelf(JPanel panel) {
        setText(label);
        setBounds(this.x, this.y, this.w, this.h);
        setPreferredSize(new Dimension(this.w, this.h));
        panel.add(this);
    }

    public String getLabel() { return this.label; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getW() { return this.w; }  
    public int getH() { return this.h; }
}
