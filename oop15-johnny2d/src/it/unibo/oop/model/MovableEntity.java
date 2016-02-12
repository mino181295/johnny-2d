package it.unibo.oop.model;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Velocity;

public abstract class MovableEntity extends AbstractEntity implements Movable {
	
	Direction creatureDirection;
	Velocity creatureVel;

	public abstract void move();
	
	public abstract void checkCollision();
	
	public void setDirection(Direction newDirection){
		this.creatureDirection = newDirection;
	}
	
	public Direction getDirection(){
		return this.creatureDirection;
	}
	
	public void setVelocity(Velocity newVelocity){
		this.creatureVel = newVelocity;
	}
	
	public Velocity getVelocity(){
		return this.creatureVel;
	}	
}
