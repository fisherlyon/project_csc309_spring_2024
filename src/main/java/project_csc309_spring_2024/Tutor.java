package project_csc309_spring_2024;


import java.awt.*;
import javax.swing.*;
import java.lang.Integer;

/**
 * 
 * @author Eric Berber
 */
public class Tutor extends JComponent{    
    
    public int TUTOR_BOX_HEIGHT = 100;
    public int TUTOR_BOX_WIDTH = 350;
    private String message; 
    public Tutor(int x, int y){
        setLocation(x, y);
        setVisible(false);
    }


    public void  help(){
        message = "That is not correct";
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isVisible() && message != null){
            g.drawRect(getX(), getY(), TUTOR_BOX_WIDTH, TUTOR_BOX_HEIGHT);
            g.drawString(message, getX(), getY() + 50);
        }
    }
}
