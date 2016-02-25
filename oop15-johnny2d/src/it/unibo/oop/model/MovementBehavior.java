package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

/**
 * Interface for a common AI movement calculated with a specific algorithm.
 */
public interface MovementBehavior {
	/**
	 * 
	 * @param targetPosition The position where the Movemnt Behavior should guide you
	 * @return A {@link Vector2} that rappresents the direction to follow
	 */
	public Vector2 getNextMove(Position targetPosition);
	
}
