package it.unibo.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import static it.unibo.oop.utilities.Settings.*;

public final class GameStateImpl implements GameState {

    private static final GameStateImpl SINGLETON = new GameStateImpl();
    private final List<MovableEntity> movableList;
    private final List<AbstractEntity> stableList;
    private Optional<MainCharacter> johnnyCharacter;
    private Arena gameArena;
    
    private GameStateImpl() {
        this.movableList = new ArrayList<>();
        this.stableList = new ArrayList<>();
        this.johnnyCharacter = Optional.of(new MainCharacter());
        this.gameArena= Factory.WallFactory.generateArena(SCREEN_HEIGHT, SCREEN_WIDTH);
    }
    
    public static GameStateImpl getInstance() {
        return SINGLETON;
    }
    
    protected void initialize(final int levelNumber) {
    	
    	//TODO Migliorare la creazione delle posizioni  
    	this.johnnyCharacter = Optional.ofNullable(Factory.MainCharacterFactory.generateCentredCharacter(new Position(this.gameArena.getPlayableRectangle().getCenterX(),this.gameArena.getPlayableRectangle().getCenterY())));
    	for (int nMonsters = 0; nMonsters < levelNumber*10; nMonsters++){
    		//MonsterGeneration
    	}
    	    
    }

    protected void removeEntity(final Entity entity) {
    	movableList.remove(entity);
    	stableList.remove(entity);
    }

    public void updatePositions(final Direction newDirection, final boolean isShooting) {
    	for (final MovableEntity currentE : movableList){
    		if (currentE instanceof Bullet){
    			((Bullet) currentE).update();
    		}
    		if (currentE instanceof BasicMonster){
    			((BasicMonster) currentE).update();
    		}
    	}
    	this.updateHeroPos(newDirection, isShooting);
    }

    protected void updateHeroPos(final Direction newDirection, final boolean isShooting ) {
        johnnyCharacter.ifPresent(c -> c.update(newDirection, isShooting));
    }

    protected void addShoot(final Bullet newBullet) {
		this.movableList.add(newBullet);
	}
	
	protected void addMovableEntity(final MovableEntity newEntity){
		this.movableList.add(newEntity);
	}
	
	public void addStableEntity(final AbstractEntity newEntity){
		this.stableList.add(newEntity);
	}

	public List<AbstractEntity> getStableList() {
		return new ArrayList<>(this.stableList);
	}
	
	public List<AbstractEntity> getMovableList() {
		return new ArrayList<>(this.movableList);
	}
	
	public Optional<MainCharacter> getMainChar() {
		return this.johnnyCharacter;
	}
	
    protected void killMainChar(){
    	this.johnnyCharacter = Optional.empty();
    }
}
