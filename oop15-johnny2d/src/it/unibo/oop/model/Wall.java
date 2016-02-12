package it.unibo.oop.model;

import java.awt.Dimension;
import java.awt.Rectangle;

import it.unibo.oop.utilities.Position;

public class Wall extends AbstractEntity{
	
	private static final int WALL_HEIGHT = 32;
	private static final int WALL_WIDTH = 32;	
	
	public static final Dimension WALL_DIMENSION = new Dimension(WALL_WIDTH,WALL_HEIGHT);

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	public Position getTopLeftCorner(){
		//TODO
		return null;
	}

	@Override
	protected int getEntityHeight() {
		return 0;
	}

	@Override
	protected int getEntityWidth() {
		
		return 0;
	}

}
