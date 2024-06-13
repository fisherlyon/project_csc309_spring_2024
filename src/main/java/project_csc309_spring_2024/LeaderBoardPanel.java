package project_csc309_spring_2024;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Displays the current leaderboard in
 * a JPanel. Showing the top 10 scores.
 * 
 * @author Fisher Lyon
 */
public class LeaderBoardPanel extends JPanel {

    private LeaderBoard lb;
    
    public LeaderBoardPanel() {
        setBackground(new Color(8, 0, 81));
        lb = new LeaderBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<LeaderBoardEntry> lbes = GameData.getInstance().getlbes();
        g.setFont(GameData.getInstance().getCustomFont().deriveFont(25f));
        g.setColor(Color.WHITE);
        g.drawString("Leaderboard", 180, 55);
        g.setFont(GameData.getInstance().getCustomFont().deriveFont(15f));
        // g.drawString doesn't account for \n and \t, so I had to format manually
        String[] formattedlbes = lb.formatlbes(lbes).split("\n");
        int len = Math.min(formattedlbes.length, 11);
        int x = 100;
        int y = 80;
        int lineHeight = 20;
        int[] tabStops = {25, 150, 275};

        for (int i = 0; i < len; i++) {
            String[] parts = formattedlbes[i].split("\t");
            for (int j = 0; j < parts.length; j++) {
                g.drawString(parts[j], x + tabStops[j], y + (i * lineHeight));
            }
        }
    }
}
