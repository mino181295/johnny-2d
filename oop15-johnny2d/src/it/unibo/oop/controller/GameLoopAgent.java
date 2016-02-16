package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.NONE;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.KeysManager;
import it.unibo.oop.view.ViewsManager;

public class GameLoopAgent implements Runnable {
    
    private final static double FPS = 10;
    private final static int TO_SECONDS = 1000;
    private final static int SLEEPING_TIME = (int)(1/FPS * TO_SECONDS);
    private final KeysManager keysMan = KeysManager.getInstance();
    private final ViewsManager viewsMan = ViewsManager.getInstance();
    private volatile Direction pgDir;
    private volatile boolean pgIsShooting;
    private volatile boolean loop;
    
    public GameLoopAgent() {
        this.loop = true;
    }
    
    public void start() {
        this.loop = true;
    }
    
    public void stop() {
        this.loop = false;
    }
    
    public boolean isPaused() {
        return !this.loop;
    }
    
    @Override
    public void run() {
        /* GAME LOOP */
        while (loop) {
            /* valutare l'aggiunta di una wait per far eseguire eventuali eventi di key releasing (onde evitare che un tasto sia processato
             * più volte in caso di un tasso di FPS elevato)
             */
            /* CHECK GIOCO FINITO/DA INIZIARE */
            this.processKeys();
            this.dbgKeysMan();        /* per debugging */
            /* chiamo C passandogli la direzione del pg e l'azione (spara o no) */
            /* chiamo V che si aggiorna e disegna frame*/
            this.viewsMan.getLevel().showIt();
            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
    }
    
    private void processKeys() {
        if (this.keysMan.isAKeyPressed(KeyCommands.ESC)) {
            this.viewsMan.stateAction(State.PAUSE);
        }
        this.pgIsShooting = this.keysMan.isAKeyPressed(KeyCommands.SPACE);
        this.pgDir = this.keysMan.getDirection(); 
    }
    
    /* per debug */
    private void dbgKeysMan() {
        if (this.pgDir != NONE) {
            System.out.println("Dir 1: " + this.pgDir);
        }
        System.out.println(this.pgIsShooting ? "SHOOT!" : "");
    }
}