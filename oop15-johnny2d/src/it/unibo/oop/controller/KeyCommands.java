package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.*;
import it.unibo.oop.utilities.Direction;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Paolo
 *
 * Enum defining which keys can be pressed during the gameplay.
 */
public enum KeyCommands {
    W(KeyEvent.VK_W, UP),
    A(KeyEvent.VK_A, LEFT),
    S(KeyEvent.VK_S, DOWN),
    D(KeyEvent.VK_D, RIGHT),
    WD(KeyEvent.VK_UNDEFINED, RIGHTUP),    
    WA(KeyEvent.VK_UNDEFINED,LEFTUP),
    SD(KeyEvent.VK_UNDEFINED,RIGHTDOWN),
    SA(KeyEvent.VK_UNDEFINED,LEFTDOWN),
    SPACE(KeyEvent.VK_SPACE, Direction.NONE),
    ESC(KeyEvent.VK_ESCAPE, Direction.NONE), /* to pause the game */
    NONE(KeyEvent.VK_UNDEFINED, Direction.NONE);
    
    private final int vkCode;
    private final Direction dir;
    
    private KeyCommands(final int vkCode, Direction dir) {
        this.vkCode = vkCode;
        this.dir = dir;
    }
    
    public Integer getVkCode() {
        return this.vkCode;
    }
    
    public Direction getDir() {
        return this.dir;
    }
    
    public boolean isMovement() {
        return this.dir != Direction.NONE;
    }
}
