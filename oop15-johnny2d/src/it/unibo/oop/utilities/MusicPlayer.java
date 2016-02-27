package it.unibo.oop.utilities;

/**
 * An interface for a music player.
 */
public interface MusicPlayer {

    /**
     * Sets the music on or off.
     * @param on
     *          true to set the music on, false to set it off
     */
    void setMusic(final boolean musicOn);
    
    /**
     * Plays the audio file.
     * @param file 
     *          the audio file to reproduce
     */
    void play(final  file);
    
    /**
     * Plays the audio file in loop.
     * @param file 
     *          the audio file to reproduce
     */
    void playLoop(final  file);
    
    /**
     * Stops the audio file reproduction
     */
    void stop();
    
    /**
     * Indicates if the music is on or not.
     * @return true if the music is on, false otherwise
     */
    boolean isMusicOn();
}