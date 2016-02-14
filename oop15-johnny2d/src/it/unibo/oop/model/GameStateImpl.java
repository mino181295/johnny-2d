package it.unibo.oop.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Direction;

public class GameStateImpl implements GameState {

    private static final GameState SINGLETON = new GameStateImpl();
    private List<MovableEntity> entyList;
    private List<AbstractEntity> absEntyList;
    private final MainCharacter johnny;
    
    private GameStateImpl() {
        this.entyList = new ArrayList<>();
        this.absEntyList = new ArrayList<>();
     
    }
    
    public static GameState getInstance() {
        return SINGLETON;
    }
    
    @Override
    public void initialize(int level) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeEntity(Entity entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePositions() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateHeroPos(final Direction d) {
        johnny.
    }
    
//    @Override
//    public void updateHeroPos(final Direction d) {
//            
//    }
    

}
