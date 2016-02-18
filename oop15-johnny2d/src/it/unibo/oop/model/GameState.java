package it.unibo.oop.model;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Paolo Venturi + Matteo Minardi
 *
 * Interface for game state 
 */
public interface GameState {

    /**
     * 
     * for game initializing: creates the game state for next play.
     * 
     * @param level
     *          value which identifies the next level
     * 
     */
    
    
    /**
     * 
     * @param entity
     *              entity to remove.
     *  
     */
    
    
    public List<AbstractEntity> getStableList();
    
    public List<AbstractEntity> getMovableList(); 
    
    public Optional<MainCharacter> getMainChar();
    
    
}
