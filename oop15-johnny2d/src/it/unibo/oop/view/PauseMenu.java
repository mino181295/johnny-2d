package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class PauseMenu extends MenuPanel {

    private static final long serialVersionUID = 1074304062110360844L;

    private enum Button implements StateButton {
        REPLAY("Replay", State.START),
        RESUME("Resume", State.PLAY),
        OPTIONS("Options", State.OPTIONS),
        QUIT("Quit", State.QUIT);
        
        private final String name;
        private final State state;
        
        private Button(final String name, final State state) {
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

    public PauseMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);
      
        /* ICON SETTING */
        this.setIcon("/pause.png");
        
        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}    