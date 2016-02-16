package it.unibo.oop.model;

import java.util.Optional;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public abstract class AbstractEnemy extends MovableEntity implements Enemy{

	private Optional<MovementBehavior> behavior;

	public AbstractEnemy(double startingX, double startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
	}
	
	public AbstractEnemy(double startingX, double startingY, Vector2 movementVector, Velocity speedValue, MovementBehavior movBeh) {
		this(startingX, startingY, movementVector, speedValue);
		this.attachBehavior(movBeh);
	}

	public void attachBehavior(MovementBehavior movBeh){
		this.behavior = Optional.of(movBeh);
	}

	public void useBehavior(Position targetPosition){
		behavior.ifPresent(x -> this.setMovement(x.getNextMove(targetPosition)));
	}
		
	public Optional<MovementBehavior> getBehavior(){
		return this.behavior;
	}
	
	public abstract int getDamage();
	
	public abstract int getScoreValue();

}
