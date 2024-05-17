package project_csc309_spring_2024;


import javax.swing.*;
import java.awt.*;

/**
 * ...
 *
 * @author Leo Rivera
 */
public class CpuPlayer extends Player{
    private Image playertwo;
    private String name = "CPU";
    public CpuPlayer(int x, int y, int health) {
        super(x, y, health);
        playertwo = new ImageIcon(getClass().getResource("/gramps.png")).getImage();
    }

    public Image getPlayerTwo() {
        return playertwo;
    }

    public void setPlayerTwo(Image playertwo){
        this.playertwo = playertwo;
    }

    public String getName() { return name; }
}
