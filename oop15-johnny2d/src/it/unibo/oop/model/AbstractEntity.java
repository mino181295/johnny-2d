package it.unibo.oop.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;
import it.unibo.oop.utilities.Position;

/**
 * This abstract class shapes the standard {@link Entity} of the game. Every
 * {@link Entity} has got its own {@link Position}, and its own Environment
 * where it's placed.
 */
public abstract class AbstractEntity implements Entity {

    private Position entityPosition;
    private Optional<GameStateImpl> gameEnvironment = Optional.ofNullable(GameStateImpl.getInstance());
    private boolean deathFlag;
    /**
     * Constructor with a position where to place the {@link AbstractEntity}
     * @param startingX Starting X position
     * @param startingY Starting Y position
     */
    public AbstractEntity(final double startingX, final double startingY) {
        this.entityPosition = new Position(startingX, startingY);
        this.deathFlag = false;
    }

    /**
     * Getter for the {@link Position} of an {@link Entity}.
     */
    public Position getPosition() {
        return this.entityPosition;
    }

    /**
     * Setter for the {@link Position} of an {@link Entity}.
     */
    public void setPosition(final Position newPosition) {
        this.entityPosition = newPosition;
    }
    /**
     * 
     * @return True if the {@link AbstractEnemy} is dead
     */
    public boolean isDead() {
		return this.deathFlag;
	}
    /**
     * Sets the {@link AbstractEntity} dead
     */
	public void killEntity() {
		this.deathFlag = true;
	}

	/**
     * Getter for the X position of an {@link Entity}.
     * 
     * @return
     */
    public double getX() {
        return entityPosition.getX();
    }

    /**
     * Getter for the Y position of an {@link Entity}.
     * 
     * @return
     */
    public double getY() {
        return entityPosition.getY();
    }
    /**
     * The top left corner of the {@link Entity}
     */
    public Position getTopLeftPos() {
        return new Position(entityPosition.getIntX() - this.getEntityWidth() / 2,
                entityPosition.getIntY() - this.getEntityHeight() / 2);
    }

    /**
     * Generates a {@link Rectangle} that delimits the current {@link Entity}.
     * 
     * @return a rectangle containing, in the center, the {@link Entity}
     */
    public Rectangle getBounds() {
        final int tmpWidth = this.getEntityWidth();
        final int tmpHeight = this.getEntityHeight();
        final Dimension tmpDim = new Dimension(tmpWidth, tmpHeight);
        final Point topLeftCorner = new Point(this.getTopLeftPos().getIntX(), this.getTopLeftPos().getIntY());
        return new Rectangle(topLeftCorner, tmpDim);
    }

    /**
     * Returns true if this entity intersecates another one passed as parameter.
     */
    public boolean intersecate(final Entity secondEntity) {
        return this.getBounds().intersects(secondEntity.getBounds());
    }

    /**
     * Getter for the shape Height of the current Object.
     */
    protected abstract int getEntityHeight();

    /**
     * Getter for the shape Width of the current Object.
     */
    protected abstract int getEntityWidth();

    /**
     * Attaches another environment to the current {@link Entity}.
     */
    public void attachEnvironment(final GameStateImpl newEnvironment) {
        this.gameEnvironment = Optional.of(newEnvironment);
    }

    /**
     * Removes the environment if it's necessary.
     */
    public void removeEnvironment() {
        if (this.hasEnvironment()) {
            this.gameEnvironment = Optional.empty();
        }
    }

    /**
     * @return true if the {@link Entity} has got an environment.
     */
    public boolean hasEnvironment() {
        return this.gameEnvironment.isPresent();
    }

    /**
     * @return the environment of an {@link Entity} as an object to manipulate.
     */
    public GameStateImpl getEnvironment() {
        return this.gameEnvironment.get();
    }

}
