package it.unibo.oop.model;

import java.awt.Dimension;
import java.awt.Rectangle;

public class BasicMonster extends MovableEntity implements Enemy{
	
	private static final int BasicMonster_HEIGHT = 32;
	private static final int BasicMonster_WIDTH = 32;	
	
	public static final Dimension BasicMonster_DIMENSION = new Dimension(BasicMonster_WIDTH,BasicMonster_HEIGHT);

	public void move() {
	}

	public void checkCollision() {
	}

	public Rectangle getBounds() {
		return null;
	}

	protected int getEntityHeight() {
		return 0;
	}

	protected int getEntityWidth() {
		return 0;
	}

}
