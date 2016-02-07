package it.unibo.oop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Launcher extends JFrame {

	private static final long serialVersionUID = -6224390548062243879L;
	private static final Color BUTTONS_COLOR = new Color(255, 220, 130);
	private static final int N_BUTTONS = 3;
	
	private Launcher() {
		super("Johnny2D Launcher");
		final Dimension prefButtonSize = new Dimension(200, 50);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.getContentPane().setLayout(new BorderLayout());
		
		JLabel background = new JLabel(new ImageIcon(Launcher.class.getResource("/background.jpg")));
		background.setLayout(new FlowLayout());
		JPanel buttonsPanel = new JPanel(new GridLayout(N_BUTTONS, 1));
		
		final JButton play = new JButton("Play");
		play.setPreferredSize(prefButtonSize);
		play.setBackground(BUTTONS_COLOR);
		final JButton options = new JButton("Options");
		play.setPreferredSize(prefButtonSize);
		options.setBackground(BUTTONS_COLOR);
		final JButton quit = new JButton("Quit");
		play.setPreferredSize(prefButtonSize);
		quit.setBackground(BUTTONS_COLOR);
		
		buttonsPanel.add(play);
		buttonsPanel.add(options);
		buttonsPanel.add(quit);
		background.add(buttonsPanel);
		this.getContentPane().add(background);
		this.setVisible(true);
	}
	
	public static void main(String... args) {
		new Launcher();
	}
}