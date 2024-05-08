package project_csc309_spring_2024;


import javax.swing.*;
import java.awt.*;

/**
 * ...
 *
 * @author Leo Rivera
 */
public class CpuPlayer {
    private Image playertwo;
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

    public Image getPlayerTwo() {
        return playertwo;
    }

    public void setPlayerTwo(Image playertwo){
        this.playertwo = playertwo;
    }

    public int getcpuX() { return x; }
    public int getcpuY() { return y; }
    public String getName() { return name; }
    public int getCpuHealth() { return health; }
    public void setCpuHealth(int health) { this.health = health;}
}
