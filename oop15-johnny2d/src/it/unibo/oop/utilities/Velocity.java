package it.unibo.oop.utilities;

public class Velocity{

	private int maxVelocity;
	private int minVelocity;
	private int time;
	
	public Velocity(int min, int max, int velocityScale){
	
		this.minVelocity=min;
		this.maxVelocity=max;
	}
	
	public void slow(){
		
	}
	
	public void accelerate(){
		
	}

	public int getMaxVelocity() {
		return this.maxVelocity;
	}

	public int getMinVelocity() {
		return this.minVelocity;
	}
	
	

}
