package math_tutor_project;

import java.awt.event.*;

/**
 * ...
 * 
 * @author Fisher
 */
public class GameController implements MouseListener, MouseMotionListener {

    @Override
    public void mousePressed(MouseEvent e) {
        
        for (int i = 0; i < GameData.getInstance().getLockedBlocks().size(); i++) {
            Block block = GameData.getInstance().getLockedBlocks().get(i);
            if (block.contains(e.getX(), e.getY())) {
                Block unlockedBlock = new Block(block.getBlockX(), block.getBlockY(), block.getDim(), block.getValue());
                unlockedBlock.setSelected(true);
                GameData.getInstance().getUnlockedBlocks().add(unlockedBlock);
                GameData.getInstance().setSelectedBlock(unlockedBlock);
                break;
            }
        }

        for (int i = 0; i < GameData.getInstance().getUnlockedBlocks().size(); i++) {
            Block block = GameData.getInstance().getUnlockedBlocks().get(i);
            if (block.contains(e.getX(), e.getY())) {
                GameData.getInstance().setSelectedBlock(block);
                block.setSelected(true);
                break;
            }
        }
    }

    // MERGE!
    @Override
    public void mouseReleased(MouseEvent e) {
        
        if (GameData.getInstance().getSelectedBlock() != null) {
            Block selectedBlock = GameData.getInstance().getSelectedBlock();
            boolean merged = false;
            
            for (Block block : GameData.getInstance().getUnlockedBlocks()) {
                if (!block.isSelected() && block.contains(e.getX(), e.getY())) {
                    OpButton pressedButton = GameData.getInstance().getPressedButton();
                    if (pressedButton != null) {
                        int mergeVal;
                        try {
                            mergeVal = pressedButton.doOp(block.getValue(), selectedBlock.getValue());
                        } catch (ArithmeticException er) {
                            System.out.println("div by zero");
                            break;
                        }
                        Block mergedBlock = new Block(block.getBlockX() + GameData.getInstance().getMouseXOffset(), block.getBlockY() + GameData.getInstance().getMouseYOffset(), block.getDim(), mergeVal);
                        GameData.getInstance().getUnlockedBlocks().add(mergedBlock);
                        GameData.getInstance().getUnlockedBlocks().remove(selectedBlock);
                        GameData.getInstance().getUnlockedBlocks().remove(block);
                        merged = true;
                    }
                    
                    break;
                }
            }
            
            if (!merged) {
                selectedBlock.setBlockX(e.getX() - GameData.getInstance().getMouseXOffset());
                selectedBlock.setBlockY(e.getY() - GameData.getInstance().getMouseYOffset());
            }

            TrashBin trashBin = GameData.getInstance().getTrashBin();
            if(trashBin.contains(e.getX(), e.getY())){
                // Data change runs repaint
                GameData.getInstance().removeUnlockedBlock(selectedBlock);
            }

            AnswerBox answerBox = GameData.getInstance().getAnswerBox();
            if (answerBox.contains(e.getX(), e.getY())) {
                selectedBlock.setBlockX(answerBox.getX() + GameData.getInstance().getAnswerBoxXOffset());
                selectedBlock.setBlockY(answerBox.getY() + GameData.getInstance().getAnswerBoxYOffset());
                selectedBlock.setAnswer(true);
                answerBox.setAnswerBlock(selectedBlock);
                answerBox.setFilled(true);

                int targetAnswer = GameData.getInstance().getTargetAnswer();
                Tutor tutor = GameData.getInstance().getTutor();
                if (selectedBlock.getValue() != targetAnswer){
                    tutor.help();
                }
                else {
                    ProblemGenerator problemGenerator = GameData.getInstance().getProblemGenerator();
                    int[] newProblem = problemGenerator.generateProblemByOperator('+');
                    GameData.getInstance().setProblem(
                        newProblem
                    );
                    GameData.getInstance().setTargetAnswer(
                        problemGenerator.solve(newProblem, '+')
                    );
                    tutor.setVisible(false);
                }
            }

            if (!answerBox.contains(selectedBlock.getBlockX(), selectedBlock.getBlockY())) {
                selectedBlock.setAnswer(false);
                answerBox.setAnswerBlock(null);
                answerBox.setFilled(false);
            }
            
            GameData.getInstance().getSelectedBlock().setSelected(false);
            GameData.getInstance().setSelectedBlock(null);
            GameData.getInstance().repaint();
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (GameData.getInstance().getSelectedBlock() != null) {
            GameData.getInstance().getSelectedBlock().setBlockX(e.getX() - GameData.getInstance().getMouseXOffset());
            GameData.getInstance().getSelectedBlock().setBlockY(e.getY() - GameData.getInstance().getMouseYOffset());
            GameData.getInstance().repaint();
        }
    }

    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
  
}