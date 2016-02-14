package it.unibo.oop.model;

import java.awt.Dimension;
import java.util.Random;

import it.unibo.oop.utilities.Position;

public class Bullet extends MovableEntity implements Shot {
	
	private static final int BULLET_HEIGHT = 32;
	private static final int BULLET_WIDTH = 32;	
	
	public static final Dimension BULLET_DIMENSION = new Dimension(BULLET_WIDTH,BULLET_HEIGHT);
	
	private int distancePercurred;
	private final int maxBulletDistance = 10 + new Random().nextInt(10);
	
	@Override
	public void move() {
	}

	@Override
	public boolean checkCollision(Position newPosition) {
		return true;		
	}

	protected int getEntityHeight() {
		return BULLET_HEIGHT;
	}

	@Override
	protected int getEntityWidth() {
		return BULLET_WIDTH;
	}

	@Override
	public int getDistancePercurred() {
		return this.distancePercurred;
	}
	
	

}
