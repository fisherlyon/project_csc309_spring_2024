package project_csc309_spring_2024;


import javax.swing.*;
import java.awt.*;

/**
 * ...
 *
 * @author Leo Rivera
 */
public class UserPlayer extends Player{
    private Image playerone;
    private String name;
    private boolean isLocalPlayer;
    public UserPlayer(int x, int y, String name, int health) {
        super(x,y, health );
        this.name = name;
        playerone = new ImageIcon(getClass().getResource("/bobby.png")).getImage();

    }

    public Image getPlayerOne() { return playerone; }
    public void setPlayerOne(Image playerone){
        this.playerone = playerone;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isLocalPlayer() {
        return isLocalPlayer;
    }
    public void setLocalPlayer(){
        isLocalPlayer = true; 
    }
}
