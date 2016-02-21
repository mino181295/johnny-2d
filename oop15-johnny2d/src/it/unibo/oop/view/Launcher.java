package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for launcher Menu-view.
 */
public class Launcher extends MenuPanel {

    private static final long serialVersionUID = 6835079187244547916L;

    private enum Button implements StateButton {
        PLAY("Play", AppState.START), OPTIONS("Options", AppState.OPTIONS), QUIT("Quit", AppState.QUIT);

        private final String name;
        private final AppState state;

        Button(final String name, final AppState state) {
            this.name = name;
            this.state = state;
        }

        public String getName() {
            return this.name;
        }

        public AppState getState() {
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

        /* AUTTONS ADDING */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}