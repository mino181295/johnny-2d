package it.unibo.oop.model;

import java.awt.Rectangle;
import it.unibo.oop.utilities.Position;

public abstract class AbstractEntity implements Entity {
	
	Position envPosition;
	
	public Position getPosition() {
		return this.envPosition;
	}
	
	public int getX(){
		return envPosition.getX();
	}
	
	public int getY(){
		return envPosition.getY();
	}
	
	public boolean isHere(Position newPosition){
		return envPosition.equals(newPosition);		
	}
	
	public abstract Rectangle getBounds();

}
