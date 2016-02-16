package it.unibo.oop.controller;

import java.util.Optional;

import it.unibo.oop.utilities.KeysManager;
import it.unibo.oop.view.ViewsManager;

/**
 * 
 * @author Paolo
 *
 *  class implementing the Controller of the MVC model.
 */
public class GameLoop implements Controller {

    private static final GameLoop SINGLETON = new GameLoop();
    private Optional<GameLoopAgent> gLAgent = Optional.empty();

    private GameLoop() {
        ViewsManager.getInstance().showView(State.LAUNCHING);
    }
    
    public static GameLoop getInstance() {
        return SINGLETON;
    }

    @Override
    public void start() { // launcher -> play / pause -> replay
        // GameStateImpl.getInstance().initialize(0);
        KeysManager.getInstance().reset();
        this.play();
    }

    // generando sempre un nuovo thread anziché usare wait e signal ottengo una gestione più semplice 
    public void play() { // pause -> play                       
        if (!this.gLAgent.isPresent()) {
            this.gLAgent = Optional.ofNullable(new GameLoopAgent());
            new Thread(this.gLAgent.get()).start();
        }
        this.gLAgent.get().play();
    }
    
    public void stop() {
        this.gLAgent.get().stop();
    }

    public static void main(String[] args) {
        new GameLoop();
    }
}
