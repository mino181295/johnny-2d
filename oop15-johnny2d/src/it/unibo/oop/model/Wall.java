package it.unibo.oop.model;

import java.awt.Dimension;

public class Wall extends AbstractEntity{
	
	private static final int WALL_HEIGHT = 32;
	private static final int WALL_WIDTH = 32;	
	
	public static final Dimension WALL_DIMENSION = new Dimension(WALL_WIDTH,WALL_HEIGHT);

	protected int getEntityHeight() {
		return 0;
	}

	protected int getEntityWidth() {
		
		return 0;
	}

}
