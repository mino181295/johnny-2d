package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.*;

public class Wall extends AbstractEntity{
	
	public Wall(int startingX, int startinY) {
		super(startingX, startinY);
	}
	
	protected int getEntityHeight() {
		return WALL.getHeight();
	}

	protected int getEntityWidth() {		
		return WALL.getWidth();
	}

}
