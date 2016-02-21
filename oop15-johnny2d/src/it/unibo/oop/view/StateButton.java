package it.unibo.oop.view;

import it.unibo.oop.controller.AppState;

/**
 * Interface for buttons with a specified {@link AppState}.
 */
public interface StateButton {

    /**
     * @return name of button.
     */
    String getName();

    /**
     * @return {@link AppState} of button.
     */
    AppState getState();
}
