package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
/**
 * Interface for a common AI movement calculated with a specific algorithm
 * @author Matteo Minardi
 *
 */
public interface MovementBehavior {

	Vector2 getNextMove(Position targetPosition);
	
}
