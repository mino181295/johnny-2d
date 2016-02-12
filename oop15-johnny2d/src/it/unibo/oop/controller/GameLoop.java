package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.NONE;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.KeysManager;
import it.unibo.oop.utilities.Pair;
import it.unibo.oop.view.Launcher;
import it.unibo.oop.view.Level;
import it.unibo.oop.view.OptionsMenu;
import it.unibo.oop.view.Showable;

/**
 * 
 * @author Paolo
 *
 *  class implementing the Controller of the MVC model.
 */
public class GameLoop implements Controller, StateObserver {

    private final static double FPS = 10;
    private final static int TO_SECONDS = 1000;
    private final static int SLEEPING_TIME = (int)(1/FPS * TO_SECONDS);
    private final Showable launcher;
    private final Showable options;
    private final Level level; /* da associarvi un'interfaccia */
    private volatile Pair<Direction, Direction> pgDir;
    private volatile boolean pgIsShooting;
    private final KeysManager<Direction> keysMan;
    
    public GameLoop() {
        this.launcher = new Launcher(this);
        this.options = new OptionsMenu();
        this.keysMan = new KeysManager<>();
        this.level = new Level(this.keysMan);
    }
    
    @Override
    public void start() {
        // this.launcher.showIt();
        this.doLoop();
    }
    
    /* GAME LOOP */
    private void doLoop() {
        while (true) {
            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /* valutare l'aggiunta di una wait per far eseguire eventuali eventi di key releasing (onde evitare che un tasto sia processato
             * più volte in caso di un tasso di FPS elevato)
             */
            this.processKeys();
            this.dbgKeysMan();        
            /* chiamo C passandogli la direzione del pg e l'azione (spara o no) */
            /* chiamo V e gli faccio disegnare il frame */
        }
    }
    
    private void processKeys() {
        this.pgIsShooting = this.keysMan.isAKeyPressed(KeyCommands.SPACE);
        this.pgDir = this.keysMan.getDirection();       
    }
    
    /* valutare incapsulamento della seguente logica in componente ad hoc */
    @Override 
    public void stateAction(final State state) {
        switch (state) {
        case PLAY:
            this.doLoop();
            break;
        case EXIT:
            System.exit(0);
            break;
        case OPTIONS:
            this.options.showIt();
            break;
        default:
        }
    }
    
    private void dbgKeysMan() {
        if (this.pgDir.getX() != NONE || this.pgDir.getY() != NONE) {
            System.out.println("Dir 1: " + this.pgDir.getX() + " Dir 2: " + this.pgDir.getY());
        }
        System.out.println(this.pgIsShooting ? "SHOOT!" : "");
    }
    
    public static void main(String[] args) {
        new GameLoop().start();
    }
}
