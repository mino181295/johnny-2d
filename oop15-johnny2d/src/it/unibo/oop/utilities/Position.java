package it.unibo.oop.utilities;

/**
 * 
 * @author Matteo Minardi
 * 
 * This class rappresents the position in the screen of every single {@link Entity}
 *
 */
public class Position implements Point2{
	
	private double x;
	private double y;
	/**
	 * Constructor that creates a position with X and Y
	 * @param x Integer value of the X
	 * @param y Integer value of the Y
	 */
	public Position(final double x, final double y){
		this.x = x;
		this.y = y;		
	}
	
	public Position(final int x, final int y){
		this((double)x,(double)y);
	}
	/**
	 * Constructor that creates a position with a precedent position
	 * @param x Integer value of the X
	 * @param y Integer value of the Y
	 */
	public Position(Position p){
		this(p.getX(),p.getY());		
	}
	
	public double getX(){
		final double valCopy = this.x;
		return valCopy;
	}
	
	public int getIntX(){
		final double valCopy = this.x;
		return (int)valCopy;
	}
	
	public double getY(){
		final double valCopy = this.y;
		return valCopy;
	}
	
	public int getIntY(){
		final double valCopy = this.y;
		return (int)valCopy;
	}

	
	public void setX(double newX){
		this.x = newX;
	}
	
	public void setY(double newY){
		this.y = newY;
	}
	/**
	 * Sum to the current point 
	 * @param secondX second X value
	 * @param secondY second Y value
	 * @return Returns the new Position of the summed vector
	 */
	public Position sumVector(Vector2 movement){
		return new Position((int)(this.getX()+ movement.getX()), (int)(this.getY()+ movement.getY()));		
	}
	/**
	 * Distance between 2 points 
	 * @param a point 1
	 * @param b point 2
	 * @return the distance between 2 points
	 */
	public static double pointsDistance(Position a, Position b){
		final double xDistance = Math.abs(a.getX()-b.getX());
		final double yDistance = Math.abs(a.getY()-b.getY());
		return Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance, 2));
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
