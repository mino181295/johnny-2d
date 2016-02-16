package it.unibo.oop.utilities;

public class Velocity{

	private double maxVelocity;
	private double minVelocity;
	private double velocityScale;
	
	public Velocity(double min, double max, double accelerationTime){	
		this.minVelocity = min;
		this.maxVelocity = max;
		this.velocityScale = (this.maxVelocity - this.minVelocity) / accelerationTime;
	}
	
	public double slow(double currentVelocity){		 
		return currentVelocity + velocityScale;
	}
	
	public double accelerate(double currentVelocity){
		return currentVelocity + velocityScale;
	}

	public double getMaxVelocity() {
		return this.maxVelocity;
	}

	public double getMinVelocity() {
		return this.minVelocity;
	}
	
	

}
