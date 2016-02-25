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
	public Position getPosition();
	
	/**
	 * It return and calculates the bounds of a specific {@link Entity}.
	 * @return the {@link Rectangle} containing the object
	 */
	public Rectangle getBounds();
	
	/**
	 * 
	 * @return true if the entity intersecates the secondEntity
	 */
	public boolean intersecate(Entity secondEntity);
}
