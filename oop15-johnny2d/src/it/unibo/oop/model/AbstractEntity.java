package it.unibo.oop.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;
import it.unibo.oop.utilities.Position;
/**
 * This abstract class shapes the standard {@link Entity} of the game. Every entity
 * has got its own {@link Position}, and its own Environment where it's placed. 
 * @author Matteo Minardi
 *
 */
public abstract class AbstractEntity implements Entity {
	
	private Position entityPosition; 
	private Optional<GameState> gameEnvironment; //TODO assegnamento alla getInstance statica

	public AbstractEntity(double startingX, double startingY){
		this.entityPosition = new Position(startingX,startingY);
	}
	/**
	 * Getter of the position of the {@link Entity}
	 */	
	public Position getPosition() {
		return this.entityPosition;
	}
	/**
	 * Setter of the position of the {@link Entity}
	 */
	public void setPosition(Position newPosition) {
		this.entityPosition = newPosition;
	}
	/**
	 * Get the X position of an entity
	 * @return
	 */
	public double getX(){
		return entityPosition.getX();
	}
	/**
	 * Get the Y position of an entity
	 * @return
	 */
	public double getY(){
		return entityPosition.getY();
	}
	
	public boolean equalsPosition(final Position newPosition){
		return entityPosition.equals(newPosition);		
	}
	
	public Position getTopLeftPos(){
		return new Position(entityPosition.getIntX() - this.getEntityWidth()/2, entityPosition.getIntY() - this.getEntityHeight()/2);
	}
	/**
	 * Method that generate a {@link Rectangle} that delimits the current {@link Entity}.
	 * @return A rectangle containing, in the center, the {@link Entity}
	 */	
	public Rectangle getBounds(){
		int tmpWidth = this.getEntityWidth();
		int tmpHeight = this.getEntityHeight();
		Dimension tmpDim = new Dimension(tmpWidth,tmpHeight);
		Point topLeftCorner = new Point(this.getTopLeftPos().getIntX(), this.getTopLeftPos().getIntY());
		return new Rectangle(topLeftCorner,tmpDim);
	}	
	/**
	 * Returns true if this entity intersecate another one passed as param.
	 */
	public boolean intersecate(final Entity secondEntity){
		return this.getBounds().intersects(secondEntity.getBounds());
	}
	/**
	 * Gets the shape Height of the current Object
	 */
	protected abstract int getEntityHeight();
	/**
	 * Gets the shape Width of the current Object
	 */
	protected abstract int getEntityWidth();
	/**
	 * Attaches another Envirnment to the current Entity.
	 */
	public void attachEnvironment(GameState newEnvironment){
	  this.gameEnvironment = Optional.of(newEnvironment);
	}
	
	public void removeEnvironment(){
		if (this.gameEnvironment.isPresent()){
			this.gameEnvironment = Optional.empty();
		}
	}	
	public boolean hasEnvirnment(){
		if (this.gameEnvironment.isPresent() ){
			return true;
		}
		return false;
	}	
	public GameState getEnvironment(){
		return this.gameEnvironment.get();
	}	 
	public void removeFromEnvironment(){
		gameEnvironment.get().removeEntity(this);
	}
	
}
