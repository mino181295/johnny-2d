package it.unibo.oop.utilities;

/**
 * Encapsulates a 2D vector. Allows some methods that modifies a Vector.
 * {@link https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/math/Vector2.html}.
 */
public class Vector2 extends Position {
	
	/**
	 * General constructor for the vector with the 2 double informations.
	 * @param x X value
	 * @param y Y value
	 */
	public Vector2(final double x, final double y) {
		super(x, y);
	}
	
	/**
	 * Constructs a vector from the given vector.
	 * @param newVector
	 */
	public Vector2(final Vector2 newVector){
		super(newVector.getX(),newVector.getY());
	}
	public Vector2(){
		super(0,0);
	}
	
	/**
	 * Adds the given components to this vector.
	 */
	public Vector2 add(final double x, final double y){
		return new Vector2(this.getX()+x, this.getY()+y);
	}
	
	/**
	 * Adds the given vector to this vector.
	 */
	public Vector2 add(final Vector2 secondVector){
		return new Vector2(this.getX() + secondVector.getX(), this.getY()+ secondVector.getY());
	}
	
	/**
	 * Multiplies this vector by a scalar.
	 */
	public Vector2 scl(final double scale){
		return new Vector2(this.getX()*scale, this.getY()*scale);
	}
	
	/**
	 * Calculates the length of this vector.
	 * @return
	 */
	public double length(){
		return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY());
	}
	
	/**
	 * Normalizes this vector.
	 */
	public Vector2 norm(){
		return new Vector2((int)this.getX()/this.length(), (int)this.getY()/this.length());
	}
	
	/**
	 * Clamps this vector's length to given min and max values.
	 */
	public Vector2 clamp(final double min, final double max){
		if (this.length() < min ){
			return this.setLength(min);
		}
		if (this.length() > max ){
			return this.setLength(max);
		}
		return this;		
	}
	
	/**
	 * Returns a new vector resized to a specific newLength.
	 */
	public Vector2 setLength(final double newLength){
		return new Vector2(this.norm().scl(newLength));
	}

}
