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
     * @return best {@link Score}.
     */
    public Score getStatFromFile();

    /**
     * to put best {@link Score} to file.
     */
    public void putStatToFile(Score topScores);

    /**
     * @return true if player has made a new record.
     */
    public boolean isRecord();
}