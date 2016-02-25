package it.unibo.oop.model;

import java.util.List;
import java.util.Optional;

/**
 * Interface for game state.
 */
public interface GameState {
	/**
	 * Gets the entities that can't be moved
	 */
	public List<AbstractEntity> getStableList();
	/**
	 * Gets the entities that can be moved
	 */
    public List<MovableEntity> getMovableList();
    /**
     * Gets the {@link MainCharacter} as an {@link Optional}
     */
    public Optional<MainCharacter> getMainChar();
    /**
     * Gets the {@link Arena} where the game is played
     */
    public Arena getArena();

    /**
     * @return true if game is finished.
     */
    public boolean isGameEnded();
    
    /**
     * Determines if the player has made a new record.
     */
    public void checkTopScore();
}
