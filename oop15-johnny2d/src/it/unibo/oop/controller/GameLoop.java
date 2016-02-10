package it.unibo.oop.controller;

import it.unibo.oop.view.Launcher;
import it.unibo.oop.view.Level;
import it.unibo.oop.view.Showable;

/**
 * 
 * @author Paolo
 *
 *  class 
 */
public class GameLoop implements Controller, KeyboardObserver {

    private final Showable launcher;
 //   private final Level level; /* da associarvi un'interfaccia */
    
    public GameLoop() {
        this.launcher = new Launcher();
    //    this.level = new Level(this);
    }
    
    @Override
    public void start() {
        this.launcher.showIt();
        this.doLoop();
    }
    
    @Override
    public void processKey(final char c) {
        switch (c) {
        case 'w':
            System.out.println("mosso in alto");
            break;
        case 'a':
            System.out.println("mosso a sx");
            break;
        case 's':
            System.out.println("mosso in basso");
            break;
        case 'd':
            System.out.println("mosso a dx");
            break;
        default:
            System.out.println("none");
        }
    }
    
    private void doLoop() {
        
    }
    
    public static void main(String[] args) {
        new GameLoop(); //.start();

    }
}
