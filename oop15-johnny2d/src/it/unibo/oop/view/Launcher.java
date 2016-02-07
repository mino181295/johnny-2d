package it.unibo.oop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Launcher extends JFrame {

	private static final long serialVersionUID = -6224390548062243879L;
	private static final Color RED = new Color(255, 100, 100);
	private static final Color GREEN = new Color(100, 255, 100);
	private static final Color BLUE = new Color(100, 100, 255);
	private static final int N_BUTTONS = 3;
	private static final int SCREEN_PROPORTION = 2;
	private static final int BUTTONS_PROPORTION = 6;
	
	private Launcher() {
		super("Johnny2D Launcher");
		final Dimension dim = this.getToolkit().getScreenSize();
		this.setSize(dim.width/SCREEN_PROPORTION, dim.height/SCREEN_PROPORTION);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.getContentPane().setLayout(new BorderLayout());
		
		JLabel background = new JLabel(new ImageIcon(Launcher.class.getResource("/background.jpg")));
		background.setLayout(new BorderLayout());
		JPanel buttonsPanel = new JPanel(new GridLayout(N_BUTTONS, 1));
		buttonsPanel.setSize(dim.width/BUTTONS_PROPORTION, dim.height/BUTTONS_PROPORTION);
		final JButton play = new JButton("Play");
		play.setBackground(RED);
		final JButton options = new JButton("Options");
		options.setBackground(GREEN);
		final JButton quit = new JButton("Quit");
		quit.setBackground(BLUE);
		
		buttonsPanel.add(play);
		buttonsPanel.add(options);
		buttonsPanel.add(quit);
		background.add(buttonsPanel, BorderLayout.CENTER);
		this.getContentPane().add(background);
		this.setVisible(true);
	}
	
	public static void main(String... args) {
		new Launcher();
	}
}