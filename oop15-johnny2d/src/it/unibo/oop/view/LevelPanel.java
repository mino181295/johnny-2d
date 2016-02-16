package it.unibo.oop.view;

import static it.unibo.oop.utilities.Settings.SCREEN_DIMENSION;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The panel containing the graphics elements of the
 * game's main level.
 */
public class LevelPanel extends JPanel {

	private static final long serialVersionUID = 8057405927611227670L;
	private static final int SPRITES_WIDTH = 32;
	private static final int SPRITES_HEIGHT = 48;
	private static final int FRONT_INDEX = 0;
	private static final int LEFT_INDEX = 1;
	private static final int RIGHT_INDEX = 2;
	private static final int BACK_INDEX = 3;
	
	private final ImageLoader loader;
	private final SpriteSheet mainCharacterSheet;
	private final SpriteSheet enemySheet;
	private List<BufferedImage> mainCharacterSprites;
	private List<BufferedImage> enemySprites;
	private BufferedImage background;
	private final JLabel stats;
	
	/**
	 * Builds the panel.
	 */
	public LevelPanel() {
		this.loader = new ImageLoader();
		/*try {
			this.background = this.loader.load("/levelBackground.jpg");
		} catch (IOException e) {
			System.out.println("Background not found");
		}*/
		this.mainCharacterSheet = new SpriteSheet("/mainCharacter.png");
		this.mainCharacterSprites = mainCharacterSheet.split(SPRITES_WIDTH, SPRITES_HEIGHT);
		this.enemySheet = new SpriteSheet("/enemy.png");
		this.enemySprites = enemySheet.split(SPRITES_WIDTH, SPRITES_HEIGHT);
		this.stats = new JLabel();
		this.stats.setFont(new Font("Verdana", 1, 20));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(this.stats);
	}
	
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		this.drawMainCharacter(g);
		this.drawEnemies(g);
		this.drawStats(g);
	}
	
	/**
	 * Draws the main character.
	 * @param g
	 * 		the {@link java.awt.Graphics} object
	 */
	private void drawMainCharacter(final Graphics g) {
		g.drawImage(this.mainCharacterSprites.get(FRONT_INDEX),
				(this.getX() + 100), (this.getY() + 100), null);
	}
	
	/**
	 * Draws the enemies.
	 * @param g
	 * 		the {@link java.awt.Graphics} object
	 */
	private void drawEnemies(final Graphics g) {
		g.drawImage(this.enemySprites.get(FRONT_INDEX),
				(this.getX() + 200), (this.getY() + 200), null);
	}
	
	/**
	 * Draws the stats for the main character.
	 * @param g
	 * 		the {@link java.awt.Graphics} object
	 */
	private void drawStats(final Graphics g) {
		g.drawImage(this.mainCharacterSprites.get(FRONT_INDEX),
				this.getX(), this.getY(), null);
		this.stats.setText("    Life: 100    Score: 0");
	}
}
