package it.unibo.oop.view;

/**
 * Interface for {@link Level}.
 */
public interface LevelInterface {

    /**
     * Repaints the {@link LevelPanel}.
     */
    void updateLevel();

    /**
     * @param level
     *            of initialization.
     */
    void initialize(final int levelNumber);
}
