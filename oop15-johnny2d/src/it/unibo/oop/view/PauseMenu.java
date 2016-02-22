package it.unibo.oop.view;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for pause Menu-view.
 */
public class PauseMenu extends MenuPanel {

    private static final long serialVersionUID = 1074304062110360844L;

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public PauseMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/pause.png");

        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Resume", AppState.PLAY),
                            new MenuPanel.StateButton("Replay", AppState.START),
                            new MenuPanel.StateButton("Options", AppState.OPTIONS), 
                            new MenuPanel.StateButton("Quit", AppState.QUIT));
    }
}