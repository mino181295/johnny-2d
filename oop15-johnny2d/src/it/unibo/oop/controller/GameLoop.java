package it.unibo.oop.controller;

import java.util.Optional;
import it.unibo.oop.view.ViewsManager;

/**
 * 
 * @author Paolo
 *
 *  class implementing the Controller of the MVC model.
 */
public class GameLoop implements Controller {

    private static final GameLoop SINGLETON = new GameLoop();
    private Optional<GameLoopAgent> gLAgent;

    private GameLoop() {
        ViewsManager.getInstance().showView(State.LAUNCHING);
    }
    
    public static GameLoop getInstance() {
        return SINGLETON;
    }

    @Override
    public void start() { // launcher -> play / pause -> replay
       // GameStateImpl.getInstance().initialize(0);
        this.play();
    }

    // generando sempre un nuovo thread anzich� usare wait e signal ottengo una gestione pi� semplice 
    public void play() { // pause -> play                       
        this.gLAgent = Optional.ofNullable(new GameLoopAgent());
        new Thread(this.gLAgent.get()).start();
    }
    
    public void stop() {
        this.gLAgent.get().stop();
    }

    public static void main(String[] args) {
        new GameLoop();
    }
}
