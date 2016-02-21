package it.unibo.oop.controller;

/**
 * Interface for some Keyboard manager.
 * 
 * @param <I>
 *            input type.
 * @param <O>
 *            output type.
 */
public interface KeysManager<I, O> extends Manager {

    /**
     * @param key
     *          adds the key passed.
     */
    void addKey(I key);
    
    /**
     * @param key
     *          removes the key passed.
     */
    void removeKey(I key);
    
    /**
     * @param key
     *          
     * @return
     */
    boolean isAKeyPressed(I key);
    
    /**
     * @return
     *          some value associated with the keys pressed setup.
     */
    O processKeys();
}