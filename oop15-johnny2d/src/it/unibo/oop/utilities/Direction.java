package it.unibo.oop.utilities;

import static java.lang.Math.*;

public enum Direction {
	
	UP(-1, 0), 
	LEFT(-1, 0),
	RIGHT(1, 0),
	DOWN(0, 1),
	
    NONE(0, 0),
    
    RIGHTUP(-Direction.trigonometricConstant, Direction.trigonometricConstant),    
	LEFTUP(-Direction.trigonometricConstant, -Direction.trigonometricConstant),
	RIGHTDOWN(Direction.trigonometricConstant, Direction.trigonometricConstant),
	LEFTDOWN(-Direction.trigonometricConstant, Direction.trigonometricConstant);
	
	static final double trigonometricConstant = sin(PI/2);
	
	private double x;
	private double y;
	
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private Direction(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 getVector2() {
		return new Vector2(this.x,this.y);
	}
	
}
