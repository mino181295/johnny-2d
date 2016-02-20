package it.unibo.oop.model;

import java.awt.Rectangle;

import it.unibo.oop.utilities.Position;

/**
 * Interface for every object in the environment with a {@link Position}.
 */
public interface Entity {
	
	/**
	 * Function that returns the position of every object placed in the map.
	 * @return the position of this entity
	 */
	Position getPosition();
	
	/**
	 * Checks if the position is the same of the passed one.
	 * @param newPosition
	 * @return true if the position is the same.
	 */
	boolean equalsPosition(Position newPosition);
	
	/**
	 * It return and calculates the bounds of a specific {@link Entity}.
	 * @return the {@link Rectangle} containing the object
	 */
	Rectangle getBounds();
	
	/**
	 * 
	 * @return true if the entity intersecates the secondEntity
	 */
	boolean intersecate(Entity secondEntity);
}
