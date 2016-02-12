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
		this.creatureDirection = newDirection;
		this.isShooting = isShooting;
	}
	
	public void moveHero(Direction newDirection , boolean isShooting){
		this.setInput(newDirection, isShooting);
		this.move();
	}
	
	@Override
	public void move() {
	}
	
	

	@Override
	public void checkCollision() {		
	}

	@Override
	protected int getEntityHeight() {
		return 0;
	}

	@Override
	protected int getEntityWidth() {
		return 0;
	}

	@Override
	public void shoot() {
		//gameEnvironment.addShoot(envPosition, creatureDirection);		
	}

}
