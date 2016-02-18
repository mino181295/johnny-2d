package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class QuitMenu extends BaseMenu {
    
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
    
    private static final String TITLE = "Exiting";

    public QuitMenu(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);
        
        /* ICON SETTING */
        this.setIcon("/exit.png");

        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}
