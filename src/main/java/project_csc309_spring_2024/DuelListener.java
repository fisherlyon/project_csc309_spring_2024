package project_csc309_spring_2024;

public interface DuelListener {
    public void onPlayerAttack(Player attacker, Player attacked);
    public void onDuelEnd(Player winner, Player loser);
}
