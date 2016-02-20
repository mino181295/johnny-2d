package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.NONE;

import it.unibo.oop.utilities.Direction;

/**
 * Agent used by {@link GameLoop} to perform the game loop.
 */
public class GameLoopAgent implements AgentInterface {

    private static final double FPS = 10;
    private static final int TO_SECONDS = 1000;
    private static final int SLEEPING_TIME = (int) (1 / FPS * TO_SECONDS);
    private final KeysManager keysMan = KeysManager.getInstance();
    private final ViewsManager viewsMan = ViewsManager.getInstance();
    private volatile Direction mainCharDir;
    private volatile boolean isMainCharShooting;
    private volatile boolean pause; /* default false */

    /**
     * Class's constructor.
     */
    public GameLoopAgent() {
        this.pause = false;
    }

    @Override
    public synchronized void play() {
        this.pause = false;
        this.notify();
    }

    @Override
    public synchronized void run() {

        /* GAME LOOP */
        while (true) {
            while (this.pause) {
                try {
                    this.viewsMan.stateAction(State.PAUSE);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /*
             * valutare l'aggiunta di un ulteriore wait per far eseguire
             * eventuali eventi di key releasing (onde evitare che un tasto sia
             * processato più volte in caso di un tasso di FPS elevato)
             */

            /* CHECK GIOCO FINITO/DA INIZIARE */
            this.processKeys();
            this.dbgKeysMan(); /* per debugging */
            // GameStateImpl.getInstance().updatePositions(this.mainCharDir,
            // this.isMainCharShooting);
            /* chiamo V che si aggiorna e disegna frame */
            this.viewsMan.getLevel().updateLevel();
            this.viewsMan.getLevel().showIt();
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
        this.mainCharDir = this.keysMan.getDirection(); // rimuovo le KeysTyped.
    }

    /* per debugging */
    private void dbgKeysMan() {
        if (this.mainCharDir != NONE) {
            System.out.println("Dir : " + this.mainCharDir);
        }
        System.out.println(this.isMainCharShooting ? "SHOOT!" : "");
    }
}