package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
/**
 * {@link Enemy} behavior that follows the {@link MainCharacter}.
 * @author Matteo Minardi
 *
 */
public class BasicEnemyBehavior implements MovementBehavior{
	
	private final MovableEntity playerPosition;
	
	public BasicEnemyBehavior(final MovableEntity player){
		this.playerPosition = player;
	}
	
	/**
	 * Returns a vector that indicates where the {@link Enemy} should go to follow the {@link MainCharacter}
	 */
	public Vector2 getNextMove(final Position targetPosition) {
		final Vector2 newMovement = new Vector2(targetPosition.getX() - playerPosition.getX(), targetPosition.getY() - playerPosition.getY());
		return newMovement.setLength(playerPosition.getVelocity().getMaxVelocity());
	}
}
