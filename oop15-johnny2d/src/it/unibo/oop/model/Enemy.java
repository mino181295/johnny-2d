package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
/**
 * Interface for an enemy 
 * @author Matteo Minardi
 *
 */
public interface Enemy {
	
	public void attachBehavior(MovementBehavior enemyBehavior);
	
	public void useBehavior(Position targetPosition);
	
	public int getScoreValue();
	
	public int getDamage();
	
}
