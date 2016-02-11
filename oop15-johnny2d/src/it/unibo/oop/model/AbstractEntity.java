package it.unibo.oop.model;

import java.awt.Rectangle;
import it.unibo.oop.utilities.Position;

public abstract class AbstractEntity implements Entity {
	
	Position envPosition;
	
	public Position getPosition() {
		return this.envPosition;
	}
	
	public boolean isHere(Position newPosition){
		return envPosition.equals(newPosition);		
	}
	
	public abstract Rectangle getBounds();

}
