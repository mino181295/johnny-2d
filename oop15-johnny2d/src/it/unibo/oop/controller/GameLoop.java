package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.NONE;

import java.util.Optional;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.KeysManager;
import it.unibo.oop.utilities.Pair;
import it.unibo.oop.view.MenuEnum;
import it.unibo.oop.view.ViewsManager;

/**
 * 
 * @author Paolo
 *
 *  class implementing the Controller of the MVC model.
 */
public class GameLoop implements Controller {

    private final static double FPS = 10;
    private final static int TO_SECONDS = 1000;
    private final static int SLEEPING_TIME = (int)(1/FPS * TO_SECONDS);
    private volatile Direction pgDir;
    private volatile boolean pgIsShooting;
    private volatile boolean loop;
    private final KeysManager keysMan;
    private final ViewsManager viewMan;
    private Optional<Thread> executor;
    
    public GameLoop() {
        this.executor = Optional.empty();
        this.viewMan = ViewsManager.getViewsManager();
        this.viewMan.setController(this);
        this.keysMan = KeysManager.getKeysManager();
        this.viewMan.show(MenuEnum.LAUNCHER); /* farlo fare all'EDT */
    }
    
    @Override
    public void start() {
        if (!this.executor.isPresent()) {
            this.executor = Optional.ofNullable(new Thread(()-> { /* attenzione a creazione di multi thread */
                this.loop = true;
                this.doLoop();
            }));
        }
        this.executor.get().start();
    }
    
    public void stop() {
        this.loop = false;
    }
    
    /* GAME LOOP */
    private void doLoop() {
        while (loop) {
            /* valutare l'aggiunta di una wait per far eseguire eventuali eventi di key releasing (onde evitare che un tasto sia processato
             * più volte in caso di un tasso di FPS elevato)
             */
            /* CHECK GIOCO FINITO/DA INIZIARE */
            this.processKeys();
            this.dbgKeysMan();        /* per debugging */
            /* chiamo C passandogli la direzione del pg e l'azione (spara o no) */
            /* chiamo V e gli faccio disegnare il frame */
            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void processKeys() {
        if (this.keysMan.isAKeyPressed(KeyCommands.ESC)) {
            this.viewMan.show(MenuEnum.PAUSE);
            this.stop(); /* forse meglio una wait */
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
    
    public static void main(String[] args) {
        new GameLoop();
    }
}
