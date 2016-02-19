package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class QuitMenu extends MenuPanel {
    
    private static final long serialVersionUID = -8073693943984907077L;

    private enum Button implements StateButton {
        YES("yes", State.EXIT),
        NO("No", State.BACK);
        
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

    public QuitMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);
        
        /* ICON SETTING */
        this.setIcon("/exit.png");

        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}
