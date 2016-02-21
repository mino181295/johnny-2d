package it.unibo.oop.controller;

import java.util.Optional;

/**
 *  class implementing the Controller of the MVC model.
 */
public final class GameLoop implements Controller {

    private static Optional<GameLoop> singleton = Optional.empty();
    private Optional<AgentInterface> gLAgent = Optional.empty();

    private GameLoop() {
        ViewsManagerImpl.getInstance().showView(AppState.LAUNCHING);
    }

    /**
     * @return
     *          the SINGLETON instance of the class.
     */
    public static synchronized Controller getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new GameLoop());
        }
        return singleton.get();
    }

    @Override
    public void start() { // launcher -> play / pause -> replay
        // GameStateImpl.getInstance().initialize(0);
        KeysManagerImpl.getInstance().reset(); 
        this.play();
    }

    @Override
    public void play() { // pause -> play
        ViewsManagerImpl.getInstance().hideView();
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

    /**
     * @param args
     *            ignored.
     */
    public static void main(final String... args) {
        new GameLoop();
    }
}