package it.unibo.oop.model;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public abstract class MovableEntity extends AbstractEntity implements Movable {

	private Vector2 movementVector;
	private final Velocity speedValues;
	
	public MovableEntity(final double startingX, final double startingY, final Vector2 movementVector, final Velocity speedValue) {
		super(startingX,startingY);
		this.movementVector = movementVector;
		this.speedValues = speedValue;		
	}
		
	public void move(){
		this.setPosition(this.getPosition().sumVector(movementVector)); 
	}
	
	public void setMovement(final Vector2 newMovement){
		this.movementVector = newMovement;
	}
	
	public Vector2 getMovement(){
		return this.movementVector;
	}
	
	public abstract void checkCollision(Position newPosition) throws CollisionHandlingException;
	
	public abstract void update();
	
	public Velocity getVelocity(){
		return this.speedValues;
	}
	
	public Direction getFaceDirection(){
		
		if (Math.abs(this.movementVector.getX()) > Math.abs(this.movementVector.getY())){
			if (this.movementVector.getX() > 0){
				return Direction.RIGHT; 
			} else {
				return Direction.LEFT;
			}
		} else{
			if (this.movementVector.getY() > 0){
				return Direction.DOWN; 
			} else {
				return Direction.UP;
			}
		}
	}
}
