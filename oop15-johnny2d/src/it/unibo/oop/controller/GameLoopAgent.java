package it.unibo.oop.controller;

import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.view.LevelInterface;

/**
 * Agent used by {@link ControllerImpl} to perform the game loop.
 */
public class GameLoopAgent implements AgentInterface {

    private static final double FPS = 30;
    private static final int TO_SECONDS = 1000;
    private static final int SLEEPING_TIME = (int) (1 / FPS * TO_SECONDS);
    private final KeysManager<KeyCommands, Direction> keysMan = KeysManagerImpl.getInstance();
    private final ViewsManager<LevelInterface, AppState> viewsMan = ViewsManagerImpl.getInstance();
    private final StateObserver stateObs;
    private volatile Direction mainCharDir;
    private volatile boolean isMainCharShooting;
    private volatile boolean pause;
    private volatile boolean gameOver;

    /**
     * Class's constructor.
     */
    public GameLoopAgent() {
        this.pause = false;
        this.stateObs = new StateObserverImpl(this.viewsMan);
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
            GameStateImpl.getInstance().updatePositions(this.mainCharDir, this.isMainCharShooting);
            
            /* CHECK GIOCO FINITO */
            this.gameOver = GameStateImpl.getInstance().isGameEnded();

            /* AGGIORNAMENTO PRINT DEL FRAME */
            this.viewsMan.getView().updateLevel();
            this.viewsMan.getView().showIt();
            
            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKeys() {
        final ActionKey action = ActionKeysManager.getInstance().processKeys(); 
        this.pause = action == ActionKey.PAUSE;
        this.isMainCharShooting = action == ActionKey.SHOOT;
        this.mainCharDir = KeysManagerImpl.getInstance().processKeys(); // rimuovo le KeysTyped.
    }

    /* per debugging */
    private void dbgKeysMan() {
        if (this.mainCharDir != Direction.NONE) {
            System.out.println("Dir : " + this.mainCharDir);
        }
        System.out.println(this.isMainCharShooting ? "SHOOT!" : "");
    }
}