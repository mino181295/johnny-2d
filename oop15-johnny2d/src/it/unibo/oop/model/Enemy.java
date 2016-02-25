package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;

/**
 * Interface for an enemy .
 */
public interface Enemy {
	/**
	 * Attach a new {@link MovementBehavior}
	 */
	public void attachBehavior(MovementBehavior enemyBehavior);
	/**
	 * Uses the attached {@link MovementBehavior}
	 */
	public void useBehavior(Position targetPosition);
	/**
	 * Gets the score value of the {@link Enemy}
	 */
	public int getScoreValue();
	/**
	 * Gets the damage value of the {@link Enemy}
	 */
	public int getDamage();
	
}
