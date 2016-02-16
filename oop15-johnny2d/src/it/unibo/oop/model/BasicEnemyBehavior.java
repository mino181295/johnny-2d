package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public class BasicEnemyBehavior implements MovementBehavior{
	
	private Position playerPosition;
	
	public BasicEnemyBehavior(MovableEntity player){
		this.playerPosition = player.getPosition();
	}
	
	public BasicEnemyBehavior(Position playerPosition){
		this.playerPosition = playerPosition;
	}

	public Vector2 getNextMove(Position targetPosition) {
		return null;
	}
}
