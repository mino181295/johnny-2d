package it.unibo.oop.model;

import java.awt.Dimension;

import it.unibo.oop.utilities.Direction;

public class MainCharacter extends MovableEntity implements Shooter{
	
	private static final int MainCharacter_HEIGHT = 32;
	private static final int MainCharacter_WIDTH = 32;	
	
	public static final Dimension MainCharacter_DIMENSION = new Dimension(MainCharacter_WIDTH,MainCharacter_HEIGHT);

	Health currentHealth;
	Score currentScore;
	boolean isShooting;
	
	private void setInput(Direction newDirection , boolean isShooting){
		this.movementVector = newDirection.getVector();
		this.isShooting = isShooting;
	}
	
	public void move() {
	}
	
	public void moveMainCharacter(Direction newDirection , boolean isShooting){
		this.setInput(newDirection, isShooting);
		this.move();
	}
	
	public void checkCollision() {		
	}

	protected int getEntityHeight() {
		return 0;
	}

	protected int getEntityWidth() {
		return 0;
	}

	public void shoot() {
		//gameEnvironment.addShoot(this.envPosition, this.creatureDirection);		
	}

}
