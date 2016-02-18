package it.unibo.oop.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class Launcher extends BaseMenu {
  
    private enum Button {
        PLAY("Play", State.START),
        OPTIONS("Options", State.OPTIONS),
        QUIT("Quit", State.EXIT);
        
        private final String name;
        private final State state;
        
        private Button(final String name, final State state) {
            this.name = name;
            this.state = state;
        }
    }
    
    private static final String TITLE = "Johnny2D Launcher";
    private final JButton play; /* fare una mappa da JButton a State */
    private final JButton options;
    private final JButton quit;
    
    public Launcher(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);
		
		/*
		 * BUTTONS CREATION
		 */
		
		/* PLAY */
		this.play = new JButton("Play");
		
		/* OPTIONS */
		this.options = new JButton("Options");
		
		/* QUIT */
	    this.quit = new JButton("Quit");
	    
	    final JLabel label = new JLabel("!!!!!!!!!!!!!!!!!!!!!!! QUI IL LOGO !!!!!!!!!!!!!!!!!!!!!!!!!");
	    label.setPreferredSize(new Dimension(200, 200));
		this.addComponent(label);
		this.addComponent(this.play);
		this.addComponent(this.options);
		this.addComponent(this.quit);
		
		/* ACTIONS da fattorizzare meglio */
		this.play.addActionListener(new MyActionListener());
		this.options.addActionListener(new MyActionListener());
		this.quit.addActionListener(new MyActionListener());
		
//		for (final Button btn: Button.values()) {
//            final JButton jBtn = new JButton(btn.name);
//            this.addComponent(jBtn);
//            jBtn.addActionListener((e) -> this.doObsAction(obs -> new Thread(()-> obs.stateAction(btn.state)).start()));
//        }
	}
	
	private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final Object src = e.getSource();
            Optional<State> state = Optional.empty();
            if (src == Launcher.this.quit) {
//                final int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
//                                     "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (response == JOptionPane.YES_OPTION) {
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