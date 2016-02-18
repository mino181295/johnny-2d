package it.unibo.oop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;

import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class Launcher extends BaseMenu {
  
    private enum Button implements StateButton {
        PLAY("Play", State.START),
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
    
    private static final String TITLE = "Johnny2D Launcher";
    private final JButton play; /* fare una mappa da JButton a State */
    private final JButton options;
    private final JButton quit;
    
    public Launcher(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);
		
        /* ICON SETTING */
        this.setIcon("/launcher.png"); 
        
       // this.addStateButton(Arrays.asList(Button.values()));
        
		/*
		 * BUTTONS CREATION
		 */
		
        final ActionListener aL = new MyActionListener();
		/* PLAY */
		this.play = new JButton("Play");
		this.play.addActionListener(aL);
		this.addComponent(this.play, true);
		
		/* OPTIONS */
		this.options = new JButton("Options");
		this.options.addActionListener(aL);
		this.addComponent(this.options, true);
		
		/* QUIT */
	    this.quit = new JButton("Quit");
	    this.quit.addActionListener(aL);
	    this.addComponent(this.quit, true);
	}
	
	private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final Object src = e.getSource();
            Optional<State> state = Optional.empty();
            if (src == Launcher.this.quit) {
                    state = Optional.of(State.QUIT);
            } else if (src == Launcher.this.options) {
                state = Optional.of(State.OPTIONS);
            } else if (src == Launcher.this.play){
                state = Optional.of(State.START);
            }
            state.ifPresent(st -> Launcher.this.doObsAction(
                            obs -> new Thread(()-> obs.stateAction(st)).start()));
        }
	}
}