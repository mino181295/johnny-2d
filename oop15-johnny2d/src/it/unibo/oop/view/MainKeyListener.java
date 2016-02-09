package it.unibo.oop.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.oop.controller.GameLoop;

/**
 * 
 * @author Paolo
 * 
 * simple custom class implementing {@link KeyListener} should be attached to the main view (where to play the game).
 *
 */
public class MainKeyListener implements KeyListener {

    private final GameLoop gL;
    
    /**
     * 
     * @param gL
     *          a {@link GameLoop} instance
     * 
     */
    public MainKeyListener(final GameLoop gL) {
        this.gL = gL;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
      //  new Thread(() -> {
            this.gL.keySwitcher(e.getKeyChar());
      //  });   
    }
}
