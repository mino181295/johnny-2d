package it.unibo.oop.controller;

/**
 * Interface for some Keyboard manager.
 * 
 * @param <I>
 *            input type.
 * @param <O>
 *            output type.
 */
public interface KeysManager<I, O> extends Manager, KeyboardObserver {

    /**
     * @param cmd
     *          
     * @return
     */
    boolean isAKeyPressed(I cmd);
    
    /**
     * @return
     *          some value associated with the keys pressed setup.
     */
    O processKeys();
}
