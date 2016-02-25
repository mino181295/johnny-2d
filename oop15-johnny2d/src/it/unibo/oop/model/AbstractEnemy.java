package it.unibo.oop.model;

import java.util.Optional;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

/**
 * Class representing an enemy with his own {@link MovementBehavior} based on
 * the type.
 */
public abstract class AbstractEnemy extends MovableEntity implements Enemy {

    private Optional<MovementBehavior> behavior;

    /**
     * Constructor with all the parameters to create an {@link Enemy} without a
     * specific {@link MovementBehavior}
     * 
     * @param startingX
     *            starting X position
     * @param startingY
     *            starting Y position
     * @param movementVector
     *            Initial movement vector
     * @param speedValue
     *            The speed setting of the character
     */
    public AbstractEnemy(final double startingX, final double startingY, final Vector2 movementVector,
            final Velocity speedValue) {
        super(startingX, startingY, movementVector, speedValue);
        this.behavior = (Optional.empty());
    }

    /**
     * Constructor that defines all the standards variables and also the
     * {@link MovementBehavior}
     * 
     * @param movBeh
     *            The {@link MovementBehavior} of the Enemy
     */
    public AbstractEnemy(final double startingX, final double startingY, final Vector2 movementVector,
            final Velocity speedValue, final MovementBehavior movBeh) {
        this(startingX, startingY, movementVector, speedValue);
        this.attachBehavior(movBeh);
    }

    /**
     * Method that updates the {@link Enemy} to the next position using his
     * {@link Vector2}, his {@link Velocity}, his {@link Position} and his
     * {@link MovementBehavior} and checks the collisions.
     */
    public void update() {
        try {
            Vector2 newMovement;
            if (this.getEnvironment().getMainChar().isPresent() && this.getBehavior().isPresent()) {
                newMovement = this.getBehavior().get()
                        .getNextMove(this.getEnvironment().getMainChar().get().getPosition());
            } else {
                newMovement = new Vector2();
            }
            this.checkCollision(this.getPosition().sumVector(newMovement));
            this.setMovement(newMovement);
            this.move();
        } catch (CollisionHandlingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Changes the behavior with a new one passed as parameter.
     */
    public void attachBehavior(final MovementBehavior movBeh) {
        this.behavior = Optional.of(movBeh);
    }

    /**
     * Checks if the {@link MovementBehavior} is present and gets the next move
     * from it.
     */
    public void useBehavior(final Position targetPosition) {
        behavior.ifPresent(x -> this.setMovement(x.getNextMove(targetPosition)));
    }

    /**
     * Getter for the {@link MovementBehavior} as an {@link Optional}.
     */
    public Optional<MovementBehavior> getBehavior() {
        return this.behavior;
    }

    /**
     * Abstract method that indicates the damage dealt with a collision with the
     * {@link MainCharacter}.
     * 
     * @return
     */
    public abstract int getDamage();

    /**
     * Abstract method that indicates the score bonus taken from the
     * {@link MainCharacter} when it dies from a {@link Bullet}
     */
    public abstract int getScoreValue();

}
