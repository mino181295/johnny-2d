package it.unibo.oop.model;

import java.awt.Dimension;
import java.awt.Point;
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
	
	public boolean equalsPosition(Position newPosition){
		return envPosition.equals(newPosition);		
	}
	
	protected abstract int getEntityHeight();

	protected abstract int getEntityWidth();
	
	public Rectangle getBounds(){
		int tmpWidth = this.getEntityWidth();
		int tmpHeight = this.getEntityHeight();
		Dimension tmpDim = new Dimension(tmpWidth,tmpHeight);
		Point topLeftCorner = new Point(envPosition.getX() - this.getEntityWidth()/2, envPosition.getY() - this.getEntityHeight()/2);
		return new Rectangle(topLeftCorner,tmpDim);
	}

}
