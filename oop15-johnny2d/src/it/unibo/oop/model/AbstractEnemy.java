package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public abstract class AbstractEnemy extends MovableEntity implements Enemy{

	private MovementBehavior behavior;

	public AbstractEnemy(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
	}
	
	public AbstractEnemy(int startingX, int startingY, Vector2 movementVector, Velocity speedValue, MovementBehavior movBeh) {
		this(startingX, startingY, movementVector, speedValue);
		this.attachBehavior(movBeh);
	}

	public void attachBehavior(MovementBehavior movBeh){
		this.behavior = movBeh;
	}

	public void useBehavior(Position targetPosition){
		this.movementVector = behavior.getNextMove(targetPosition);
	}
		
	public MovementBehavior getBehavior(){
		return this.behavior;
	}
	
	public abstract int getDamage();
	
	public abstract int getScoreValue();

}
