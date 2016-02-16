package it.unibo.oop.controller;

import java.util.Optional;

import it.unibo.oop.view.Launcher;
import it.unibo.oop.view.OptionsMenu;
import it.unibo.oop.view.PauseMenu;
import it.unibo.oop.view.Showable;
import it.unibo.oop.view.ViewsManager;

/**
 * 
 * @author Paolo
 *
 * Enum with each game states together with views.
 */
public enum State {
    
    /* With Action */
    START(Optional.empty(), Optional.of(() -> GameLoop.getInstance().start())),
    PLAY(Optional.empty(), Optional.of(() -> GameLoop.getInstance().play())),
    EXIT(Optional.empty(), Optional.of(() -> System.exit(0))),
    
    /* With View */
    LAUNCHING(Optional.of(new Launcher(ViewsManager.getInstance())), Optional.of(() -> 
                                       ViewsManager.getInstance().getLevel().hideIt())),
    OPTIONS(Optional.of(new OptionsMenu(ViewsManager.getInstance())), Optional.empty()),
    PAUSE(Optional.of(new PauseMenu(ViewsManager.getInstance())), Optional.empty()),/*of(() -> GameLoop.getInstance().stop())),*/
    BACK(Optional.empty(), Optional.of(() -> ViewsManager.getInstance().showLast()));
    
    
    private Optional<Showable> view = Optional.empty();
    private Optional<Runnable> action = Optional.empty();
    
    private State(final Optional<Showable> view, Optional<Runnable> action) {
        this.view = view;
        this.action = action;
    }
    
    public Optional<Showable> getView() {
        return this.view;
    }
    
    public void doAction() {
        this.action.ifPresent(Runnable::run);
    }   
}