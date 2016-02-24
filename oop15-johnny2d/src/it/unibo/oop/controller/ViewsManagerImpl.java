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
public final class ViewsManagerImpl implements ViewsManager<LevelInterface, AppState> {

    private static Optional<ViewsManager<LevelInterface, AppState>> singleton = Optional.empty();
    private final LevelInterface level;
    private final MainFrame mainFrame; // class which contains all the menu-views.
    private List<AppState> history; // stack open-views.

    private ViewsManagerImpl() {
        this.history = new ArrayList<>();
        this.mainFrame = new MainFrameImpl();
        this.level = new Level(new KeyboardObserverImpl<>(MovementKey.class, MovementKeysManager.getInstance()),
                               new KeyboardObserverImpl<>(ActionKey.class, ActionKeysManager.getInstance()));
    }

    /**
     * @return
     *          the singleton instance of the class.
     */
    public static synchronized ViewsManager<LevelInterface, AppState> getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new ViewsManagerImpl());
        }
        return singleton.get();
    }

    @Override
    public synchronized void reset() { /* per evitare di sovraffollare inutilmente la history */ 
        this.history = new ArrayList<>();
    }
    
    @Override
    public LevelInterface getView() {
        return this.level;
    }

    @Override
    public synchronized void showView(final AppState state) {
        try {
            SwingUtilities.invokeAndWait(() -> this.mainFrame.changeView(state));
            if (!this.history.contains(state)) {
                this.history.add(state);
            }
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void hideView() {
        try {
            SwingUtilities.invokeAndWait(() -> this.mainFrame.setVisible(false));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void showLast() {
        final int lastIndex = this.history.size() - 1;
        if (lastIndex > 0) {
            this.history.remove(lastIndex); /* rimuovo la view che ha fatto "roll-back" per evitare loop */
            this.showView(this.history.get(lastIndex - 1)); /* mostro quella che la precedeva */
        }
    }
}