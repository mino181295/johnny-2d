package it.unibo.oop.model;

import java.awt.Rectangle;

public class BasicMonster extends MovableEntity implements Enemy{

	@Override
	public void move() {
	}

	@Override
	public void checkCollision() {
	}

	@Override
	public Rectangle getBounds() {
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
