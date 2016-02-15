package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;

public interface Enemy {
	
	public void attachBehavior(MovementBehavior enemyBehavior);
	
	public void useBehavior(Position targetPosition);
	
	int getScoreValue();
	
}
