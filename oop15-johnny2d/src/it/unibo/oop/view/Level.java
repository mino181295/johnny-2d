package it.unibo.oop.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Level extends JFrame {

	private static final long serialVersionUID = 3472179234994682358L;
	
	public Level() {
		super("Johnny 2D");
		
		final Dimension screen = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		this.setSize(screen);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setVisible(true);
	}
}