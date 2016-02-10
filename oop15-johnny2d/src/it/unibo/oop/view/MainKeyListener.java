package it.unibo.oop.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.oop.controller.GameLoop;
import it.unibo.oop.controller.KeyboardObserver;

/**
 * 
 * @author Paolo
 * 
 * simple custom class implementing {@link KeyListener} should be attached to the main view (where to play the game).
 *
 */
public class MainKeyListener implements KeyListener {

    private final KeyboardObserver gLObserver;
    
    /**
     * 
     * @param gL
     *          a {@link GameLoop} instance
     * 
     */
    public MainKeyListener(final KeyboardObserver gL) {
        this.gLObserver = gL;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        new Thread(() -> {
            this.gLObserver.processKey(e.getKeyCode());
        }).start();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
