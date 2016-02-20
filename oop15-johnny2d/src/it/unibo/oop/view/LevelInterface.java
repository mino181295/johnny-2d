package it.unibo.oop.view;

import it.unibo.oop.controller.KeyboardObserver;

/**
 * Interface for Level, should be factored.
 */
public interface LevelInterface extends Showable {

    /**
     * repaints the panel.
     */
    void updateLevel();

    /**
     * @param obs
     *            observer to attach.
     */
    void addObserver(final KeyboardObserver obs);

    /**
     * @param val
     *            to enable(true)/disable(false) music.
     */
    void enableMusic(final boolean val);
}
