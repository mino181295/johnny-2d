package it.unibo.oop.controller;

/**
 * 
 * @author Paolo
 *  
 *  Interface for objects used in response to a keyboard event.
 */
public interface KeyboardObserver {
    
    /**
     * Method designed for KEY_PRESSEDD events, used to process the key-Code passed.
     * 
     * @param keyCode
     *          VK_Code associated with the key pressed. 
     */
    public void processKey(int keyCode);
}
