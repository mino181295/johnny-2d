package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for quit Menu-view.
 */
public class QuitMenu extends MenuPanel {

    private static final long serialVersionUID = -8073693943984907077L;

    private enum Button implements StateButton {
        YES("yes", AppState.EXIT), NO("No", AppState.BACK);

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
    public QuitMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/exit.png");

        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}
