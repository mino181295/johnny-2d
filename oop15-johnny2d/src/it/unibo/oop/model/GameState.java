package it.unibo.oop.model;

import java.util.List;

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
    
    public void addShoot(Bullet newBullet);
    
    public void addMovableEntity(MovableEntity newEntity);
    
    public void addStableEntity(AbstractEntity newEntity);
    
    public List<AbstractEntity> getStableList();
    
    public List<AbstractEntity> getMovableList(); 
    
    public MainCharacter getMainChar();
    
    public void updatePositions();
    
}
