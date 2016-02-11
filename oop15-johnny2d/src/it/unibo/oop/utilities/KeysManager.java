package it.unibo.oop.utilities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static it.unibo.oop.controller.KeyCommands.*;
import it.unibo.oop.controller.KeyCommands;
import it.unibo.oop.controller.KeyboardObserver;

/**
 * 
 * @author Paolo
 *
 * class used by the controller to process the key list.
 */
public class KeysManager<T> implements KeyboardObserver {

    private final List<Integer> keysPressed; /* svuotata da KeyRelease event */
    private List<Character> keysTyped; /* svuotata ad ogni frame */
    
    public KeysManager() {
        this.keysPressed = new ArrayList<>();
        this.keysTyped = new ArrayList<>(); /*lista perché più tasti alla volta potrebbero essere typed p.e. M tasti direzione e 1 spara */
    }
    
    /* NB: massimo vengono considerati 2 tasti di direzione priorità a quelli pressed */
    public synchronized Direction getDirection() {
        
        /* scorro la lista keysPressed e cerco i primi due tasti non opposti; gli eventuali "posti liberi" vengono riemipi
         * da max 2 tasti di direzione presi dalla keysTyped
         *
         *
         *prendo i primi 2 di direzione, se sono opposti prendo il primo di direzione
         *
         */
        final List<Integer> list = new ArrayList<>(2);  
        int i = 0;
         
        for (Integer key: this.keysPressed) {
            if(list.isEmpty() || list.get(0) != key || list.get(0) != list.get(0).) {
                
            }
        }
        this.keysTyped = new ArrayList<>();
    }

    private static Optional<KeyCommands> getCrossKey() {
        KeyCommands tmp;
        switch (key) {
        case W:
            tmp = S;
            break;
        case S:
            tmp = W;
            break;
        case A:
            tmp = D;
            break;
        case D:
            tmp = A;
            break;
        default:
            tmp = null;
        } 
        return Optional.ofNullable(tmp);
    }
    
    public synchronized boolean isAKeyPressed(final int VK_Code) {
        return this.keysPressed.contains(VK_Code) || this.keysTyped.contains(VK_Code);
    }
    
    @Override
    public synchronized void keyPressed(final int keyCode) {
        if (!this.keysPressed.contains(keyCode)) {
            this.keysPressed.add(keyCode);
        }
    }

    @Override
    public synchronized void keyReleased(final int keyCode) {
        if (this.keysPressed.contains(keyCode)) {
            this.keysPressed.remove(keyCode);
        }
    }
    
    @Override
    public synchronized void keyTyped(final char keyChar) {
        if (this.keysTyped.contains(keyChar)) {
            this.keysTyped.remove(keyChar);
        }
    }
    
}
