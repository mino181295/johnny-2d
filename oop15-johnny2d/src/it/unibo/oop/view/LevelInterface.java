package it.unibo.oop.view;

/**
 * Interface for {@link Level}.
 */
public interface LevelInterface extends Showable {

    /**
     * Repaints the {@link LevelPanel}.
     */
    void updateLevel();

    /**
     * @param val
     *            to enable(true)/disable(false) music.
     */
    void enableMusic(final boolean val);

    /**
     * @param level
     *            of initialization.
     */
    void initialize(final int levelNumber);
}
