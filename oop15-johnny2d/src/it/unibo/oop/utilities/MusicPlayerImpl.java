package it.unibo.oop.utilities;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * A singleton class that represents a music player used to play the game's
 * music. The music is taken from OpenGameArt.
 * 
 * @see <a href="http://opengameart.org/">OpenGameArt</a>
 */
public final class MusicPlayerImpl implements MusicPlayer {

    private static final MusicPlayerImpl SINGLETON = new MusicPlayerImpl();
    public static final String LEVEL_BACKGROUND = "LevelBackground";
    public static final String GAME_OVER = "GameOver";

    private final Map<String, Clip> musicLibrary;
    private boolean musicOn;

    private MusicPlayerImpl() {
        this.musicLibrary = new HashMap<>();
        this.musicLibrary.put(LEVEL_BACKGROUND, this.loadMusic("/audio/LevelBackground.mp3"));
        this.musicLibrary.put(GAME_OVER, this.loadMusic("/audio/GameOver.wav"));
    }

    private Clip loadMusic(final String path) {
        AudioInputStream ais;
        Clip clip = null;
        final URL url = this.getClass().getResource(path);
        try {
            ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
            ais.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }

    private Clip getMusic(final String musicName) {
        return this.musicLibrary.get(musicName);
    }

    /**
     * Getter for the singleton pattern.
     * 
     * @return the singleton instance of the {@link MusicPlayerImpl}
     */
    public static MusicPlayer getInstance() {
        return SINGLETON;
    }

    @Override
    public void setMusic(final boolean musicOn) {
        this.musicOn = musicOn;
    }

    @Override
    public boolean isMusicOn() {
        return this.musicOn;
    }

    @Override
    public void play(final String musicName) {
        if (this.musicOn) {
            this.getMusic(musicName).setFramePosition(0);
            this.getMusic(musicName).start();
        }
    }

    @Override
    public void playLoop(final String musicName) {
        if (this.musicOn) {
            this.getMusic(musicName).loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void stop(final String musicName) {
        this.getMusic(musicName).stop();
    }

    @Override
    public void stopAll() {
        this.musicLibrary.values().forEach(e -> {
            e.stop();
        });
    }

    @Override
    public void closeMusicPlayer() {
        this.musicLibrary.values().forEach(e -> {
            e.stop();
            e.close();
        });
    }
}