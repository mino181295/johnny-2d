package it.unibo.oop.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import it.unibo.oop.utilities.Position;

public abstract class AbstractEntity implements Entity {
	
	Position envPosition; 
	//Optional<GameState> gameEnvironment;
	
	public Position getPosition() {
		return this.envPosition;
	}
	
	public int getX(){
		return envPosition.getIntX();
	}
	
	public int getY(){
		return envPosition.getIntY();
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
		Point topLeftCorner = new Point(envPosition.getIntX() - this.getEntityWidth()/2, envPosition.getIntY() - this.getEntityHeight()/2);
		return new Rectangle(topLeftCorner,tmpDim);
	}
	
	/*
	public attachEnvironment(GameState newEnvironment){
	  this.gameEnvironment = newEnvironment;
	}
	
	public removeEnvironment(){
		this.gameEnvironment = Optional.empty();
	}
	
	public Optional<GameState> getEnvironment(){
		return this.gameEnvirnment;
	}
	 
	public void removeFromEnvirnment(){
		gameEnvironment.removeItem(this);
	}
	*/
}
