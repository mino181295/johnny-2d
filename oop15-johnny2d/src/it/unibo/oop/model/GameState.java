package it.unibo.oop.model;

import java.util.List;
import java.util.Optional;

/**
 * Interface for game state.
 */
public interface GameState {

    /**
     * For game initializing: creates the game state for next play.
     * 
     * @param level
     *            value which identifies the next level
     */

    /**
     * @param entity
     *            entity to remove.
     */

    List<AbstractEntity> getStableList();

    List<MovableEntity> getMovableList();

    Optional<MainCharacter> getMainChar();

    Arena getArena();

    /**
     * @return true if game is finished.
     */
    boolean isGameEnded();
    
    /**
     * Determines if the player has made a new record.
     */
    void checkTopScore();
}
