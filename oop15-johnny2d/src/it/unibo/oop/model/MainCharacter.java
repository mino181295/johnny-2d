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
	
	public MainCharacter(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
		currentHealth = new Health(3);
		currentScore = new Score(0);
	}
	
	public MainCharacter(Vector2 movementVector, Velocity speedValue) {
		this(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT/2, movementVector, speedValue);
	}
	
	public MainCharacter() {
		this(Settings.SCREEN_WIDTH/2, Settings.SCREEN_HEIGHT/2, new Vector2(), MAIN_CHARACTER.getSpeed());
	}
	
	public void update(Direction newDirection , boolean isShooting){
		this.setMovement(newDirection.getVector2());
		this.move();
		if (isShooting){
			this.shoot();
		}
	}

	public void checkCollision(Position newPosition) throws CollisionHandlingException {	
		MainCharacter tmpJohnny = new MainCharacter(newPosition.getIntX(), newPosition.getIntY(),this.movementVector, this.speedValues);
		//Counting the number of collided walls (Usually 1)
		long numWallCollisions = this.getEnvironment().getStableList().stream()
				  													  .filter(x -> x instanceof Wall)
				  													  .filter(tmpJohnny::intersecate)
				  													  .count();
		//Collecting the collectables item ( like score bonuses, health recharge or others..)
		List<Collectable> collectablesCollided = this.getEnvironment().getStableList().stream()
																					  .filter(x -> x instanceof Collectable)
																					  .filter(tmpJohnny::intersecate)
																					  .map(x -> (Collectable)x )
																					  .collect(Collectors.toList());
																					  
		//Checking if collided some enemies																			  
		List<AbstractEnemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
																					.filter(x -> x instanceof AbstractEnemy)
																					.filter(tmpJohnny::intersecate)
																					.map(x -> (AbstractEnemy)x)
																					.collect(Collectors.toList());
	
		//If the character collides with a wall in the next move it can't move there
		if (numWallCollisions > 0){
			throw new CollisionHandlingException();
		}
		//If it collides with one or more bonus it takes them and apply it;
		if (collectablesCollided.size() > 0){
			collectablesCollided.stream()
								.forEach(x -> x.collect(this));
			collectablesCollided.stream()
								.forEach(x -> ((AbstractEntity) x)
								.removeFromEnvironment());
		}
		//Checks the collision with the collided enemies. Damage the hero and kills the monsters (temporary)
		if (enemyCollisions.size() > 0){			
			int dmgDealt = enemyCollisions.stream()
						   				  .map(x -> x.getDamage())	
						   				  .reduce((x,y) -> x+y)
						   				  .get();
			
			this.currentHealth.decreaseHealth(dmgDealt);
			
			enemyCollisions.stream()
						   .forEach(x -> x.removeFromEnvironment());
			
			//TODO Modificare con optionale
			if (this.currentHealth.isDead()){
				this.removeFromEnvironment();
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
