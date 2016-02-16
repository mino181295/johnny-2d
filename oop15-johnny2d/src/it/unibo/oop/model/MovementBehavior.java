package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public interface MovementBehavior {

	public Vector2 getNextMove(Position targetPosition);
	
}
