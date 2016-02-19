package it.unibo.oop.controller;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import javax.swing.JPanel;
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

    private static ViewsManager SINGLETON;
    private final LevelInterface level;
    private final MainFrame mainFrame;
    private volatile Optional<State> prevState = Optional.empty();
    private volatile Optional<State> currState = Optional.empty();

    private ViewsManager() {
        this.mainFrame = new MainFrameImpl();
        this.level = new Level(KeysManager.getInstance());
    }

    public static ViewsManager getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new ViewsManager();
        }
        return SINGLETON;
    }

    public LevelInterface getLevel() {
        return this.level;
    }

    @Override 
    public synchronized void stateAction(final State state) {
        state.doAction();
        this.showView(state);
    }

    public synchronized void showView(final State state) {
        if (state.getView().isPresent()) {
            final JPanel view = state.getView().get();
            try {
                SwingUtilities.invokeAndWait(() -> this.mainFrame.setPanel(view));
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
}