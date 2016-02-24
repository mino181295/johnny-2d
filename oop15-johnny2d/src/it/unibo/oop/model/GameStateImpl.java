package it.unibo.oop.model;

import static it.unibo.oop.utilities.Settings.SCREEN_HEIGHT;
import static it.unibo.oop.utilities.Settings.SCREEN_WIDTH;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibo.oop.controller.ControllerImpl;
import it.unibo.oop.utilities.CharactersSettings;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;

public final class GameStateImpl implements GameState {
	
	private static final int BASIC_DEFAULT = 15;
	private static final int BASIC_SCALE = 3;
	
	private static final int INVISIBLE_DEFAULT = 3;
	
	private static final int COLLECTIBLES_DELAY = 120;
	private static final int MONSTERS_DELAY = 130;
	
	private int randomCollectiblesDelay = COLLECTIBLES_DELAY;
	private int randomMonstersDelay = MONSTERS_DELAY;

	private static final GameStateImpl SINGLETON = new GameStateImpl();
	private final List<MovableEntity> movableList;
	private final List<AbstractEntity> stableList;
	private Optional<MainCharacter> johnnyCharacter;
	private final Arena gameArena;
	
	private long updatesNumber;
	private long lastShotFrame;

	private GameStateImpl() {
		this.updatesNumber = 0;
		this.lastShotFrame = 0;
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
        this.johnnyCharacter = Optional.ofNullable(
                Factory.MainCharacterFactory.generateStillCharacter(this.getArena().getPlayableRectangle().getCenterX(),
                        this.getArena().getPlayableRectangle().getCenterY()));
        this.spawnBasicMonsters(BASIC_DEFAULT);
        this.spawnInvisibleMonsters(INVISIBLE_DEFAULT);
    }

	private void spawnBasicMonsters(final int number){
		BasicMonster tmpMonster;
		Position randomPos;
		long monsterConfilicts;
		boolean distanceCondition;
		
		for (int nMonsters = 0; nMonsters < number; nMonsters++) {
			do {
				randomPos  = this.gameArena.getPositionInside(CharactersSettings.BASIC_ENEMY);
				tmpMonster = Factory.EnemiesFactory.generateStillBasicEnemy(randomPos.getX(), randomPos.getY());
				final BasicMonster finalMonster = tmpMonster;
				monsterConfilicts = this.movableList.stream().filter(x -> x.intersecate(finalMonster))
						.filter(x -> x instanceof AbstractEnemy).count();
				distanceCondition = Position.pointsDistance(this.getMainChar().get().getPosition(), tmpMonster.getPosition())>400?false:true;
			} while (monsterConfilicts != 0 || distanceCondition);
			this.addMovableEntity(tmpMonster);
		}
	}
	
	private void spawnInvisibleMonsters(final int number){
		InvisibleMonster tmpMonster;
		Position randomPos;
		long monsterConfilicts;
		boolean distanceCondition;
		
		for (int nMonsters = 0; nMonsters < number; nMonsters++) {
			do {
				randomPos  = this.gameArena.getPositionInside(CharactersSettings.INVISIBLE_ENEMY);
				tmpMonster = Factory.EnemiesFactory.generateStillInvisibleEnemy(randomPos.getX(), randomPos.getY());
				final InvisibleMonster finalMonster = tmpMonster;
				monsterConfilicts = this.movableList.stream().filter(x -> x.intersecate(finalMonster))
						.filter(x -> x instanceof AbstractEnemy).count();
				distanceCondition = Position.pointsDistance(this.getMainChar().get().getPosition(), tmpMonster.getPosition())>700?false:true;
			} while (monsterConfilicts != 0 || distanceCondition);
			this.addMovableEntity(tmpMonster);
		}
	}
	
	private void spawnRandomHealthCollectable(){
		final Position randomPos = this.getArena().getPositionInside(CharactersSettings.BONUS);
		this.addStableEntity(new HealthBonus(randomPos.getX(), randomPos.getY()));		
	}
	
	private void spawnRandomScoreCollectable(){
		final Position randomPos = this.getArena().getPositionInside(CharactersSettings.BONUS);
		this.addStableEntity(new ScoreBonus(randomPos.getX(), randomPos.getY()));		
	}

    private void removeDeadEntities() {
        this.stableList.removeAll(this.stableList.stream().filter(x -> x.isDead()).collect(Collectors.toList()));
        this.movableList.removeAll(this.movableList.stream().filter(x -> x.isDead()).collect(Collectors.toList()));
    }

	/**
	 * Updates all the positions of the {@link MovableEntity} in the lists.
	 */
	public void updatePositions(final Direction newDirection, final boolean isShooting) {
		this.updatesNumber ++;
		movableList.stream().forEach(x -> x.update());
		this.updateHeroPos(newDirection, isShooting);
		this.removeDeadEntities();
		
		if (this.updatesNumber % (COLLECTIBLES_DELAY+randomCollectiblesDelay) == 0){
			randomCollectiblesDelay = new Random().nextInt(COLLECTIBLES_DELAY);
			if (new Random().nextInt(3) == 0){
				this.spawnRandomHealthCollectable();
			} else {
				this.spawnRandomScoreCollectable();
			}
		}
		if (this.updatesNumber % (MONSTERS_DELAY+randomMonstersDelay) == 0){
			randomMonstersDelay = new Random().nextInt(MONSTERS_DELAY);
			this.spawnBasicMonsters(BASIC_SCALE);
		} 
	}

    /**
     * Adds a {@link Bullet} to the MovableList of {@link MovableEntity}
     * 
     * @param newBullet
     */
    protected void addShoot(final Bullet newBullet) {
    	long deltaTime = this.updatesNumber - this.lastShotFrame;   	
    	if (deltaTime >= 6){
    		this.lastShotFrame = this.updatesNumber;
    		this.movableList.add(newBullet);
    	}
    }

	/**
	 * Updates the {@link MainCharacter} basing the new movement on the keys
	 * pressed
	 */
	protected void updateHeroPos(final Direction newDirection, final boolean isShooting) {
		
		johnnyCharacter.ifPresent(c -> {
											c.setInput(newDirection, isShooting);
											c.update();
										});
	}
	
	/**
	 * Adds a {@link MovableEntity} to the movableList
	 */
	protected void addMovableEntity(final MovableEntity newEntity) {
		this.movableList.add(newEntity);
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
     * Gets the {@link Arena} of the Game
     * 
     * @return
     */
    public Arena getArena() {
        return this.gameArena;
    }
    
    public void checkTopScore() {
        final Score score = this.johnnyCharacter.get().getScore();
        if (score.compareTo(ControllerImpl.getInstance().getStatFromFile()) >= 0) {
            ControllerImpl.getInstance().putStatToFile(score);
        }
    }

	/**
	 * Returns the {@link Optional} of the main character
	 */
	public Optional<MainCharacter> getMainChar() {
		return this.johnnyCharacter;
	}

	public boolean isGameEnded() {
		final boolean noneEnemy = this.movableList.stream()
				.filter(e -> e instanceof Enemy)
				.collect(Collectors.toList())
				.isEmpty();
		return noneEnemy || this.johnnyCharacter.isPresent() && 
				this.johnnyCharacter.get().isDead();
	}
}