package it.unibo.oop.view;

import it.unibo.oop.controller.KeyboardObserver;

/**
 * Interface for {@link Level}.
 */
public interface LevelInterface extends Showable, ESource<KeyboardObserver> {

    /**
     * Repaints the {@link LevelPanel}.
     */
    void updateLevel();

    /**
     * @param val
     *            to enable(true)/disable(false) music.	    
     */ 
    void enableMusic(final boolean val);
}
