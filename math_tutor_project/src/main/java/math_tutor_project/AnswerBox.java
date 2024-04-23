package math_tutor_project;

import java.awt.*;

public class AnswerBox {

    private int answerBoxX;
    private int answerBoxY;
    private Block answerBlock = null;
    private boolean filled = false;
    private int dim = 50;

    public AnswerBox(int answerBoxX, int answerBoxY) {
        this.answerBoxX = answerBoxX - dim / 2; // Center the box horizontally;
        this.answerBoxY = answerBoxY - dim / 2;
    }

    public void draw(Graphics g) {
        if (isFilled()) {
            g.setColor(Color.blue);
            g.fillRect(answerBoxX, answerBoxY, dim, dim);
            g.setColor(Color.black);
            g.drawRect(answerBoxX, answerBoxY, dim, dim);
        }
        else {
            g.setColor(Color.black);
            g.drawRect(answerBoxX, answerBoxY, dim, dim);
        }
    }

    public boolean contains(int x, int y) {
        return x >= answerBoxX && x <= answerBoxX + dim && y >= answerBoxY && y <= answerBoxY + dim;
    }

    public Block getAnswerBlock() {
        if (answerBlock != null) {
            return answerBlock;
        }
        return null;
    } 

    public void setAnswerBlock(Block b) { answerBlock = b; }
    public void setFilled(boolean b) { filled = b; }
    public boolean isFilled() { return filled; }
    public int getX() { return this.answerBoxX; }
    public int getY() { return this.answerBoxY; }
}
