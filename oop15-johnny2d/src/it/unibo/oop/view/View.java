package it.unibo.oop.view;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.view.keyboard.ActionKey;
import it.unibo.oop.view.keyboard.ActionKeysManager;
import it.unibo.oop.view.keyboard.KeysManager;
import it.unibo.oop.view.keyboard.MovementKeysManager;

/**
 * Interface for a view.
 */
public interface View {

    /**
     * @return the {@link LevelInterface}
     */
    LevelInterface getLevelView();

    /**
     * @return the {@link MovementKeysManager}
     */
    KeysManager<?, Direction> getMovementKeysManager();

    /**
     * @return the {@link ActionKeysManager}
     */
    KeysManager<?, ActionKey> getACtionKeysManager();

    /**
     * @param state
     *            object used to choose which view should be shown.
     */
    void showView(AppState state);

    /**
     * Hides the view or some parts of it.
     */
    void hideView();

    /**
     * Shows the previous view.
     */
    void showLast();
    
    /**
     * Resets views stack.
     */
    void reset();
}