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
    private boolean selected = false;
    private boolean answer = false;
    
    public Block(int blockX, int blockY, int dim, int value) {
        this.blockX = blockX - dim / 2; // Center the disk horizontally
        this.blockY = blockY - dim / 2;
        this.dim = dim;
        this.value = value;
    }

    public void draw(Graphics g) {
        if (GameData.getInstance().getSelectedBlock() == this) {
            g.setColor(new Color(128, 0, 128));
            g.fillRect(blockX, blockY, dim, dim);
            g.setColor(Color.BLACK);
            g.drawRect(blockX, blockY, dim, dim);
        } else {
            g.setColor(new Color(0, 200, 200));
            g.fillRect(blockX, blockY, dim, dim);
            g.setColor(Color.BLACK);
            g.drawRect(blockX, blockY, dim, dim);
        }
        g.setFont(new Font("Impact", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(Integer.toString(value));
        int textHeight = fm.getHeight();
        int x = blockX + (dim - textWidth) / 2;
        int y = blockY + (dim + textHeight - 10) / 2;
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(value), x, y);
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
    public Boolean isSelected() { return selected; }
    public void setSelected(Boolean b) { selected = b; }
    public Boolean isAnswer() { return answer; }
    public void setAnswer(Boolean b) { answer = b; }
}