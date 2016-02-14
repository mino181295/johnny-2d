package it.unibo.oop.model;

import java.awt.Dimension;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public class BasicMonster extends MovableEntity implements Enemy{

	protected static final int BasicMonster_HEIGHT = 32;
	protected static final int BasicMonster_WIDTH = 32;	
	
	public static final Dimension BasicMonster_DIMENSION = new Dimension(BasicMonster_WIDTH,BasicMonster_HEIGHT);

	public BasicMonster(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		this.move();
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
