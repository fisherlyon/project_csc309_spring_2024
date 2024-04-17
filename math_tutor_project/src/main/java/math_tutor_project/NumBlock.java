package math_tutor_project;

import java.awt.*;

/**
 * ...
 * 
 * @author Fisher Lyon
 */
public class NumBlock {

    private int blockX;
    private int blockY;
    private int dim;
    private Color color;
    private Font font;
    private int value;
    
    public NumBlock(int blockX, int blockY, int dim, Color color, int value) {
        this.blockX = blockX - dim / 2; // Center the disk horizontally
        this.blockY = blockY;
        this.dim = dim;
        this.color = color;
        this.value = value;
    }

    public void draw(Graphics g) {
        if (GameData.getInstance().getSelectedBlock() == this) {
            g.setColor(Color.YELLOW);
            g.fillRect(blockX, blockY, dim, dim);
            g.setColor(Color.RED);
            g.drawRect(blockX, blockY, dim, dim);
        } else {
            g.setColor(color);
            g.fillRect(blockX, blockY, dim, dim);

            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(value), blockX, blockY);
        }
    }

    public boolean contains(int x, int y) {
        return x >= blockX && x <= blockX + dim && y >= blockY && y <= blockY + dim;
    }

    public int getBlockX() { return blockX; }
    public void setBlockX(int x) { this.blockX = x; }
    public int getBlockY() { return blockY; }
    public void setBlockY(int y) { this.blockY = y; }
    public int getDim() { return dim; }
    public int getValue() { return value; }
}
