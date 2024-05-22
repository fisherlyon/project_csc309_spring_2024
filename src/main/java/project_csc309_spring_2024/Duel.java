package project_csc309_spring_2024;

import java.util.ArrayList;

public class Duel {

    private Player player1;
    private Player player2;
    public static int DEFAULT_NETWORK_DUEL_DIFFICULTY = 50;
    ArrayList<DuelListener> listeners;

    public Duel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.listeners = new ArrayList<>();
    }

    public void castAttack(Player attacker, Player playerToAttack) {
        playerToAttack.setHealth(playerToAttack.getHealth() - 10);
        notifyAllLisenters(attacker, playerToAttack);
    }

    public void notifyAllLisenters(Player attacker, Player playerToAttack) {
        for (DuelListener listener : listeners) {
            listener.onPlayerAttack(attacker, playerToAttack);
            if (playerToAttack.getHealth() <= 0) {

                listener.onDuelEnd(attacker, playerToAttack);
            }

        }
    }

    public void addDuelListener(DuelListener duelListener) {
        listeners.add(duelListener);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<DuelListener> getListeners() {
        return listeners;
    }
    
}