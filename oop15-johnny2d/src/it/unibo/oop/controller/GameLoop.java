package it.unibo.oop.controller;

import java.util.Optional;

/**
 * 
 * @author Paolo
 *
 *  class implementing the Controller of the MVC model.
 */
public class GameLoop implements Controller {

    private static final GameLoop SINGLETON = new GameLoop();
    private Optional<AgentInterface> gLAgent = Optional.empty();

    private GameLoop() {
        ViewsManager.getInstance().showView(State.LAUNCHING);
    }

    public static Controller getInstance() {
        return SINGLETON;
    }

    @Override
    public void start() { // launcher -> play / pause -> replay
        // GameStateImpl.getInstance().initialize(0); deve implicare l'inizializzazione anche della view Level.
        KeysManager.getInstance().reset();
        this.play();
    }

    @Override
    public void play() { // pause -> play  
        ViewsManager.getInstance().hideView();
        if (!this.gLAgent.isPresent()) {
            this.gLAgent = Optional.ofNullable(new GameLoopAgent());
            new Thread(this.gLAgent.get()).start();
        } else {
            this.gLAgent.get().play(); 
        }
    }

//    public double getStatFromFile() {
//
//    }
//
//    public double addStatToFile() {
//
//    }

    public static void main(String[] args) {
        new GameLoop();
    }
}