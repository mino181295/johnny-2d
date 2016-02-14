package it.unibo.oop.model;

import it.unibo.oop.utilities.Direction;

/**
 * 
 * @author Paolo + Matteo
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
    public void initialize(final int level); 
    
    /**
     * 
     * @param entity
     *              entity to remove.
     *  
     */
    public void removeEntity(final Entity entity);
    
    public void updatePositions();
    
    public void updateHeroPos(final Direction d);
}
