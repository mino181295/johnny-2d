package it.unibo.oop.model;

import java.awt.Rectangle;

import it.unibo.oop.utilities.Position;

/**
 * The interface of every object in the environment with a position. 
 * @author Matteo Minardi
 *
 */
public interface Entity {
	/**
	 * Function that returns the position of every object placed in the map.
	 * @return The position of this entity
	 */
	Position getPosition();
	/**
	 * Checks if the position is the same of the passed one.
	 * @param newPosition
	 * @return True if the position is the same.
	 */
	boolean equalsPosition(Position newPosition);
	
	/**
	 * It return and calculates the bounds of a specific {@link Entity}.
	 * @return The {@link Rectangle} containing the object
	 */
	Rectangle getBounds();
	/**
	 * 
	 * @return True if the entity intersecate the secondEntity
	 */
	boolean intersecate(Entity secondEntity);
}
