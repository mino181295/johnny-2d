package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;;

public class BasicMonster extends AbstractEnemy {

    private static final int SCORE_VALUE = 10;
    private final static int DMG = 1;

    public BasicMonster(final double startingX, final double startingY, final Vector2 movementVector,
            final Velocity speedValue) {
        super(startingX, startingY, movementVector, speedValue);
        this.attachBehavior(new BasicEnemyBehavior(this));
    }

    public void update() {
    	Vector2 newMovement;
    	if ( this.getEnvironment().getMainChar().isPresent() ){
    		newMovement  = this.getBehavior().get().getNextMove(this.getEnvironment().getMainChar().get().getPosition());
    	} else {
    		newMovement = new Vector2();   		
    	}
        
        try {
            this.checkCollision(this.getPosition().sumVector(newMovement));
            this.setMovement(newMovement);
            this.move();
        } catch (CollisionHandlingException e) {
        	e.getMessage();
        }

    }

    public void checkCollision(final Position newPosition) throws CollisionHandlingException {
        final BasicMonster tmpEnemy = Factory.EnemiesFactory.generateStillBasicEnemy(newPosition.getIntX(), newPosition.getIntY());
        // Checks if the entity in the next move is inside the rectanuglar Arena
        if (!this.getEnvironment().getArena().isInside(tmpEnemy)) {
            throw new CollisionHandlingException("Next movement not inside the arena");
        }
        final long numWallCollisions = this.getEnvironment().getStableList().stream().filter(x -> x instanceof Wall)
                .filter(tmpEnemy::intersecate).count();
        
        if (numWallCollisions > 0) {
            throw new CollisionHandlingException("Next movement collides a wall");
        }
        final List<AbstractEnemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
                .filter(x -> x instanceof AbstractEnemy).filter(tmpEnemy::intersecate).map(x -> (AbstractEnemy) x)
                .collect(Collectors.toList());
        
        if (enemyCollisions.size() > 1){
        	throw new CollisionHandlingException();
        }

    }

    protected int getEntityHeight() {
        return BASIC_ENEMY.getHeight();
    }

    protected int getEntityWidth() {
        return BASIC_ENEMY.getWidth();
    }

    @Override
    public int getScoreValue() {
        return BasicMonster.SCORE_VALUE;
    }

    @Override
    public int getDamage() {
        return BasicMonster.DMG;
    }

}
