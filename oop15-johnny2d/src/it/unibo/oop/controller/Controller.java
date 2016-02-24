package it.unibo.oop.controller;

import it.unibo.oop.model.Score;

/**
 * Interface for Controller entity in MVC model.
 */
public interface Controller {

    /**
     * method which "starts" the object implementing this interface for the
     * first time.
     */
    void start();

    /**
     * method which "plays" some functionalities of the object implementing this
     * interface.
     */
    void play();

    /**
     * Reset player's record-score to minimum score.
     */
    public void resetStatFile();
    
    /**
     * @return best {@link Score}.
     */
    Score getStatFromFile();

    /**
     * to put best {@link Score} to file.
     */
    void putStatToFile(Score topScores);

    /**
     * @return true if player has made a new record.
     */
    boolean isRecord();
}