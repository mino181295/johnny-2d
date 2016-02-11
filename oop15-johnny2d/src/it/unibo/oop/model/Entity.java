package it.unibo.oop.model;

import java.awt.Rectangle;

import it.unibo.oop.utilities.Position;

/**
 * The interface of every object in the environment with a position 
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
	 * 
	 * @param newPosition
	 * @return True if the position is the same
	 */
	boolean isHere(Position newPosition);
	
	/**
	 * 
	 * @return The 
	 */
	Rectangle getBounds();

}
