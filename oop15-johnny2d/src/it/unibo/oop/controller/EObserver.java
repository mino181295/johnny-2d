package it.unibo.oop.controller;

/**
 * 
 * @author Paolo
 *
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
    public void update(Object source, Object... args);
}
