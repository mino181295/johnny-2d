package it.unibo.oop.model;

import java.awt.Dimension;

import it.unibo.oop.utilities.Position;

public class BasicMonster extends MovableEntity implements Enemy{
	
	protected static final int BasicMonster_HEIGHT = 32;
	protected static final int BasicMonster_WIDTH = 32;	
	
	public static final Dimension BasicMonster_DIMENSION = new Dimension(BasicMonster_WIDTH,BasicMonster_HEIGHT);

	public void move() {
	}

	public boolean checkCollision(Position newPosition) {
		return false;
	}

	protected int getEntityHeight() {
		return BasicMonster_HEIGHT;
	}

	protected int getEntityWidth() {
		return BasicMonster_WIDTH;
	}

	

}
