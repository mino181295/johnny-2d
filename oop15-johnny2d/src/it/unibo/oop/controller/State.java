package it.unibo.oop.controller;

import java.util.Optional;

/**
 * 
 * @author Paolo
 *
 * Enum with each game states together with actions.
 */
public enum State {
                            
    /* With Action */
    START(Optional.of(() -> GameLoop.getInstance().start()), false),
    PLAY(Optional.of(() -> GameLoop.getInstance().play()), false),
    EXIT(Optional.of(() -> System.exit(0)), false),
    BACK(Optional.of(() -> ViewsManager.getInstance().showLast()), false),
    PAUSE(Optional.of(() -> ViewsManager.getInstance().reset()), true),
    
    /* With View */
    LAUNCHING(Optional.empty(), true),
    QUIT(Optional.empty(), true),
    OPTIONS(Optional.empty(), true),
    CREDITS(Optional.empty(), true);
    
    private final Optional<Runnable> action;
    private final boolean drawable;
    
    private State(final Optional<Runnable> action, final boolean drawable) {
        this.action = action;
        this.drawable = drawable;
    }
    
    public boolean isDrawable() {
        return this.drawable;
    }
    
    public void doAction() {
        this.action.ifPresent(Runnable::run);
    }   
}