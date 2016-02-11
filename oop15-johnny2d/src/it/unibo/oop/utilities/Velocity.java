package it.unibo.oop.utilities;

public class Velocity{
	
	private static final int VELOCITY_SCALE = 10;

	private int xVelocity;
	private int yVelocity;
	
	private int maxVelocity;
	private int minVelocity;
	
	public Velocity(int x, int y, int velocityScale, int min, int max){
		this.xVelocity=x;
		this.yVelocity=y;
		
		this.minVelocity=min;
		this.maxVelocity=max;
	}
	
	public int getXVelocity(){
		return this.xVelocity;
	}
	
	public int getYVelocity(){
		return this.yVelocity;
	}
	
	public void slow(){
		
	}
	
	public void accelerate(){
		
	}
	
	public Position getNextPosition(Position currentPosition){
		return new Position(currentPosition.getX()+getXVelocity(), currentPosition.getY()+getXVelocity());		
	}

}
