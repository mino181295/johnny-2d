package it.unibo.oop.model;

import java.util.Optional;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;
/**
 * Class rapresenting an enemy with his own {@link MovementBehavior} based on the type. 
 * @author Matteo Minardi
 *
 */
public abstract class AbstractEnemy extends MovableEntity implements Enemy{

	private Optional<MovementBehavior> behavior;

	public AbstractEnemy(double startingX, double startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
	}
	
	public AbstractEnemy(double startingX, double startingY, Vector2 movementVector, Velocity speedValue, MovementBehavior movBeh) {
		this(startingX, startingY, movementVector, speedValue);
		this.attachBehavior(movBeh);
	}
	/**
	 * Changes the behavior with a new one passed as argument
	 */
	public void attachBehavior(MovementBehavior movBeh){
		this.behavior = Optional.of(movBeh);
	}
	/**
	 * Checks if the {@link MovementBehavior} is present and gets the next move from it.
	 */
	public void useBehavior(Position targetPosition){
		behavior.ifPresent(x -> this.setMovement(x.getNextMove(targetPosition)));
	}
	/**
	 * Gets the {@link MovementBehavior} as an {@link Optional}
	 */	
	public Optional<MovementBehavior> getBehavior(){
		return this.behavior;
	}
	/**
	 * Abstract method that indicates the damage dealt with a collision with the {@link MainCharacter}
	 * @return
	 */
	public abstract int getDamage();
	/**
	 * Abstract method that indicates the score bonus taken from the {@link MainCharacter} when it dies from a bullet
	 */
	public abstract int getScoreValue();

}
