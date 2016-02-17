package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;;

public class BasicMonster extends AbstractEnemy{

	public BasicMonster(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
	}

	private final int SCORE_VALUE = 10;
	private final int DMG = 1;
	
	
	public void update(){
		this.setMovement(this.getBehavior().get().getNextMove(this.getEnvironment().getMainChar().get().getPosition()));
			try {
				this.checkCollision(this.getPosition().sumVector(this.getMovement()));
				this.move();
			} catch (CollisionHandlingException e) {

			}
		
	}
	public void checkCollision(Position newPosition) throws CollisionHandlingException{
		BasicMonster tmpEnemy = new BasicMonster(newPosition.getIntX(), newPosition.getIntY(), this.getMovement(), this.getVelocity());
		
		long numWallCollisions = this.getEnvironment().getStableList().stream()
				  													  .filter(x -> x instanceof Wall)
				  													  .filter(tmpEnemy::intersecate)
				  													  .count();
		
		List<Enemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
																			.filter(x -> x instanceof Enemy)
																			.filter(tmpEnemy::intersecate)
																			.map(x -> (Enemy)x)
																			.collect(Collectors.toList());
		
		if (numWallCollisions > 0){
			throw new CollisionHandlingException();
		}	
		//TODO
		
	}
	protected int getEntityHeight() {
		return BASIC_ENEMY.getHeight();
	}

	protected int getEntityWidth() {
		return BASIC_ENEMY.getWidth();
	}

	@Override
	public int getScoreValue() {
		return this.SCORE_VALUE;
	}
	@Override
	public int getDamage() {
		return this.DMG;
	}	

}
