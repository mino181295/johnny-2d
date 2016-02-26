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
     * Puts best {@link Score} to file.
     */
    void putStatToFile();
}