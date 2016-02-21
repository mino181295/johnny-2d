package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for pause Menu-view.
 */
public class PauseMenu extends MenuPanel {

    private static final long serialVersionUID = 1074304062110360844L;

    private enum Button implements StateButton {
        REPLAY("Replay", AppState.START),
        RESUME("Resume", AppState.PLAY), 
        OPTIONS("Options", AppState.OPTIONS), 
        QUIT("Quit", AppState.QUIT);

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
    public PauseMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/pause.png");

        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}