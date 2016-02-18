package it.unibo.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.oop.utilities.Direction;

public class GameStateImpl implements GameState {

    private static final GameStateImpl SINGLETON = new GameStateImpl();
    private List<MovableEntity> movableList;
    private List<AbstractEntity> stableList;
    private Optional<MainCharacter> johnnyCharacter;
    
    private GameStateImpl() {
        this.movableList = new ArrayList<>();
        this.stableList = new ArrayList<>();
        johnnyCharacter = Optional.of(new MainCharacter());
     
    }
    
    public static GameStateImpl getInstance() {
        return SINGLETON;
    }
    
    protected void initialize(int levelNumber) {
    	//TODO
    }

    protected void removeEntity(Entity entity) {
    	movableList.remove(entity);
    	stableList.remove(entity);
    }

    public void updatePositions(final Direction newDirection, final boolean isShooting) {
    	for (MovableEntity currentE : movableList){
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

    protected void addShoot(Bullet newBullet) {
		this.movableList.add(newBullet);
	}
	
	protected void addMovableEntity(MovableEntity newEntity){
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
	
	public Optional<MainCharacter> getMainChar() {
		return this.johnnyCharacter;
	}
	
    protected void killMainChar(){
    	this.johnnyCharacter = Optional.empty();
    }
}
