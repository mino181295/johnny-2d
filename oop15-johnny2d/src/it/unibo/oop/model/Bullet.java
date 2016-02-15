package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BULLET;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public class Bullet extends MovableEntity implements Shot {

	
	public Bullet(int startingX, int startingY, Vector2 movementVector) {
		super(startingX, startingY, movementVector, BULLET.getSpeed());
		this.setInput(movementVector);
	}

	public Bullet(MainCharacter heroPosition) {
		this(heroPosition.getX(), heroPosition.getY(), heroPosition.getDirection());
		this.setInput(heroPosition.getDirection());
	}


	private int distancePercurred;
	private final int maxBulletDistance = 10 + new Random().nextInt(10);

	
	@Override
	public void checkCollision(Position newPosition) throws CollisionHandlingException {
		// Creation of the bullet in the next position
		Bullet tmpBullet = new Bullet(newPosition.getIntX(),newPosition.getIntY(),this.movementVector);
		//Counting how mutch walls it collides (Usually 1)
		long numWallCollisions = this.getEnvironment().getStableList().stream()
											 						  .filter(x -> x instanceof Wall)
											 						  .filter(tmpBullet::intersecate)
											 						  .count();
		//Collectr all the Enemies collided (usually 1)
		List<AbstractEntity> enemyCollisions = this.getEnvironment().getMovableList().stream()
																	.filter(x -> x instanceof Enemy)
																	.filter(tmpBullet::intersecate)
																	.collect(Collectors.toList());
		//If collides a wall the bullet dies and gets removed																	
		if (numWallCollisions > 0){
			this.removeFromEnvirnment();
			throw new CollisionHandlingException();
		}
		//If the bullet collides with an enemy both die
		if (enemyCollisions.size() > 0){
			enemyCollisions.stream().forEach(x -> x.removeFromEnvirnment());
			this.removeFromEnvirnment();
			throw new CollisionHandlingException();
		}

		
	}
	
	public void update(){
		try {
			this.checkCollision(this.getPosition().sumVector(movementVector));
			this.move();
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

	@Override
	public int getDistancePercurred() {
		return this.distancePercurred;
	}

	
	
	

}
