package it.unibo.oop.controller;

import it.unibo.oop.view.Launcher;

/**
 * 
 * @author Paolo
 *
 *  class 
 */
public class GameLoop {

    private final Launcher launcher;
    
    public static void main(String[] args) {
        new GameLoop().start();

    }
    
    /**
     * 
     */
    public GameLoop() {
        this.launcher = new Launcher(this);
        
    }
    
    public void start() {
        this.launcher.showIt();
        this.doLoop();
    }
    
    private void doLoop() {
        
    }
    
    public void keySwitcher(final char c) {
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
}
