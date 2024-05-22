package project_csc309_spring_2024;

public class SoundLoader {

    public static void loadSounds(AudioPlayer audioPlayer) {
        audioPlayer.addSound("select", "/sounds/mouse_click.wav");
        audioPlayer.addSound("drop", "/sounds/minecraft_pop.wav");
    }
}
