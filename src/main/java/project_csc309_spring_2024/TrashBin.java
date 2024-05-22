package project_csc309_spring_2024;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Eric Berber
 */

public class TrashBin extends JComponent {
    private Image image;
    public TrashBin(int x, int y) {
        this.image = new ImageIcon(getClass().getResource("/trash.png")).getImage();
        setLocation(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, getX(), getY(), null);
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= getX() && x <= getX() + image.getWidth(null) &&
                y >= getY() && y <= getY() + image.getHeight(null);
    }

    public Image getImage(){
        return image;
    }
}
