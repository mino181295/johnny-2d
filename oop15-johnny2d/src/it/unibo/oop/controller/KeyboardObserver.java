package it.unibo.oop.controller;

/**
 * 
 * @author Paolo
 *  
 *  Interface for objects used in response to a keyboard event.
 */
public interface KeyboardObserver {    
    /**
     * @param KeyCode
     *              VK_Code of the key.
     * @param eventID
     *              id that identifies if key has been Pressed/Released or Typed.
     */
    public void keyAction(int KeyCode, int eventID);
}
