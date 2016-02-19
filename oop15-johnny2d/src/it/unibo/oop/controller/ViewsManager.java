package it.unibo.oop.controller;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.SwingUtilities;
import it.unibo.oop.view.Level;
import it.unibo.oop.view.LevelInterface;
import it.unibo.oop.view.MainFrame;
import it.unibo.oop.view.MainFrameImpl;

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

    private static Optional<ViewsManager> SINGLETON = Optional.empty();
    private final LevelInterface level;
    private final MainFrame mainFrame;
    private List<State> history;

    private ViewsManager() {
        this.history = new ArrayList<>();
        this.mainFrame = new MainFrameImpl();
        this.level = new Level(KeysManager.getInstance());
    }

    public synchronized static ViewsManager getInstance() {
        if (!SINGLETON.isPresent()) {
            SINGLETON = Optional.of(new ViewsManager());
        }
        return SINGLETON.get();
    }

    public LevelInterface getLevel() {
        return this.level;
    }

    @Override 
    public synchronized void stateAction(final State state) {
        state.doAction();
        if (state.isDrawable()) {
            this.showView(state); 
        }
    }

    public synchronized void showView(final State state) {
        try {
            SwingUtilities.invokeAndWait(() -> this.mainFrame.changeView(state));
            if (!this.history.contains(state)) {
                this.history.add(state);
            }
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void hideView() {
        try {
            SwingUtilities.invokeAndWait(() -> this.mainFrame.setVisible(false));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public synchronized void showLast() {
        final int lastIndex = this.history.size() - 1;
        this.history.remove(lastIndex); /* rimuovo la view che ha fatto "roll-back" per evitare loop */
        this.showView(this.history.get(lastIndex-1)); /* mostro quella che la precedeva */               
    }
    
    public synchronized void reset() { /* per evitare di sovraffollare la history */ 
        this.history = new ArrayList<>();
    }
}