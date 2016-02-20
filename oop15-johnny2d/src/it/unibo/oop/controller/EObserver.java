package it.unibo.oop.controller;

/**
 * Interface for events observers.
 */
public interface EObserver {

    /**
     * 
     * @param source
     *              object which generates the event.
     * @param args
     *              arguments for the observer.
     */
    void update(Object source, Object... args);
}
