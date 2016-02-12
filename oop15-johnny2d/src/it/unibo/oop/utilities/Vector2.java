package it.unibo.oop.utilities;

public class Vector2 extends Position{
	
	
	public Vector2(double x, double y) {
		super(x, y);
	}
	
	public Vector2(Vector2 newVector){
		super(newVector.getX(),newVector.getY());
	}

	public Vector2 add(double x, double y){
		return new Vector2(this.getX()+x, this.getY()+y);
	}
	
	public Vector2 add(Vector2 secondVector){
		return new Vector2(this.getX() + secondVector.getX(), this.getY()+ secondVector.getY());
	}
	
	public Vector2 mul(double scale){
		return new Vector2(this.getX()*scale, this.getY()*scale);
	}
	
	public double length(){
		return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY());
	}
	
	public Vector2 norm(){
		return new Vector2((int)this.getX()/this.length(), (int)this.getY()/this.length());
	}
	
	public Vector2 clamp(double min, double max){
		if (this.length() < min ){
			return this.setLength(min);
		}
		if (this.length() > max ){
			return this.setLength(max);
		}
		return this;		
	}
	
	private Vector2 setLength(double newLength){
		return new Vector2(this.getX(),this.getY()).norm().mul(newLength);
	}

}
