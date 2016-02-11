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

import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class Launcher extends BaseMenu {
    
	private static final Color BUTTONS_COLOR = new Color(255, 220, 130);
	
	public Launcher(final StateObserver stateObserver) {
	    
		super("Johnny2D Launcher");
	
//		final Dimension prefButtonSize = new Dimension(200, 50);
//		this.setSize(500, 500);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setLocationRelativeTo(null);
//		this.setResizable(false);
//						
//		final URL imgURL = Launcher.class.getResource("/background.jpg");
//		final ImageIcon img = new ImageIcon(imgURL);
//		
//		final JPanel menuPanel = new ImagePanel(img.getImage()); 
//		menuPanel.setLayout(new GridBagLayout());
		
		/*
		 * BUTTONS CREATION
		 */
		
		/* PLAY */
		final JButton play = new JButton("Play");
//		play.setPreferredSize(prefButtonSize);
//		play.setBackground(BUTTONS_COLOR);
		
		/* OPTIONS */
		final JButton options = new JButton("Options");
//		options.setPreferredSize(prefButtonSize);
//		options.setBackground(BUTTONS_COLOR);
		
		/* QUIT */
	    final JButton quit = new JButton("Quit");
//		quit.setPreferredSize(prefButtonSize);
//		quit.setBackground(BUTTONS_COLOR);
		
//		/* ADD BUTTONS */
//		final GridBagConstraints cnst = new GridBagConstraints();
//		cnst.insets = new Insets(3, 3, 3, 3);
//		cnst.gridy = 0;
//		this.getContentPane().add(play, cnst);
//		cnst.gridy++;
//		this.getContentPane().add(options, cnst);
//		cnst.gridy++;
//		this.getContentPane().add(quit, cnst);
		
		this.addButton(play);
		this.addButton(options);
		this.addButton(quit);
		
		/* ACTIONS */
		quit.addActionListener(e -> {
			final int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
					"Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				stateObserver.stateAction(State.EXIT);
			}
		});
	
		// this.setContentPane(menuPanel);
		// this.setVisible(true);
	}
	
//	@Override
//	public void showIt() {
//	    try {
//            SwingUtilities.invokeAndWait(()->this.setVisible(true));
//        } catch (InvocationTargetException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//	}
	
//  public static void main(String... args) {
//      new Launcher();
//  }
	
}