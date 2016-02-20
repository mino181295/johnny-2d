package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
/**
 * Interface for an enemy 
 * @author Matteo Minardi
 *
 */
public interface Enemy {
	
	void attachBehavior(MovementBehavior enemyBehavior);
	
	void useBehavior(Position targetPosition);
	
	int getScoreValue();
	
	int getDamage();
	
}
