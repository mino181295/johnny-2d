package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.MAIN_CHARACTER;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Settings;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public class MainCharacter extends MovableEntity implements Shooter{

	private Health currentHealth;
	private Score currentScore;
	
	public MainCharacter(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
		currentHealth = new Health(3);
		currentScore = new Score(0);
	}
	
	public MainCharacter(Vector2 movementVector, Velocity speedValue) {
		this(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT/2, movementVector, speedValue);
	}
	
	public MainCharacter() {
		this(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT/2, new Vector2(), MAIN_CHARACTER.getSpeed());
	}
	
	public void update(Direction newDirection , boolean isShooting){
		this.setInput(newDirection.getVector2());
		this.move();
		if (isShooting){
			this.shoot();
		}
	}

	public void checkCollision(Position newPosition) {	
		
		//TODO
	}

	protected int getEntityHeight() {
		return MAIN_CHARACTER.getHeight();
	}

	protected int getEntityWidth() {
		return MAIN_CHARACTER.getWidth();
	}

	public Score getScore(){
		return this.currentScore;
	}
	
	public void shoot() {
		this.getEnvironment().addShoot(new Bullet(this));		
	}

}
