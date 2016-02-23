package it.unibo.oop.model;

import static it.unibo.oop.utilities.Settings.SCREEN_HEIGHT;
import static it.unibo.oop.utilities.Settings.SCREEN_WIDTH;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;

public final class GameStateImpl implements GameState {

    private static final GameStateImpl SINGLETON = new GameStateImpl();
    private final List<MovableEntity> movableList;
    private final List<AbstractEntity> stableList;
    private Optional<MainCharacter> johnnyCharacter;
    private final Arena gameArena;

    private GameStateImpl() {
        this.movableList = new ArrayList<>();
        this.stableList = new ArrayList<>();
        this.johnnyCharacter = Optional.of(new MainCharacter());
        this.gameArena = Factory.WallFactory.generateArena(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    public static GameStateImpl getInstance() {
        return SINGLETON;
    }

    /**
     * Initialize the new level of the game creating enemies and the character
     */
    public void initialize(final int levelNumber) {
        // This may create bugs.
        this.movableList.clear();
        this.stableList.clear();
        this.stableList.addAll(this.gameArena.getBoundsList());
        // The screen dimension should be passed by the controller (POSSIBLE BUG
        // FOR THE SCORE)
        this.johnnyCharacter = Optional.ofNullable(
                Factory.MainCharacterFactory.generateStillCharacter(this.getArena().getPlayableRectangle().getCenterX(),
                        this.getArena().getPlayableRectangle().getCenterY()));
        // Should be improved the monster generation
        for (int nMonsters = 0; nMonsters < levelNumber * 10; nMonsters++) {
            final Position randomPos = this.gameArena.getPositionInside();
            System.out.println(randomPos);
            final BasicMonster tmpMonster = Factory.EnemiesFactory.generateStillBasicEnemy(randomPos.getX(), randomPos.getY());
            if (this.getArena().isInside(tmpMonster)){
            	this.addMovableEntity(tmpMonster);
            }
        }
    }

    /**
     * Removes an entity from the list of the things to be drawed and updated.
     */
    protected void removeEntity(final Entity entity) {
        movableList.remove(entity);
        stableList.remove(entity);
    }

    /**
     * Updates all the positions of the {@link MovableEntity} in the lists.
     */
    public void updatePositions(final Direction newDirection, final boolean isShooting) {
        for (final MovableEntity currentE : movableList) {
            if (currentE instanceof Bullet) {
                ((Bullet) currentE).update();
            }
            if (currentE instanceof BasicMonster) {
                ((BasicMonster) currentE).update();
            }
        }
        this.updateHeroPos(newDirection, isShooting);
        this.removeDeadEntities();
    }
    
    private void removeDeadEntities(){
    	this.stableList.removeAll(this.stableList.stream().filter(x->x.isDead()).collect(Collectors.toList()));
        this.movableList.removeAll(this.movableList.stream().filter(x->x.isDead()).collect(Collectors.toList()));
    }

    /**
     * Updates the {@link MainCharacter} basing the new movement on the keys
     * pressed
     */
    protected void updateHeroPos(final Direction newDirection, final boolean isShooting) {
        johnnyCharacter.ifPresent(c -> c.update(newDirection, isShooting));
    }

    /**
     * Adds a {@link Bullet} to the MovableList of {@link MovableEntity}
     * 
     * @param newBullet
     */
    protected void addShoot(final Bullet newBullet) {
    	System.out.println("Projkrnegre");
        this.movableList.add(newBullet);
    }

    /**
     * Adds a {@link MovableEntity} to the movableList
     */
    protected void addMovableEntity(final MovableEntity newEntity) {
        this.movableList.add(newEntity);
    }

    /**
     * Kills the {@link MainCharacter}
     */
    // May create bugs with the Score
    protected void killMainChar() {
        this.johnnyCharacter = Optional.empty();
    }

    /**
     * Add to the stableList the stable entities like {@link HealthBonus} and
     * {@link Wall}
     */
    public void addStableEntity(final AbstractEntity newEntity) {
        this.stableList.add(newEntity);
    }

    /**
     * Gets a {@link List} of {@link AbstractEntity} that contains the
     * {@link Entity} that can't be moved
     */
    public List<AbstractEntity> getStableList() {
        return new ArrayList<>(this.stableList);
    }

    /**
     * Gets a {@link List} of {@link MovableEntity} that contains the
     * {@link Entity} that can be moved
     */
    public List<MovableEntity> getMovableList() {
        return new ArrayList<>(this.movableList);
    }

    /**
     * Returns the {@link Optional} of the main character
     */
    public Optional<MainCharacter> getMainChar() {
        return this.johnnyCharacter;
    }

    /**
     * Gets the {@link Arena} of the Game
     * 
     * @return
     */
    public Arena getArena() {
        return this.gameArena;
    }

    public boolean isGameEnded() {
        return this.movableList.isEmpty() || this.johnnyCharacter.isPresent() && 
                                             this.johnnyCharacter.get().isDead();
    }
}
