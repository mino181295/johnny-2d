package it.unibo.oop.model;
import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public abstract class MovableEntity extends AbstractEntity implements Movable {

	private Vector2 movementVector;
	private Velocity speedValues;
	
	public MovableEntity(double startingX, double startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX,startingY);
		this.movementVector = movementVector;
		this.speedValues = speedValue;		
	}
		
	public void move(){
		this.setPosition(this.getPosition().sumVector(movementVector)); 
	}
	
	public void setMovement(Vector2 newMovement){
		this.movementVector = newMovement;
	}
	
	public Vector2 getMovement(){
		return this.movementVector;
	}
	
	public abstract void checkCollision(Position newPosition) throws CollisionHandlingException;
	
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
