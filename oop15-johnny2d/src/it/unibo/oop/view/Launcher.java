package it.unibo.oop.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import it.unibo.oop.controller.State;

public class Launcher extends BaseMenu {
  
    private static final String TITLE = "Johnny2D Launcher";
    private final JButton play; /* fare una mappa da JButton a State */
    private final JButton options;
    private final JButton quit;
    
	public Launcher() {
	    
		super(TITLE);
		
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
	}
	
	private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final Object src = e.getSource();
            if (src == Launcher.this.quit) {
                final int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                                     "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    Launcher.this.doObsAction(obs -> obs.stateAction(State.EXIT));
                }
            } else if (src == Launcher.this.options) {
                Launcher.this.doObsAction(obs -> obs.stateAction(State.OPTIONS));
            } else if (src == Launcher.this.play) {
                Launcher.this.doObsAction(obs -> obs.stateAction(State.PLAY));
            }
        }
	
	}
}