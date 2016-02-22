package it.unibo.oop.model;

import java.util.List;
import java.util.Optional;

/**
 * Interface for game state.
 */
public interface GameState {

    /**
     * For game initializing: creates the game state for next play.
     * @param level
     *          value which identifies the next level
     */
    
    
    /**
     * @param entity
     *              entity to remove.
     */
    
    
    public List<AbstractEntity> getStableList();
    
    public List<MovableEntity> getMovableList(); 
    
    public Optional<MainCharacter> getMainChar();
    
    public Arena getArena();
    
    /**
     * @return
     *          true if game is finished.
     */
    boolean isGameEnded();
}
