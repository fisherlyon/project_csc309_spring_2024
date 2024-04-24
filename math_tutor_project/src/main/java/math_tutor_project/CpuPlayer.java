package math_tutor_project;

import javax.swing.*;
import java.awt.*;

/**
 * ...
 *
 * @author Leo Rivera
 */
public class CpuPlayer {
    private final Image playertwo;
    private int health;
    private String name = "CPU";
    private  int x;
    private int y;

    public CpuPlayer(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
        playertwo = new ImageIcon(getClass().getResource("/gramps.png")).getImage();
    }

    public int getcpuX() { return x; }
    public int getcpuY() { return y; }
    public Image getImage() { return playertwo; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
}
