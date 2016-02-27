package it.unibo.oop.controller;

import java.io.IOException;

import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.view.View;

/**
 * Class implementing {@link StateObserver} interface.
 */
public class StateObserverImpl implements StateObserver {

    private final View view;

    /**
     * @param man
     */
    public StateObserverImpl(final View view) {
        this.view = view;
    }

    @Override
    public void stateAction(final AppState state) {
        synchronized (this.view) {
            this.doStateAction(state);
            if (state.isDrawable()) {
                this.view.showView(state);
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
        case BACK:
            this.view.showLast();
            break;
        case GAME_OVER:
            GameStateImpl.getInstance().checkTopScore();
            break;
        case EXIT:
            try {
                ControllerImpl.getInstance().putStatToFile();
            } catch (IOException e) {
                System.out.println("Error in file writing.");
            }
            System.exit(0);
            break;
        default:
        }
    }
}