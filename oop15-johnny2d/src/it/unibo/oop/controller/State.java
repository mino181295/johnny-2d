package it.unibo.oop.controller;

import java.util.Optional;

/**
 * Enum with each application state together with some actions, used to
 * communicate the next state of the app.
 */
public enum State {

    /**
     * To start the game.
     */
    START(Optional.of(() -> GameLoop.getInstance().start()), false),

    /**
     * To play/replay the game.
     */
    PLAY(Optional.of(() -> GameLoop.getInstance().play()), false),

    /**
     * To exit the game.
     */
    EXIT(Optional.of(() -> System.exit(0)), false),

    /**
     * To come back the previous app-state.
     */
    BACK(Optional.of(() -> ViewsManager.getInstance().showLast()), false),

    /**
     * To pause the game.
     */
    PAUSE(Optional.of(() -> ViewsManager.getInstance().reset()), true),

    /**
     * To launch the game.
     */
    LAUNCHING(Optional.empty(), true),

    /**
     * To quit the game.
     */
    QUIT(Optional.empty(), true),

    /**
     * To show the options of the app.
     */
    OPTIONS(Optional.empty(), true),

    /**
     * To show the credits of the app.
     */
    CREDITS(Optional.empty(), true);

    private final Optional<Runnable> action;
    private final boolean drawable;

    State(final Optional<Runnable> action, final boolean drawable) {
        this.action = action;
        this.drawable = drawable;
    }

    /**
     * @return true if the State has a view associated.
     */
    public boolean isDrawable() {
        return this.drawable;
    }

    /**
     * action associated to the state of the view.
     */
    public void doAction() {
        this.action.ifPresent(Runnable::run);
    }
}