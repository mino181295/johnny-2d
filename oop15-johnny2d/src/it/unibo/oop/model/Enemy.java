package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;

/**
 * Interface for an enemy .
 */
public interface Enemy {
	/**
	 * Attach a new {@link MovementBehavior}
	 */
	void attachBehavior(MovementBehavior enemyBehavior);
	/**
	 * Uses the attached {@link MovementBehavior}
	 */
	void useBehavior(Position targetPosition);
	/**
	 * Gets the score value of the {@link Enemy}
	 */
	int getScoreValue();
	/**
	 * Gets the damage value of the {@link Enemy}
	 */
	int getDamage();
	
}
