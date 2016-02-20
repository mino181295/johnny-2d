package it.unibo.oop.view;

import it.unibo.oop.controller.KeyboardObserver;

/**
 * Interface for {@link Level}.
 */
public interface LevelInterface extends Showable {

    /**
     * @param obs
     *            observer to attach.
     */
    void addObserver(final KeyboardObserver obs);

    /**
     * Repaints the {@link LevelPanel}.
     */
    void updateLevel();
}
