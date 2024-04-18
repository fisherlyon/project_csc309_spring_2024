package math_tutor_project;

import java.awt.*;

/**
 * ...
 * 
 * @author Fisher Lyon
 */
public class Block {

    private int blockX;
    private int blockY;
    private int dim;
    private int value;
    
    public Block(int blockX, int blockY, int dim, int value) {
        this.blockX = blockX - dim / 2; // Center the disk horizontally
        this.blockY = blockY;
        this.dim = dim;
        this.value = value;
    }

    public void draw(Graphics g) {
        if (GameData.getInstance().getSelectedBlock() == this) {
            g.setColor(Color.RED);
            g.fillRect(blockX, blockY, dim, dim);
            g.setColor(Color.BLACK);
            g.drawRect(blockX, blockY, dim, dim);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(blockX, blockY, dim, dim);
            g.setColor(Color.BLACK);
            g.drawRect(blockX, blockY, dim, dim);
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
