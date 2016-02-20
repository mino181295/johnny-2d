package it.unibo.oop.controller;

/**
 * Interface for events observers.
 */
public interface EObserver {

    /**
     * @param args
     *              arguments for the observer.
     * throw new IllegalArgumentException
     *                  if args passed doesn't fit the method implementation.
     */
    void update(Object... args);
}
