package it.unibo.oop.controller;

/**
 * Interface for objects used in response to a State event.
 */
public interface StateObserver {

    /**
     * 
     * Action to do according to the {@link AppState} passed.
     * @param state
     *              state of the game/app.
     */
    void stateAction(AppState state);
}
