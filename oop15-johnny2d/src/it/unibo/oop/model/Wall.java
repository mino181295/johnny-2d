package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.*;

public class Wall extends AbstractEntity{
	
	public Wall(double startingX, double startinY) {
		super(startingX, startinY);
	}
	
	protected int getEntityHeight() {
		return WALL.getHeight();
	}

	protected int getEntityWidth() {		
		return WALL.getWidth();
	}

}
