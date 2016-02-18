package it.unibo.oop.view;

import javax.swing.JButton;

import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class OptionsMenu extends BaseMenu {
    
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
    
    private static final String TITLE = "Options";
    public OptionsMenu(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);
        /*
         * BUTTONS CREATION
         */
        
        /* MUSIC */
        final JButton music = new JButton("Music");
        
        /* DIFFICULTY */
        final JButton diff = new JButton("Difficulty");
        
        /* TEXTURES */
        final JButton textures = new JButton("Textures");
        
        /* BACK */
        final JButton back = new JButton("back");
        
        this.addComponent(music);
        this.addComponent(diff);
        this.addComponent(textures);
        this.addComponent(back);
        
        /* ACTIONS */
        back.addActionListener(e -> this.doObsAction(obs -> new Thread(() -> obs.stateAction(State.BACK)).start()));
    }  
}
