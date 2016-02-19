package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class CreditsMenu extends MenuPanel {
    
    private static final long serialVersionUID = 7436402336801219924L;

    private enum Button implements StateButton {
        BACK("Back", State.BACK);
        
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
    
    public CreditsMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);
        
        /* ICON SETTING */
        this.setIcon("/credits.png");
        
        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }  
}
