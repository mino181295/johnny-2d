package it.unibo.oop.controller;

import java.util.Optional;

import it.unibo.oop.model.GameStateImpl;

/**
 *  class implementing the Controller of the MVC model.
 */
public final class ControllerImpl implements Controller {

    private static Optional<ControllerImpl> singleton = Optional.empty();
    private Optional<AgentInterface> gLAgent = Optional.empty();

    private ControllerImpl() {
        ViewsManagerImpl.getInstance().showView(AppState.LAUNCHING);
    }

    /**
     * @return
     *          the SINGLETON instance of the class.
     */
    public static synchronized Controller getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new ControllerImpl());
        }
        return singleton.get();
    }

    @Override
    public void start() { // launcher -> play / pause -> replay
        GameStateImpl.getInstance().initialize(1);
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
        new ControllerImpl();
    }
}