package it.unibo.oop.controller;

/**
 * Interface for object which manage something.
 * @param <T>
 *              Type of objects managed.
 */
public interface Manager<T> extends EObserver {

    /**
     * @return
     *          something managed.
     */
    T getStuff();

    /**
     * To reset manager state.
     */
    void reset();
}
