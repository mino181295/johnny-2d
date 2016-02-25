package it.unibo.oop.controller;

import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.view.LevelInterface;
import it.unibo.oop.view.ViewsManager;

/**
 * Class implementing {@link StateObserver} interface.
 */
public class StateObserverImpl implements StateObserver {

    private final ViewsManager<LevelInterface, AppState> man;
    
    /**
     * @param man
     */
    public StateObserverImpl(final ViewsManager<LevelInterface, AppState> man) {
        this.man = man;
    }
    
    @Override
    public void stateAction(final AppState state) {
        synchronized (this.man) {
            this.doStateAction(state);
            if (state.isDrawable()) {
                this.man.showView(state);
            }
        }
    }

    private void doStateAction(final AppState state) {
        switch (state) {
        case START:
            ControllerImpl.getInstance().start();
            break;
        case PLAY:
            ControllerImpl.getInstance().play();
            break;
        case PAUSE:
            this.man.reset();
            break;
        case BACK:
            this.man.showLast();
            break;
        case GAME_OVER:
            GameStateImpl.getInstance().checkTopScore();
            break;    
        case EXIT:
            System.exit(0);
            break;
        default:
        }
    }
}