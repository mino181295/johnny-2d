package it.unibo.oop.controller;

import it.unibo.oop.model.GameState;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.view.View;
import it.unibo.oop.view.ViewImpl;
import it.unibo.oop.view.keyboard.ActionKey;

/**
 * Agent used by {@link ControllerImpl} to perform the game loop.
 */
public class GameLoopAgent implements AgentInterface {

    private static final double FPS = 30;
    private static final int TO_SECONDS = 1000;
    private static final int SLEEPING_TIME = (int) (1 / FPS * TO_SECONDS);
    private final GameState gameState = GameStateImpl.getInstance();
    private final View view = ViewImpl.getInstance();
    private final StateObserver stateObs;
    private volatile Direction mainCharDir;
    private volatile boolean isMainCharShooting;
    private volatile boolean pause;
    private volatile boolean gameOver;

    /**
     * Class's constructor.
     */
    public GameLoopAgent() {
        this.stateObs = new StateObserverImpl(this.view);
    }

    @Override
    public synchronized void play() {
        this.pause = false;
        this.gameOver = false;
        this.notifyAll();
    }

    @Override
    public synchronized void run() {

        /* GAME LOOP */
        while (true) {
            while (this.pause || this.gameOver) {
                try {
                    if (this.pause) {
                        this.stateObs.stateAction(AppState.PAUSE);
                    } else {
                        this.stateObs.stateAction(AppState.GAME_OVER);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /* ACQUISIZIONE TASTI PREMUTI */
            this.processKeys();
            this.dbgKeysMan(); /* per debugging */
            
            /* AGGIORNAMENTO GAMESTATE */
            this.gameState.updatePositions(this.mainCharDir, this.isMainCharShooting);
            
            /* CHECK GIOCO FINITO */
            this.gameOver = this.gameState.isGameEnded();

            /* AGGIORNAMENTO E PRINTING DEL FRAME */
            this.view.getLevelView().updateLevel();
            this.view.getLevelView().showIt();
            
            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKeys() {
        final ActionKey action = this.view.getACtionKeysManager().processKeys(); 
        this.pause = action == ActionKey.PAUSE;
        this.isMainCharShooting = action == ActionKey.SHOOT;
        this.mainCharDir = this.view.getMovementKeysManager().processKeys();
    }

    /* per debugging */
    private void dbgKeysMan() {
        if (this.mainCharDir != Direction.NONE) {
            System.out.println("Dir : " + this.mainCharDir);
        }
        System.out.println(this.isMainCharShooting ? "SHOOT!" : "");
    }
}