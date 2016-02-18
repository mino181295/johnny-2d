package it.unibo.oop.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class OptionsMenu extends BaseMenu {
    
    private enum Button implements StateButton {
        CREDITS("Credits", State.CREDITS),
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
    
    private static final String TITLE = "Options";
    
    public OptionsMenu(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);
        
        /* ICON SETTING */
        this.setIcon("/options.png");
        
        /* MUSIC */
        final JPanel musicPane = new JPanel(new FlowLayout());
        final JLabel label = new JLabel("Music");
        final JCheckBox check = new JCheckBox();
        // check.setBackground(new Color(255, 220, 130));
        musicPane.add(label);
        musicPane.add(check);
        this.addComponent(musicPane, false);
//        /* DIFFICULTY */
//        final JButton diff = new JButton("Difficulty");

//        this.addComponent(diff);
        
        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }  
}
