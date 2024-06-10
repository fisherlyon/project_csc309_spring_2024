package project_csc309_spring_2024;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Level implements DuelListener {

    private ProblemGenerator problemGenerator;
    private int target;
    private int difficulty;
    private Duel duel;
    private ArrayList<Character> lockedOperations;
    private ArrayList<String> defeatedComputerPlayers;
    public Level(Duel duel, int difficulty){
        duel.addDuelListener(this);
        this.difficulty = difficulty;
        this.duel = duel;
        problemGenerator = new ProblemGenerator(difficulty);
        lockedOperations = new ArrayList<Character>(Arrays.asList('-', '*','/'));
        defeatedComputerPlayers = new ArrayList<>(); 
        nextProblem();

    }

    public void unlockOperation(Character op){
        lockedOperations.remove(op);
    }

    public void unlockAllOperations(){
        lockedOperations.clear();
    }

    public boolean isLockedOperation(Character op){
        return lockedOperations.contains(op);
    } 

    public void nextProblem(){
        target = problemGenerator.generateProblem();
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    
    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Duel getDuel() {
        return duel;
    }

    @Override
    public void onPlayerAttack(Player attacker, Player attacked) {
        return;
    }

    @Override
    public void onDuelEnd(Player winner, Player loser) {
        if(winner instanceof UserPlayer && ((UserPlayer)winner).isLocalPlayer()){
            UserPlayer localPlayer = (UserPlayer)winner;
            CpuPlayer computerCharacter = GameData.getInstance().getCpuPlayer();
            String characterName = computerCharacter.getCharacterImages().getCharacterName();

            if(!defeatedComputerPlayers.contains(characterName)){
                Boolean leveledUp = localPlayer.gainExp(100);
                if(leveledUp && lockedOperations.size() > 0){
                    Character peek = lockedOperations.get(0);
                    problemGenerator.setDifficulty(difficulty += 20);
                    unlockOperation(peek);
                }
                defeatedComputerPlayers.add(characterName);
            }
        }

    }
}
