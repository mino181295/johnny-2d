package it.unibo.oop.view;

import it.unibo.oop.controller.State;

public interface StateButton {

    /**
     * @return
     *          name of button.
     */
    public String getName();
    
    /**
     * @return
     *          {@link State} of button.
     */
    public State getState();
}
