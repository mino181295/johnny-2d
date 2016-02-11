package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.NONE;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.KeysManager;
import it.unibo.oop.view.Launcher;
import it.unibo.oop.view.Level;
import it.unibo.oop.view.OptionsMenu;
import it.unibo.oop.view.Showable;

/**
 * 
 * @author Paolo
 *
 *  class 
 */
public class GameLoop implements Controller, StateObserver {

    private final static int FRAMES_PER_SECOND = 60;
    private final static int SLEEPING_TIME = 1/FRAMES_PER_SECOND;
    private final Showable launcher;
    private final Showable options;
    private final Level level; /* da associarvi un'interfaccia */
    
    private volatile Direction pgDir = NONE;
    private volatile boolean pgIsShooting = false;
    private final KeysManager<Direction> keysMan;
    
    public GameLoop() {
        this.launcher = new Launcher(this);
        this.level = new Level(this.keysMan);
        this.options = new OptionsMenu();
        this.keysMan = new KeysManager<>();
    }
    
    @Override
    public void start() {
        // this.launcher.showIt();
        this.doLoop();
    }
    
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
    
    private void processKeys() {
        synchronized(this) { /* per proteggere pgDir e pdIsShooting */
            this.pgIsShooting = this.keysMan.isAKeyPressed(KeyEvent.VK_SPACE);
            if (this.pgDir == NONE) {
                this.pgDir = this.keysMan.getDirection();
            }
        }
    }

    /* GAME LOOP */
    private void doLoop() {
        while (true) {
            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            synchronized(this) {    /* per proteggere pgDir e pdIsShoting */
              //  System.out.println(this.keys);
                this.processKeys();
                /* chiamo C passandogli la direzione del pg e l'azione (spara o no) */
                this.pgDir = Direction.NONE; /* dopo l'utilizzo reinizializzo tutto */
                this.pgIsShooting = false;
            }
            /* chiamo V e gli faccio disegnare il frame */
        }
    }
    
    public static void main(String[] args) {
        new GameLoop().start();

    }
}
