package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.JLabel;

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
        
        /* LABELS ADDING */
        for (final String text: Arrays.asList("Model: Matteto Minardi", "View: Pasini Giacomo", "Controller: Paolo Venturi")) {
            final JLabel label = new JLabel(text);
            label.setFont(new Font("MusicStyle", Font.PLAIN, 25));
            label.setForeground(Color.WHITE);
            this.addComponent(label, false);
        }
        
        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }  
}
