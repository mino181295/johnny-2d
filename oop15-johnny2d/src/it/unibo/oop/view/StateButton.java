package it.unibo.oop.view;

import it.unibo.oop.controller.State;

public interface StateButton {

    /**
     * @return
     *          name of button.
     */
    String getName();
    
    /**
     * @return
     *          {@link State} of button.
     */
    State getState();
}
