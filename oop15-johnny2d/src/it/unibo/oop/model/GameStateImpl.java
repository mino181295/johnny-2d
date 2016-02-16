package it.unibo.oop.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Direction;

public class GameStateImpl implements GameState {

    private static final GameState SINGLETON = new GameStateImpl();
    private List<MovableEntity> movableList;
    private List<AbstractEntity> stableList;
    private final MainCharacter johnnyCharacter;
    
    private GameStateImpl() {
        this.movableList = new ArrayList<>();
        this.stableList = new ArrayList<>();
        johnnyCharacter = new MainCharacter();
     
    }
    
    public static GameState getInstance() {
        return SINGLETON;
    }
    
    @Override
    public void initialize(int levelNumber) {
    }

    @Override
    public void removeEntity(Entity entity) {
    }

    @Override
    public void updatePositions() {
    	for (MovableEntity currentE : movableList){
    		if (currentE instanceof Bullet){
    			((Bullet) currentE).update();
    		}
    		if (currentE instanceof Enemy){
    			((Bullet) currentE).update();
    		}
    	}
    	//this.updateHeroPos(newDirection, isShooting);
    }

    public void updateHeroPos(final Direction newDirection, final boolean isShooting ) {
        johnnyCharacter.update(newDirection, isShooting);
    }

	public void addShoot(Bullet newBullet) {
		this.movableList.add(newBullet);
	}
	
	public void addMovableEntity(MovableEntity newEntity){
		this.movableList.add(newEntity);
	}
	
	public void addStableEntity(AbstractEntity newEntity){
		this.stableList.add(newEntity);
	}

	public List<AbstractEntity> getStableList() {
		return new ArrayList<>(this.stableList);
	}
	
	public List<AbstractEntity> getMovableList() {
		return new ArrayList<>(this.movableList);
	}
	
	public MainCharacter getMainChar() {
		return this.johnnyCharacter;
	}
    
}
