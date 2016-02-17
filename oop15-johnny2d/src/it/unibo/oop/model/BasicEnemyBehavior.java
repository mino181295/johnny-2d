package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
/**
 * {@link Enemy} behavior that follows the {@link MainCharacter}.
 * @author Matteo Minardi
 *
 */
public class BasicEnemyBehavior implements MovementBehavior{
	
	private Position playerPosition;
	
	public BasicEnemyBehavior(MovableEntity player){
		this.playerPosition = player.getPosition();
	}
	
	public BasicEnemyBehavior(Position playerPosition){
		this.playerPosition = playerPosition;
	}
	/**
	 * Returns a vector that indicates where the {@link Enemy} should go to follow the {@link MainCharacter}
	 */
	public Vector2 getNextMove(Position targetPosition) {
		//TODO
		return null;
	}
}
