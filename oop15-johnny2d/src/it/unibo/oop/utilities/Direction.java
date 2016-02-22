package it.unibo.oop.utilities;

import static java.lang.Math.*;

public enum Direction {
	
	UP(0, -1), 
	LEFT(-1, 0),
	RIGHT(1, 0),
	DOWN(0, 1),
	
    NONE(0, 0),
    
    RIGHTUP(-Direction.TRIGONOMETRIC_CONSTANT, Direction.TRIGONOMETRIC_CONSTANT),    
	LEFTUP(-Direction.TRIGONOMETRIC_CONSTANT, -Direction.TRIGONOMETRIC_CONSTANT),
	RIGHTDOWN(Direction.TRIGONOMETRIC_CONSTANT, Direction.TRIGONOMETRIC_CONSTANT),
	LEFTDOWN(-Direction.TRIGONOMETRIC_CONSTANT, Direction.TRIGONOMETRIC_CONSTANT);
	
	private static final double TRIGONOMETRIC_CONSTANT = sin(PI/2);
	
	private double x;
	private double y;
	
	private Direction(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	private Direction(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 getVector2() {
		return new Vector2(this.x,this.y);
	}
}