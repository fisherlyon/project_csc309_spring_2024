package math_tutor_project;

import java.awt.*;
import javax.swing.*;

public class TrashBin extends JComponent {
    private int x;
    private int y;
    private Image image;
    private String IMAGE_PATH = "./math_tutor_project/src/main/resources/trash.png";

    public TrashBin(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(IMAGE_PATH).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, null);
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= this.x && x <= this.x + image.getWidth(null) &&
                y >= this.y && y <= this.y + image.getHeight(null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage(){
        return image;
    }
}
