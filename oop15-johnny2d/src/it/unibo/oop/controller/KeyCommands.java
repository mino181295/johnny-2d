package it.unibo.oop.controller;

import java.awt.event.KeyEvent;

import it.unibo.oop.utilities.Direction;

/**
 * Enum defining which keys can be pressed during the gameplay. NOTE: the
 * keyboard layout can be customize simply modifying the VK_Code of the keys.
 */
public enum KeyCommands {
    /**
     * Up movement of main character.
     */
    UP(KeyEvent.VK_W, Direction.UP),

    /**
     * Left movement of main character.
     */
    LEFT(KeyEvent.VK_A, Direction.LEFT),

    /**
     * Down movement of main character.
     */
    DOWN(KeyEvent.VK_S, Direction.DOWN),

    /**
     * Right movement of main character.
     */
    RIGHT(KeyEvent.VK_D, Direction.RIGHT),

    /**
     * Right-Up movement of main character.
     */
    RIGHTUP(KeyEvent.VK_UNDEFINED, Direction.RIGHTUP),

    /**
     * Left-Up movement of main character.
     */
    LEFTUP(KeyEvent.VK_UNDEFINED, Direction.LEFTUP),

    /**
     * Right-Down movement of main character.
     */
    RIGHTDOWN(KeyEvent.VK_UNDEFINED, Direction.RIGHTDOWN),

    /**
     * Left-Down movement of main character.
     */
    LEFTDOWN(KeyEvent.VK_UNDEFINED, Direction.LEFTDOWN),

    /**
     * Shooting action of main character.
     */
    SPACE(KeyEvent.VK_SPACE, Direction.NONE),

    /**
     * To pause the game.
     */
    ESC(KeyEvent.VK_ESCAPE, Direction.NONE),

    /**
     * When no moves have been performed.
     */
    NONE(KeyEvent.VK_UNDEFINED, Direction.NONE);

    private final int vkCode;
    private final Direction dir;

    KeyCommands(final int vkCode, final Direction dir) {
        this.vkCode = vkCode;
        this.dir = dir;
    }

    int getVkCode() {
        return this.vkCode;
    }

    Direction getDir() {
        return this.dir;
    }

    boolean isMovement() {
        return this.dir != Direction.NONE;
    }
}