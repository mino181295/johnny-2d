package it.unibo.oop.controller;

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
}