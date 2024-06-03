package project_csc309_spring_2024;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class LeaderBoardPanel extends JPanel {

    private LeaderBoard lb;
    
    public LeaderBoardPanel() {
        setBackground(Color.BLACK);
        lb = new LeaderBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<LeaderBoardEntry> lbes = GameData.getInstance().getlbes();
        g.setFont(GameData.getInstance().getCustomFont().deriveFont(25f));
        g.setColor(Color.WHITE);
        g.drawString("Leaderboard", 180, 60);
        g.setFont(GameData.getInstance().getCustomFont().deriveFont(15f));
        String[] formattedlbes = lb.formatlbes(lbes).split("\n");
        int len = Math.min(formattedlbes.length, 11);
        int x = 120;
        int y = 90;
        int lineHeight = 20;
        int[] tabStops = {25, 150, 225}; // adjust these positions based on your needs

        for (int i = 0; i < len; i++) {
            String[] parts = formattedlbes[i].split("\t");
            for (int j = 0; j < parts.length; j++) {
                g.drawString(parts[j], x + tabStops[j], y + (i * lineHeight));
            }
        }
    }
}
