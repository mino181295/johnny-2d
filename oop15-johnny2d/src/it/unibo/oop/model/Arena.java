package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.WALL;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.oop.utilities.Position;

/**
 * Class rappresenting a game Arena bounded by a {@link List} of {@link Wall}
 * where the {@link Entity} can be placed
 *
 */
public class Arena {

    private static final int EMPTY_SPACES = 1;

    private final List<Wall> boundsList;
    private final Rectangle playableRectangle;

    private final List<Position> spawnPoints;

    public Arena(final int panelHeight, final int panelWidth, final int hudDimension) {
        // Settings the arena walls
        this.boundsList = new ArrayList<>();
        final int drawableAreaHeight = panelHeight - hudDimension;

        final int heightRest = drawableAreaHeight % WALL.getHeight();
        final int widthRest = panelWidth % WALL.getWidth();

        final int heightBlocks = drawableAreaHeight / WALL.getHeight();
        final int widthBlocks = panelWidth / WALL.getWidth();
        // Creation of externs walls
        for (int offsetX = EMPTY_SPACES; offsetX < widthBlocks - EMPTY_SPACES-1; offsetX++) {
            for (int offsetY = EMPTY_SPACES; offsetY < heightBlocks - EMPTY_SPACES-1; offsetY++) {
                if (offsetX == EMPTY_SPACES || offsetY == EMPTY_SPACES || offsetX == widthBlocks - 2 - EMPTY_SPACES
                        || offsetY == heightBlocks - 2 - EMPTY_SPACES) {
                    this.boundsList.add(new Wall(widthRest / 2 + offsetX * WALL.getWidth() + WALL.getWidth() / 2,
                            hudDimension + heightRest / 2 + offsetY * WALL.getHeight() + WALL.getHeight() / 2));
                }
            }
        }
        // Getting playable rectangle
        final Position topLeftCorner = this.boundsList.get(0).getPosition();
        this.playableRectangle = new Rectangle(topLeftCorner.getIntX() /*+ (int) WALL.getWidth() / 2*/,
                topLeftCorner.getIntY() /*+ (int) WALL.getHeight() / 2*/,
                (int) (widthBlocks - 2 * EMPTY_SPACES) * WALL.getWidth(),
                (int) (heightBlocks - 2 * EMPTY_SPACES) * WALL.getHeight());

        this.spawnPoints = new ArrayList<>();
    }

    /**
     * Returns the {@link List} of the bounding {@link Wall} entities
     */
    public List<Wall> getBoundsList() {
        return this.boundsList;
    }

    /**
     * Getter that returns a {@link Rectangle} of the playable place inside the
     * {@link List} of {@link Wall}
     */
    public Rectangle getPlayableRectangle() {
        return this.playableRectangle;
    }

    /**
     * Return the possible spawn points
     */
    public List<Position> getSpawnPoints() {
        return this.spawnPoints;
    }

    /**
     * Add a spawn point to the possible spawn points {@link List}
     * 
     * @param spawnPosition
     *            The spawn point that will be added
     */
    public void addSpawnPoint(final Position spawnPosition) {
        this.spawnPoints.add(spawnPosition);
    }

    /**
     * Gets a random spawn point from the list
     */
    public Position getRandomSpawnPoint() {
        final Random tmpRandom = new Random(this.spawnPoints.size());
        return this.spawnPoints.get(tmpRandom.nextInt());
    }

    /**
     * 
     */
    public double getUpperY() {
        return this.playableRectangle.getY();
    }

    /**
     * 
     */
    public double getLowerY() {
        return this.playableRectangle.getY() + this.playableRectangle.getHeight();
    }

    /**
     * 
     */
    public double getLeftX() {
        return this.playableRectangle.getX();
    }

    /**
     * 
     */
    public double getRightX() {
        return this.playableRectangle.getX() + this.playableRectangle.getWidth();
    }

    /**
     * Gets a position inside the playable {@link Rectangle}
     */
    public Position getPositionInside() {
        final Random randX = new Random((long) playableRectangle.getWidth());
        final Random randY = new Random((long) playableRectangle.getHeight());

        return new Position(randX.nextInt() + this.playableRectangle.getX(),
                randY.nextInt() + this.playableRectangle.getY());
    }

    /**
     * Checks if the parameter is inside or outside the bounding {@link Wall}
     */
    public boolean isInside(final Entity entity) {
        boolean isInside = true;
        if (!this.playableRectangle.intersects(entity.getBounds())) {
            return false;
        }
        for (final Wall block : this.boundsList) {
            if (block.intersecate(entity)) {
                isInside = false;
            }
        }
        return isInside;
    }
}
