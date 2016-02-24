package it.unibo.oop.controller;

import java.awt.event.KeyEvent;

/**
 * Interface for action keys i.e. which are not for hero movement.
 */
public enum ActionKey implements Key{

    /**
     * Shooting action of main character.
     */
    SHOOT(KeyEvent.VK_SPACE),

    /**
     * To pause the game.
     */
    PAUSE(KeyEvent.VK_ESCAPE),

    /**
     * When no moves have been performed.
     */
    NONE(KeyEvent.VK_UNDEFINED);
    
    private final int vkCode;
            
    ActionKey(final int vkCode) {
        this.vkCode = vkCode;
    }
   
    @Override
    public int getVkCode() {
        return this.vkCode;
    }
}
