package it.unibo.oop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

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
 *  NOTE: aggiungere stack schede aperte per corretta terminazione app o soluzione analoga. 
 */

public class ViewsManager implements StateObserver {

    private static final ViewsManager SINGLETON = new ViewsManager();
    private final LevelInterface level;
    private Optional<State> prevState = Optional.empty();
    private Optional<State> currState = Optional.empty();
    
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
    
    @Override 
    public void stateAction(final State state) {
        this.hideAll();
        state.doAction();
        this.showView(state);
    }
    
    public void showView(final State state) {
        if (state.getView().isPresent()) {
            final Showable view = state.getView().get();
            try {
                SwingUtilities.invokeAndWait(() -> view.showIt());
                prevState = currState;
                currState = Optional.of(state);
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void showLast() {
        this.showView(this.prevState.get());
    }
    
    public void hideView(final State state) {
        if (state.getView().isPresent()) {
            final Showable view = state.getView().get();
            try {
                SwingUtilities.invokeAndWait(() -> view.hideIt());
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void hideAll() {
        for (final State stateView: State.values()) {
            this.hideView(stateView);
        }
    }

}
