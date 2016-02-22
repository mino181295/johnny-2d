package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.*;
/**
 * Class that represents a block of Wall that is used to delimit the arena 
 */
public class Wall extends AbstractEntity{
	
	public Wall(final double startingX, final double startingY) {
		super(startingX, startingY);
	}
	/**
	 * 
	 */
	protected int getEntityHeight() {
		return WALL.getHeight();
	}
	/**
	 * 
	 */
	protected int getEntityWidth() {		
		return WALL.getWidth();
	}

}
