package it.unibo.oop.view;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.utilities.KeysManager;

/**
 * 
 * @author Paolo
 *
 * class which manages all the game views.
 */

/*
 *  NOTE: aggiungere contatore schede aperte per corretta terminazione app o soluzione analoga. 
 */

public class ViewsManager implements StateObserver {

    private static final ViewsManager SINGLETON = new ViewsManager();
    private final LevelInterface level;
    
    private ViewsManager() {
        this.level = new Level(KeysManager.getInstance());
        level.addObserver(KeysManager.getInstance());     
    }
    
    public static ViewsManager getInstance() {
        return SINGLETON;
    }
    
    public LevelInterface getLevel() {
        return this.level;
    }
    
    public void showView(final State state) {
        try {
            SwingUtilities.invokeAndWait(() -> state.getView().ifPresent(Showable::showIt));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public void hideView(final State state) {
        try {
            SwingUtilities.invokeAndWait(() -> state.getView().ifPresent(Showable::hideIt));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }   
    }
    
    @Override 
    public void stateAction(final State state) {
        this.hideAll();
        state.doAction();
        this.showView(state);
    }
    
    private void hideAll() {
        for (final State stateView: State.values()) {
            this.hideView(stateView);
        }
    }

}
