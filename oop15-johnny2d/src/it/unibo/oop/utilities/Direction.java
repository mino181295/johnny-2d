package it.unibo.oop.utilities;


public enum Direction {
	
	UP(-1,0), 
	DOWN(0,1),
	LEFT(-1,0),
	RIGHT(1,0),
    NONE(0,0);
	
	private int x;
	private int y;
	
	private Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}
