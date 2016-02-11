package it.unibo.oop.controller;

public interface StateObserver {

    /**
     * 
     * Action to do according to the {@link State} passed.
     * @param state
     *              state of the game.
     */
    public void stateAction(State state);
}
