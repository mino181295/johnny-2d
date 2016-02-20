package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

/**
 * Panel for Launcher Menu-view.
 */
public class Launcher extends MenuPanel {

    private static final long serialVersionUID = 6835079187244547916L;

    private enum Button implements StateButton {
        PLAY("Play", State.START), OPTIONS("Options", State.OPTIONS), QUIT("Quit", State.QUIT);

        private final String name;
        private final State state;

        Button(final String name, final State state) {
            this.name = name;
            this.state = state;
        }

        public String getName() {
            return this.name;
        }

        public State getState() {
            return this.state;
        }
    }

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public Launcher(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/launcher.png");

        this.addStateButton(Arrays.asList(Button.values()));
    }
}