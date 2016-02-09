package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.oop.controller.GameLoop;

public class Launcher extends JFrame {

	private static final long serialVersionUID = -6224390548062243879L;
	private static final Color BUTTONS_COLOR = new Color(255, 220, 130);
	
	private final GameLoop gL;
	
	public Launcher(final GameLoop gL) {
	    
		super("Johnny2D Launcher");
		
		this.gL = gL;
		
		final Dimension prefButtonSize = new Dimension(200, 50);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
						
		final URL imgURL = Launcher.class.getResource("/background.jpg");
		final ImageIcon img = new ImageIcon(imgURL);
		
		final JPanel menuPanel = new ImagePanel(img.getImage()); 
		menuPanel.setLayout(new GridBagLayout());
		
		/*
		 * BUTTONS CREATION
		 */
		
		/* PLAY */
		final JButton play = new JButton("Play");
		play.setPreferredSize(prefButtonSize);
		play.setBackground(BUTTONS_COLOR);
		
		/* OPTIONS */
		final JButton options = new JButton("Options");
		options.setPreferredSize(prefButtonSize);
		options.setBackground(BUTTONS_COLOR);
		
		/* QUIT */
	    final JButton quit = new JButton("Quit");
		quit.setPreferredSize(prefButtonSize);
		quit.setBackground(BUTTONS_COLOR);
		
		/* ADD BUTTONS */
		final GridBagConstraints cnst = new GridBagConstraints();
		cnst.insets = new Insets(3, 3, 3, 3);
		cnst.gridy = 0;
		menuPanel.add(play, cnst);
		cnst.gridy++;
		menuPanel.add(options, cnst);
		cnst.gridy++;
		menuPanel.add(quit, cnst);
		
		quit.addActionListener(e -> {
			final int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
					"Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		});
	
		this.setContentPane(menuPanel);
		this.setVisible(true);
	
		/* per keylistener: solo prova, non centra col Launcher, sono da mettere nella view di gioco */
        this.addKeyListener(new MainKeyListener(this.gL));
        this.setFocusTraversalKeysEnabled(false);
		this.requestFocus(); /* per attivare il key listener */
	}
	
//	public static void main(String... args) {
//		new Launcher();
//	}
	public void showIt() {
	    this.setVisible(true);
	}
}