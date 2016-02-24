package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.KAMIKAZE_ENEMY;

import java.awt.Rectangle;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;;

public class KamikazeMonster extends AbstractEnemy {

    private static final int SCORE_VALUE = 50;
    private final static int DMG = 2;
    
    private static int actionRadiusLenght = 400;
    
    private boolean isVisible;
    private final Rectangle actionRadius;


    public KamikazeMonster(final double startingX, final double startingY, final Vector2 movementVector,
            final Velocity speedValue) {
        super(startingX, startingY, movementVector, speedValue);
        this.attachBehavior(new KamikazeEnemyBehavior(this));
        this.actionRadius = new Rectangle((int)startingX-actionRadiusLenght/2,(int)startingY - actionRadiusLenght/2, actionRadiusLenght,actionRadiusLenght);
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
    }

    public boolean isVisible() {
		return this.isVisible;
	}

	public void setVisible(final boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Rectangle getActionRadius() {
		return this.actionRadius;
	}

	protected int getEntityHeight() {
        return KAMIKAZE_ENEMY.getHeight();
    }

    protected int getEntityWidth() {
        return KAMIKAZE_ENEMY.getWidth();
    }

    @Override
    public int getScoreValue() {
        return KamikazeMonster.SCORE_VALUE;
    }

    @Override
    public int getDamage() {
        return KamikazeMonster.DMG;
    }

}