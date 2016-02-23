package it.unibo.oop.model;

import java.awt.Point;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public class KamikazeEnemyBehavior implements MovementBehavior{
	
	private final KamikazeMonster playerPosition;
	
	public KamikazeEnemyBehavior(final KamikazeMonster player){
		this.playerPosition = player;
	}
	
	/**
	 * Returns a vector that indicates where the {@link Enemy} should go to follow the {@link MainCharacter}.
	 */
	public Vector2 getNextMove(final Position targetPosition) {
		Position destination;
		if (playerPosition.getActionRadius().contains(new Point(targetPosition.getIntX(), targetPosition.getIntY()))){
			destination = targetPosition;
			playerPosition.setVisible(true);
		} else {
			destination = new Position(playerPosition.getActionRadius().getCenterX(),playerPosition.getActionRadius().getCenterY());
			playerPosition.setVisible(false);
		}
		final Vector2 newMovement = new Vector2(destination.getX() - playerPosition.getX(), destination.getY() - playerPosition.getY());
		return newMovement.setLength(playerPosition.getVelocity().getMaxVelocity());
	}
}