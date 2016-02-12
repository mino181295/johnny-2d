package it.unibo.oop.model;


public class Bullet extends MovableEntity implements Shot {

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
