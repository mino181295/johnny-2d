package it.unibo.oop.model;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public abstract class MovableEntity extends AbstractEntity implements Movable {
	
	Vector2 movementVector;
	Velocity speedValues;

	public abstract void move();
	
	public abstract void checkCollision();
	
	public void setDirection(Vector2 newMovement){
		this.movementVector = newMovement;
	}
	
	public Vector2 getDirection(){
		return this.movementVector;
	}
	
	public void setVelocity(Velocity newVelocity){
		this.speedValues = newVelocity;
	}
	
	public Velocity getVelocity(){
		return this.speedValues;
	}	
}
