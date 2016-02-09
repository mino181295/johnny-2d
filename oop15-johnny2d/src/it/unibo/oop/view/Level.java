package it.unibo.oop.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;

/**
 * The game's main level.
 */
public class Level extends JFrame {

	private static final long serialVersionUID = 3472179234994682358L;
	private static final int SPRITES_WIDTH = 32;
	private static final int SPRITES_HEIGHT = 32;
	
	private SpriteSheet mainCharacter = new SpriteSheet("/maincharacter.png");
	private List<BufferedImage> mainCharacterSprites;
	private final Graphics gr = this.getGraphics();
	
	/**
	 * Constructs the level.
	 */
	public Level() {
		super("Johnny 2D");
		
		mainCharacterSprites = mainCharacter.split(SPRITES_WIDTH, SPRITES_HEIGHT);
		final Dimension screen = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		this.setSize(screen);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		gr.drawImage(mainCharacterSprites.get(0), 50, 50, null);
		
		this.setVisible(true);
	}
	
	public static void main(String... args) {
		new Level();
	}
}