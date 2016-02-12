package it.unibo.oop.utilities;

import java.awt.Point;

/**
 * 
 * @author Matteo Minardi
 * 
 * This class rappresents the position in the screen of every single {@link Entity}
 *
 */
public class Position implements Point2D{
	
	private int x;
	private int y;
	/**
	 * Constructor that creates a position with X and Y
	 * @param x Integer value of the X
	 * @param y Integer value of the Y
	 */
	public Position(final int x, final int y){
		this.x = x;
		this.y = y;		
	}
	/**
	 * Constructor that creates a position with a precedent position
	 * @param x Integer value of the X
	 * @param y Integer value of the Y
	 */
	public Position(Position p){
		this(p.getX(),p.getY());		
	}
	
	public int getX(){
		int valCopy = this.x;
		return valCopy;
	}
	
	public int getY(){
		int valCopy = this.y;
		return valCopy;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	/**
	 * Sum to the current point 
	 * @param secondX second X value
	 * @param secondY second Y value
	 * @return Returns the new Position of the summed vector
	 */
	public Position sumVectors(int secondX, int secondY){
		return new Position(this.getX()+ secondX, this.getY()+ secondY);		
	}
	/**
	 * Distance between 2 points 
	 * @param a point 1
	 * @param b point 2
	 * @return the distance between 2 points
	 */
	public static double pointsDistance(Position a, Position b){
		final int xDistance = Math.abs(a.getX()-b.getX());
		final int yDistance = Math.abs(a.getY()-b.getY());
		return Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance, 2));
	}
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "("+this.getX()+","+this.getY()+")";
	}
	
	
}
