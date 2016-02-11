package it.unibo.oop.controller;

import static it.unibo.oop.utilities.Direction.DOWN;
import static it.unibo.oop.utilities.Direction.NONE;
import static it.unibo.oop.utilities.Direction.RIGHT;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Direction;
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
public class GameLoop implements Controller, KeyboardObserver, StateObserver {

    private final static int FRAMES_PER_SECOND = 60;
    private final static int SLEEPING_TIME = 1/FRAMES_PER_SECOND;
    private final Showable launcher;
    private final Showable options;
    private final Level level; /* da associarvi un'interfaccia */
    
    private volatile Direction pgDir = NONE;
    private volatile boolean pgIsShooting = false;
    private final List<Integer> keys;
    
    public GameLoop() {
        this.launcher = new Launcher(this);
        this.level = new Level(this);
        this.options = new OptionsMenu();
        this.keys = new ArrayList<>();
    }
    
    @Override
    public void start() {
        // this.launcher.showIt();
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
    
    @Override
    public synchronized void keyPressed(final int keyCode) {
        if (!this.keys.contains(keyCode)) {
            this.keys.add(keyCode);
        }
    }
    
    @Override
    public synchronized void keyReleased(final int keyCode) {
//        if (this.keys.contains(keyCode)) {
//            this.keys.remove(keyCode);
//        }
    }
    
    private void processKeys() {
        synchronized(this) { /* per proteggere pgDir e pdState */
            if (this.pgDir == NONE) { /* ---> implemento una sorta di invokeAndWait */
                switch (keyCode) { /* switch for a pg move */
                case KeyEvent.VK_W:
                    System.out.println("mosso in alto");
                    this.pgDir = Direction.UP;
                    break;
                case KeyEvent.VK_A:
                    System.out.println("mosso a sx");
                    this.pgDir = Direction.LEFT;
                    break;
                case KeyEvent.VK_S:
                    System.out.println("mosso in basso");
                    this.pgDir = DOWN;
                    break;
                case KeyEvent.VK_D:
                    System.out.println("mosso a dx");
                    this.pgDir = RIGHT;
                    break;
                default:
                }
            }
            switch (keyCode) { 
            case KeyEvent.VK_ESCAPE:
                System.out.println("show menu");
                this.launcher.showIt(); /* da sostituire col menu di pausa */
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("shoot");
                this.pgIsShooting = true;
                break;
            default:
            }
        }    
    }
    
    private void doLoop() {
        try {
            Thread.sleep(SLEEPING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized(this) {    /* per proteggere pgDir e pdState */
        
            this.processKeys();
            /* chiamo C passandogli la direzione del pg e l'azione (spara o no) */
            this.pgDir = Direction.NONE; /* dopo l'utilizzo reinizializzo tutto */
            this.pgIsShooting = false;
        }
        /* chiamo V e gli faccio disegnare il frame */
    }
    
    public static void main(String[] args) {
        new GameLoop().start();

    }
}
