package it.unibo.oop.utilities;

/**
 * A singleton class that represents a music player used to play the game's
 * music. The music is taken from OpenGameArt.
 * 
 * @see <a href="http://opengameart.org/">OpenGameArt</a>
 */
public final class MusicPlayerImpl implements MusicPlayer {

    private static final MusicPlayerImpl SINGLETON = new MusicPlayerImpl();
    private static final String LEVEL_BACKGROUND = "/audio/LevelBackground.mp3";
    private static final String GAME_OVER = "/audio/GameOver.wav";

    private boolean musicOn;

    private MusicPlayerImpl() {
        
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
    public void play() {

    }

    @Override
    public void playLoop() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isMusicOn() {
        return this.musicOn;
    }
}
