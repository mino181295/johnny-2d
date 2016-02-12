package it.unibo.oop.model;

import java.awt.Dimension;

public class Bullet extends MovableEntity implements Shot {
	
	private static final int SHOT_HEIGHT = 32;
	private static final int SHOT_WIDTH = 32;	
	
	public static final Dimension SHOT_DIMENSION = new Dimension(SHOT_WIDTH,SHOT_HEIGHT);

	private int distancePercurred;
	
	@Override
	public void move() {
	}

	@Override
	public void checkCollision() {
		
	}

	protected int getEntityHeight() {
		return 0;
	}

	@Override
	protected int getEntityWidth() {
		return 0;
	}

	@Override
	public int getDistancePercurred() {
		return this.distancePercurred;
	}
	
	

}
