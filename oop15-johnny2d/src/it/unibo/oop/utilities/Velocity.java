package it.unibo.oop.utilities;
/**
 * Rapresents the speed and the acceleration of an {@link Entity}
 * @author Matteo Minardi
 *
 */
public class Velocity{

	private final double stillVelocity = 0;
	
	private double maxVelocity;
	private double minVelocity;
	private double velocityScale;
	
	public Velocity(double min, double max, double accelerationTime){	
		this.minVelocity = min;
		this.maxVelocity = max;
		this.velocityScale = (this.maxVelocity - this.minVelocity) / accelerationTime;
	}
	/**
	 * Accelerate the currentVelocity to his new velocity basing on the velocityScale
	 */
	public double slow(double currentVelocity){		 
		double newVelocity = currentVelocity - velocityScale;
		return newVelocity<minVelocity?stillVelocity:newVelocity;
	}
	/**
	 * Decelerate the currentVelocity to his new velocity basing on the velocityScale
	 */
	public double accelerate(double currentVelocity){
		double newVelocity = currentVelocity + velocityScale;
		return newVelocity>maxVelocity?maxVelocity:newVelocity;
	}
	/**
	 * Getter for the maxVelocity of the {@link Entity}
	 * @return
	 */
	public double getMaxVelocity() {
		return this.maxVelocity;
	}
	/**
	 * Getter for the minVelocity of the {@link Entity}
	 * @return
	 */
	public double getMinVelocity() {
		return this.minVelocity;
	}
	
	

}
