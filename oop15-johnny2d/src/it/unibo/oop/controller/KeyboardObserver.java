package it.unibo.oop.controller;

/**
 * 
 * @author Paolo
 *  
 *  Interface for objects used in response to a keyboard event.
 */
public interface KeyboardObserver {
    
    /**
     * Method designed for KEY_PRESSED events, used to process the key-Code passed.
     * 
     * @param keyCode
     *          VK_Code associated with the key pressed. 
     */
    public void keyPressed(int keyCode);
    
    /**
     * Method designed for KEY_RELEASED events, used to process the key-Code passed.
     * 
     * @param keyCode
     *          VK_Code associated with the key pressed. 
     */
    public void keyReleased(int keyCode);
   
    /**
     * Method designed for KEY_TYPED events, used to process the key-Char passed.
     * 
     * @param keyChar
     *          char associated with the key pressed. 
     */
 //   public void keyPressed(char keyChar);
    
}
