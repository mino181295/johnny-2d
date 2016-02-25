package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.MAIN_CHARACTER;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public class MainCharacter extends MovableEntity implements Shooter {

    private Health currentHealth;
    private Score currentScore;

    private Direction lastDirection;
    private Direction currentDirection;
    
    private boolean isShooting;

    public MainCharacter(final double startingX, final double startingY, final Vector2 startingMovement) {
        super(startingX, startingY, startingMovement, MAIN_CHARACTER.getSpeed());
        this.currentHealth = new Health();
        this.currentScore = new Score(0);
        this.lastDirection = Direction.UP;
        this.currentDirection = Direction.NONE;
    }

    public MainCharacter(final double startingX, final double startingY) {
        this(startingX, startingY, new Vector2());
    }
    
    public void setInput(final Direction newDirection, final boolean isShooting){
    	if (newDirection != Direction.NONE){
    		lastDirection = newDirection;
    	}
    	this.currentDirection = newDirection;    	
    	this.isShooting = isShooting;
    }

    public void update() {
        // Takes the new frame direction
        Vector2 newMovement = currentDirection.getVector2();
        // If the main character is accelerating
        try {
            if ((currentDirection != Direction.NONE)) {
                newMovement = newMovement.setLength(this.getVelocity().accelerate(this.getMovement().length()));
            } else {
                newMovement = this.getMovement().setLength(this.getVelocity().slow(this.getMovement().length()));
            }
            this.checkCollision(this.getPosition().sumVector(newMovement));
            this.setMovement(newMovement);
            this.move();
        } catch (CollisionHandlingException e) {
            System.out.println(e.getMessage());
        } finally {
            if (isShooting && !this.currentHealth.isDead()) {
                this.shoot();
            }
        }

    }

    public void checkCollision(final Position newPosition) throws CollisionHandlingException {

        final MainCharacter tmpJohnny = Factory.MainCharacterFactory.generateStillCharacter(newPosition.getX(),
                newPosition.getY());
        // Checks if in the next move the character is inside the Arena
        if (!this.getEnvironment().getArena().isInside(tmpJohnny)) {
            throw new CollisionHandlingException("Next movement not inside the arena");
        }
        // Counting the number of collided walls (Usually 1)
        final long numWallCollisions = this.getEnvironment().getStableList().stream().filter(x -> x instanceof Wall)
                .filter(tmpJohnny::intersecate).count();
        // Collecting the collectables item ( like score bonuses, health
        // recharge or others..)
        final List<Collectable> collectablesCollided = this.getEnvironment().getStableList().stream()
                .filter(x -> x instanceof Collectable).filter(tmpJohnny::intersecate).map(x -> (Collectable) x)
                .collect(Collectors.toList());

        // Checking if collided some enemies
        final List<AbstractEnemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
                .filter(x -> x instanceof AbstractEnemy).filter(tmpJohnny::intersecate).map(x -> (AbstractEnemy) x)
                .collect(Collectors.toList());

        // If the character collides with a wall in the next move it can't move
        // there
        if (numWallCollisions > 0) {
            System.out.println("Entrato");
            throw new CollisionHandlingException("Next move collides a Wall");
        }
        // If it collides with one or more bonus it takes them and apply it;
        if (!collectablesCollided.isEmpty()) {
            collectablesCollided.stream().forEach(x -> x.collect(this));
            collectablesCollided.stream().forEach(x -> ((AbstractEntity) x).killEntity());
        }
        // Checks the collision with the collided enemies. Damage the hero and
        // kills the monsters (temporary)
        if (!enemyCollisions.isEmpty()) {
            final int dmgDealt = enemyCollisions.stream().map(x -> x.getDamage()).reduce((x, y) -> x + y).get();
            this.currentHealth.decreaseHealth(dmgDealt);

            final int scoreGained = enemyCollisions.stream().map(x -> x.getScoreValue()).reduce((x, y) -> x + y).get();
            this.currentScore.increaseScore(scoreGained);

            enemyCollisions.stream().forEach(x -> x.killEntity());

            if (this.isDead()) {
                throw new CollisionHandlingException("The main character died");
            }
        }
    }

    @Override
    public Direction getFaceDirection() {
        switch (lastDirection) {
        case LEFTDOWN:
            return Direction.DOWN;
        case LEFTUP:
            return Direction.UP;
        case RIGHTUP:
            return Direction.UP;
        case RIGHTDOWN:
            return Direction.DOWN;
        case NONE:
            return Direction.UP;

        default:
            return lastDirection;
        }
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    protected int getEntityHeight() {
        return MAIN_CHARACTER.getHeight();
    }

    protected int getEntityWidth() {
        return MAIN_CHARACTER.getWidth();
    }

    public Score getScore() {
        return this.currentScore;
    }

    public Health getHealth() {
        return this.currentHealth;
    }

    @Override
    public boolean isDead() {
        return this.currentHealth.isDead();
    }
    /**
     * Shoots a normal bullet adding it in the {@link GameStateImpl}
     */
    public void shoot() {
        this.getEnvironment().addShoot(new Bullet(this));
    }
}