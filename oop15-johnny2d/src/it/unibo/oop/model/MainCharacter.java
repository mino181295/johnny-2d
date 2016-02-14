package it.unibo.oop.model;

import java.awt.Dimension;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Settings;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public class MainCharacter extends MovableEntity implements Shooter{

	private static final int MainCharacter_HEIGHT = 32;
	private static final int MainCharacter_WIDTH = 32;	
	
	public static final Dimension MainCharacter_DIMENSION = new Dimension(MainCharacter_WIDTH,MainCharacter_HEIGHT);
	
	Health currentHealth;
	Score currentScore;
	boolean isShooting;
	
	public MainCharacter(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
		// TODO Auto-generated constructor stub
	}
	
	public MainCharacter(Vector2 movementVector, Velocity speedValue) {
		this(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT/2, movementVector, speedValue);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Direction newDirection , boolean isShooting){
		this.setInput(newDirection.getVector2());
		this.isShooting = isShooting;
		this.move();
	}

	public boolean checkCollision(Position newPosition) {	
		return true;
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
