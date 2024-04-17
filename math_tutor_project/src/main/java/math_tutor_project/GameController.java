package math_tutor_project;

import java.awt.event.*;

/**
 * ...
 * 
 * @author Fisher
 */
public class GameController implements MouseListener, MouseMotionListener, ComponentListener {

    private int initialX;
    private int initialY;

    @Override
    public void mousePressed(MouseEvent e) {
        
        for (int i = 0; i < GameData.getInstance().getNumBlocks().size(); i++) {
            NumBlock block = GameData.getInstance().getNumBlocks().get(i);
            if (block.contains(e.getX(), e.getY())) {
                GameData.getInstance().setSelectedBlock(block);
                break;
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (GameData.getInstance().getSelectedBlock() != null) {
            GameData.getInstance().getSelectedBlock().setBlockX(e.getX() - GameData.getInstance().getMouseXOffset());
            GameData.getInstance().getSelectedBlock().setBlockY(e.getY() - GameData.getInstance().getMouseYOffset());
            GameData.getInstance().repaint();
        }
    }

    @Override public void componentHidden(ComponentEvent arg0) {}
    @Override public void componentMoved(ComponentEvent arg0) {}
    @Override public void componentResized(ComponentEvent arg0) {}
    @Override public void componentShown(ComponentEvent arg0) {}
    @Override public void mouseMoved(MouseEvent arg0) {}
    @Override public void mouseClicked(MouseEvent arg0) {}
    @Override public void mouseEntered(MouseEvent arg0) {}
    @Override public void mouseExited(MouseEvent arg0) {}
  
}