package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BULLET;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
/**
 * Class rappresnting the Bullet fired from an {@link Entity} that implements a {@link Shooter}.
 * @author Matteo Minardi
 *
 */
public class Bullet extends MovableEntity implements Shot {

	private double remainingDistance = 10 + new Random().nextInt(10);
	
	public Bullet(int startingX, int startingY, Vector2 movementVector) {
		super(startingX, startingY, movementVector, BULLET.getSpeed());
		this.setMovement(movementVector);
	}

	public Bullet(MainCharacter heroPosition) {
		this(heroPosition.getX(), heroPosition.getY(), heroPosition.getMovement());
		//Takes the hero position
		this.setMovement(heroPosition.getMovement());
		//The movement vector is in the same Hero direction but in another speed values
		this.getMovement().setLength(this.getVelocity().getMinVelocity());
	}
	
	public void checkCollision(Position newPosition) throws CollisionHandlingException {
		// Creation of the bullet in the next position
		Bullet tmpBullet = new Bullet(newPosition.getIntX(),newPosition.getIntY(),this.movementVector);
		//Counting how mutch walls it collides (Usually 1)
		long numWallCollisions = this.getEnvironment().getStableList().stream()
											 						  .filter(x -> x instanceof Wall)
											 						  .filter(tmpBullet::intersecate)
											 						  .count();
		//Collectr all the Enemies collided (usually 1)
		List<Enemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
																	.filter(x -> x instanceof Enemy)
																	.filter(tmpBullet::intersecate)
																	.map(x -> (Enemy)x)
																	.collect(Collectors.toList());
		//If collides a wall the bullet dies and gets removed																	
		if (numWallCollisions > 0){
			this.removeFromEnvironment();
			throw new CollisionHandlingException();
		}
		//If the bullet collides with an enemy both die
		if (enemyCollisions.size() > 0){
			//Calculates the score obtained killing the monsters
			int tmpScore = enemyCollisions.stream().map(x -> x.getScoreValue())
												   .reduce((x,y) -> x+y)
												   .get();
			this.getEnvironment().getMainChar().get().getScore().increaseScore(tmpScore);
			
			//Removes the monsters from the envirnoment
			enemyCollisions.stream().forEach(x -> ((AbstractEntity) x).removeFromEnvironment());
			this.removeFromEnvironment();
			//Throws the exception avoiding the next movement
			throw new CollisionHandlingException();
		}		
	}
	
	public void update(){
		try {
			//Calculates the new movement vector
			Vector2 newMovement = this.getMovement().setLength(this.getVelocity().accelerate(this.getMovement().length()));			
			this.setMovement(newMovement.clamp(this.getVelocity().getMinVelocity(), this.getVelocity().getMaxVelocity()));
			//Check if there are collision in the new position
			this.checkCollision(this.getPosition().sumVector(movementVector));
			//moves if no exception
			this.move();
			this.remainingDistance -= this.movementVector.length();
			if (remainingDistance <= 0){
				this.removeFromEnvironment();
			}
		} catch (CollisionHandlingException e){
			System.out.println("Il proiettile ha colliso ed è stato rimosso");
		}
	}
	protected int getEntityHeight() {
		return BULLET.getHeight();
	}

	@Override
	protected int getEntityWidth() {
		return BULLET.getWidth();
	}

	public double getRemainingDistance(){
		return this.remainingDistance;
	}
}
