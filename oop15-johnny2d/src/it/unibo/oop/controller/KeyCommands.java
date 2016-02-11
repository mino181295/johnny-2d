package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.*;
import it.unibo.oop.utilities.Direction;
import java.awt.event.KeyEvent;

public enum KeyCommands {
    W(KeyEvent.VK_W, UP),
    A(KeyEvent.VK_A, LEFT),
    S(KeyEvent.VK_S, DOWN),
    D(KeyEvent.VK_D, RIGHT),
    SPACE(KeyEvent.VK_SPACE, NONE),
    ESC(KeyEvent.VK_ESCAPE, NONE);
    
    private final int VK_Code;
    private final Direction dir;
    
    private KeyCommands(final int VK_Code, Direction dir) {
        this.VK_Code = VK_Code;
        this.dir = dir;
    }
}
