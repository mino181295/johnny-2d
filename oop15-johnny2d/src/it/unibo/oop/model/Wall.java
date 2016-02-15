package it.unibo.oop.model;

import java.awt.Dimension;

public class Wall extends AbstractEntity{
	
	protected final static int WALL_HEIGHT = 32;
	protected final static int WALL_WIDTH = 32;	
	
	public static final Dimension WALL_DIMENSION = new Dimension(WALL_WIDTH,WALL_HEIGHT);

	public Wall(int startingX, int startinY) {
		super(startingX, startinY);
	}
	
	protected int getEntityHeight() {
		return Wall.WALL_HEIGHT;
	}

	protected int getEntityWidth() {		
		return Wall.WALL_WIDTH;
	}

}
