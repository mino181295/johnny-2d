package it.unibo.oop.controller;

import java.util.Optional;

import it.unibo.oop.view.Launcher;
import it.unibo.oop.view.OptionsMenu;
import it.unibo.oop.view.PauseMenu;
import it.unibo.oop.view.QuitMenu;
import it.unibo.oop.view.Showable;

/**
 * 
 * @author Paolo
 *
 * Enum with each game states together with views and actions.
 */
public enum State {
    
    LAUNCHING(Optional.of(new Launcher(ViewsManager.getInstance())), Optional.empty()),
                                                                     /* Optional.of(() -> 
                                       ViewsManager.getInstance().getLevel().hideIt() */
    /* With Action */
    START(Optional.empty(), Optional.of(() -> GameLoop.getInstance().start())),
    PLAY(Optional.empty(), Optional.of(() -> GameLoop.getInstance().play())),
    EXIT(Optional.empty(), Optional.of(() -> System.exit(0))),
    BACK(Optional.empty(), Optional.of(() -> ViewsManager.getInstance().showLast())),
    
    /* With View */
    QUIT(Optional.of(new QuitMenu(ViewsManager.getInstance())), Optional.empty()),
    OPTIONS(Optional.of(new OptionsMenu(ViewsManager.getInstance())), Optional.empty()),
    PAUSE(Optional.of(new PauseMenu(ViewsManager.getInstance())), Optional.empty());
    
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