package it.unibo.oop.controller;

import it.unibo.oop.view.Launcher;

public class GameLoop {

    private final Launcher launcher;
    
    public static void main(String[] args) {
        new GameLoop().start();

    }
    
    public GameLoop() {
        this.launcher = new Launcher();
        
    }
    
    public void start() {
        this.launcher.showIt();
        this.doLoop();
    }
    
    private void doLoop() {
        
    }
}
