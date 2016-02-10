package it.unibo.oop.controller;

/**
 * 
 * @author Paolo
 *  
 *  Interface for objects used in response to a keyboard event.
 */
public interface KeyboardObserver {

    /**
     * Method designed for KEY_TYPE events, used to process the key-value passed.
     * 
     * @param c
     *          character associated with the key to process. 
     */
    
    public void processKey(char c);
}
