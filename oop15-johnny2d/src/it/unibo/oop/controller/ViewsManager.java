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
 * class which manages all the game views.
 */


public final class ViewsManager implements StateObserver {

    private static Optional<ViewsManager> singleton = Optional.empty();
    private final LevelInterface level;
    private final MainFrame mainFrame; // class which contains all the menu-views.
    private List<State> history; // stack view aperte.

    private ViewsManager() {
        this.history = new ArrayList<>();
        this.mainFrame = new MainFrameImpl();
        this.level = new Level(KeysManager.getInstance());
    }

    /**
     * @return
     *          the singleton instance of the class.
     */
    public static synchronized ViewsManager getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new ViewsManager());
        }
        return singleton.get();
    }

    public LevelInterface getLevel() {
        return this.level;
    }

    @Override 
    public synchronized void stateAction(final State state) {
        state.doAction();
        // this.doStateAction(state);
        if (state.isDrawable()) {
            this.showView(state); 
        }
    }

//    private void doStateAction(final State state) {
//
//    }

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

    public synchronized void hideView() {
        try {
            SwingUtilities.invokeAndWait(() -> this.mainFrame.setVisible(false));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public synchronized void showLast() {
        final int lastIndex = this.history.size() - 1;
        this.history.remove(lastIndex); /* rimuovo la view che ha fatto "roll-back" per evitare loop */
        this.showView(this.history.get(lastIndex - 1)); /* mostro quella che la precedeva */
    }

    public synchronized void reset() { /* per evitare di sovraffollare inutilmente la history */ 
        this.history = new ArrayList<>();
    }
}