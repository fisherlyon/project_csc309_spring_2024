package math_tutor_project;

import java.awt.*;
import javax.swing.*;

public class TrashBin extends JComponent {
    private Image image;
    private String IMAGE_PATH = "./math_tutor_project/src/main/resources/trash.png";
    public TrashBin(int x, int y) {
        this.image = new ImageIcon(IMAGE_PATH).getImage();
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
