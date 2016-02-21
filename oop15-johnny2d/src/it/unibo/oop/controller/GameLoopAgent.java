package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.NONE;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.view.LevelInterface;

/**
 * Agent used by {@link ControllerImpl} to perform the game loop.
 */
public class GameLoopAgent implements AgentInterface {

    private static final double FPS = 10;
    private static final int TO_SECONDS = 1000;
    private static final int SLEEPING_TIME = (int) (1 / FPS * TO_SECONDS);
    private final KeysManager<KeyCommands, Direction> keysMan = KeysManagerImpl.getInstance();
    private final ViewsManager<LevelInterface, AppState> viewsMan = ViewsManagerImpl.getInstance();
    private final StateObserver stateObs;
    private volatile Direction mainCharDir;
    private volatile boolean isMainCharShooting;
    private volatile boolean pause; /* default false */

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
        this.notifyAll();
    }

    @Override
    public synchronized void run() {

        /* GAME LOOP */
        while (true) {
            while (this.pause) {
                try {
                    this.stateObs.stateAction(AppState.PAUSE);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /*
             * valutare l'aggiunta di un ulteriore wait per far eseguire
             * eventuali eventi di key releasing (onde evitare che un tasto sia
             * processato pi� volte in caso di un tasso di FPS elevato)
             */

            /* CHECK GIOCO FINITO/DA INIZIARE */
            this.processKeys();
            this.dbgKeysMan(); /* per debugging */
            // GameStateImpl.getInstance().updatePositions(this.mainCharDir,
            // this.isMainCharShooting);
            /* chiamo V che si aggiorna e disegna frame */
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
        this.pause = this.keysMan.isAKeyPressed(KeyCommands.ESC);
        this.isMainCharShooting = this.keysMan.isAKeyPressed(KeyCommands.SPACE);
        this.mainCharDir = this.keysMan.processKeys(); // rimuovo le KeysTyped.
    }

    /* per debugging */
    private void dbgKeysMan() {
        if (this.mainCharDir != NONE) {
            System.out.println("Dir : " + this.mainCharDir);
        }
        System.out.println(this.isMainCharShooting ? "SHOOT!" : "");
    }
}