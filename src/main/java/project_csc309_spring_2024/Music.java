package project_csc309_spring_2024;

public class Music {
    private MusicManager musicManager;

    public Music() {
        musicManager = new MusicManager();
        loadMusicTracks();
    }

    private void loadMusicTracks() {
        musicManager.loadMusic("background", "/music/background_music.wav");
        musicManager.loadMusic("brazil", "/music/brazil_music.wav");
        musicManager.loadMusic("christmas", "/music/christmas_music.wav");
        musicManager.loadMusic("classroom", "/music/classroom_music.wav");
        musicManager.loadMusic("moon", "/music/moon_music.wav");
        musicManager.loadMusic("time_attack", "/music/time_attack_music.wav");
    }

    public MusicManager getMusicManager() {
        return musicManager;
    }
}
