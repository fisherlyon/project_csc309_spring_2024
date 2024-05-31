package project_csc309_spring_2024;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    private Map<String, Clip> clips = new HashMap<>();
    private Clip currentClip;

    public void loadMusic(String identifier, String filePath) {
        try {
            URL url = this.getClass().getResource(filePath);
            if (url == null) {
                throw new IOException("Resource not found: " + filePath);
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clips.put(identifier, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading music: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void playMusic(String identifier) {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
        }
        currentClip = clips.get(identifier);
        if (currentClip != null) {
            currentClip.start();
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopMusic(String identifier) {
        Clip clip = clips.get(identifier);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void stopAllMusic() {
        for (Clip clip : clips.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

    public void closeMusic(String identifier) {
        Clip clip = clips.get(identifier);
        if (clip != null) {
            clip.close();
        }
    }

    public void closeAllMusic() {
        for (Clip clip : clips.values()) {
            clip.close();
        }
    }
}
