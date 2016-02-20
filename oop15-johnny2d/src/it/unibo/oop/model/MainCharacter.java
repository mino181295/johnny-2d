package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.MAIN_CHARACTER;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Settings;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public class MainCharacter extends MovableEntity implements Shooter{

	private Health currentHealth;
	private Score currentScore;
	
	public MainCharacter(final double startingX, final double startingY, final Vector2 movementVector, final Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
		currentHealth = new Health(3);
		currentScore = new Score(0);
	}
	
	public MainCharacter(final double startingX, final double startingY, final Vector2 startingMovement) {
		this(startingX, startingY, startingMovement, MAIN_CHARACTER.getSpeed());
	}
	
	public MainCharacter(final double startingX, final double startingY) {
		this(startingX, startingY, new Vector2(), MAIN_CHARACTER.getSpeed());
	}
	
	public MainCharacter() {
		this(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT/2, new Vector2(), MAIN_CHARACTER.getSpeed());
	}
	
	public void update(final Direction newDirection , final boolean isShooting){
		//Takes the new frame direction
		Vector2 newMovement = newDirection.getVector2();
		//If the main character is accelerating
		try {
			if (newDirection != Direction.NONE){
				newMovement = newMovement.setLength(this.getVelocity().accelerate(this.getMovement().length()));
			} else {
				newMovement = newMovement.setLength(this.getVelocity().slow(this.getMovement().length()));
			}
			//newMovement = newMovement.clamp(this.getVelocity().getMinVelocity(), this.getVelocity().getMaxVelocity());
			this.checkCollision(this.getPosition().sumVector(newMovement));
			this.setMovement(newMovement);
			this.move();	
		} catch (CollisionHandlingException e) {
		} finally {
			if (isShooting && !this.currentHealth.isDead()){
				this.shoot();
			}
		}
		
	}

	public void checkCollision(final Position newPosition) throws CollisionHandlingException {	
		
		final MainCharacter tmpJohnny = Factory.MainCharacterFactory.generateStillCharacter(newPosition.getX(), newPosition.getY());
		//Counting the number of collided walls (Usually 1)
		final long numWallCollisions = this.getEnvironment().getStableList().stream()
				  													  .filter(x -> x instanceof Wall)
				  													  .filter(tmpJohnny::intersecate)
				  													  .count();
		//Collecting the collectables item ( like score bonuses, health recharge or others..)
		final List<Collectable> collectablesCollided = this.getEnvironment().getStableList().stream()
																					  .filter(x -> x instanceof Collectable)
																					  .filter(tmpJohnny::intersecate)
																					  .map(x -> (Collectable)x )
																					  .collect(Collectors.toList());
																					  
		//Checking if collided some enemies																			  
		final List<AbstractEnemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
																					.filter(x -> x instanceof AbstractEnemy)
																					.filter(tmpJohnny::intersecate)
																					.map(x -> (AbstractEnemy)x )
																					.collect(Collectors.toList());
	
		//If the character collides with a wall in the next move it can't move there
		if (numWallCollisions > 0){
			throw new CollisionHandlingException();
		}
		//If it collides with one or more bonus it takes them and apply it;
		if (!collectablesCollided.isEmpty()){
			collectablesCollided.stream()
								.forEach(x -> x.collect(this));
			collectablesCollided.stream()
								.forEach(x -> ((AbstractEntity) x).removeFromEnvironment());
		}
		//Checks the collision with the collided enemies. Damage the hero and kills the monsters (temporary)
		if (!enemyCollisions.isEmpty()){			
			final int dmgDealt = enemyCollisions.stream()
						   				  .map(x -> x.getDamage())	
						   				  .reduce((x,y) -> x+y)
						   				  .get();
			this.currentHealth.decreaseHealth(dmgDealt);
			
			final int scoreGained = enemyCollisions.stream()
	   				  						 .map(x -> x.getScoreValue())	
	   				  						 .reduce((x,y) -> x+y)
	   				  						 .get();			
			this.currentScore.increaseScore(scoreGained);
			
			enemyCollisions.stream()
						   .forEach(x -> x.removeFromEnvironment());
			
			if (this.currentHealth.isDead()){
				this.getEnvironment().killMainChar();
				throw new CollisionHandlingException();
			}
		}	
	}

	
	protected int getEntityHeight() {
		return MAIN_CHARACTER.getHeight();
	}

	protected int getEntityWidth() {
		return MAIN_CHARACTER.getWidth();
	}

	public Score getScore(){
		return this.currentScore;
	}
	
	public void shoot() {
		this.getEnvironment().addShoot(new Bullet(this));		
	}

}
