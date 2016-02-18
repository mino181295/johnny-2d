package it.unibo.oop.view;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;
import static it.unibo.oop.utilities.CharactersSettings.MAIN_CHARACTER;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.JLabel;

import it.unibo.oop.utilities.Direction;

/**
 * The {@link javax.swing.JPanel} containing the graphics elements
 * of the game's main level.
 */
public class LevelPanel extends BackgroundPanel {

	private static final long serialVersionUID = 8057405927611227670L;
	
	private final SpriteSheet mainCharacterSheet;
	private final SpriteSheet enemySheet;
	private Map<Direction, BufferedImage> mainCharacterSprites;
	private Map<Direction, BufferedImage> enemySprites;
	private final JLabel stats;
	
	/**
	 * Builds the {@link javax.swing.JPanel} and loads every
	 * {@link it.unibo.oop.view.SpriteSheet}.
	 */
	public LevelPanel() {
		super("/background.jpg");
		this.mainCharacterSheet = new SpriteSheet("/mainCharacter.png");
		this.mainCharacterSprites = mainCharacterSheet.split(MAIN_CHARACTER.getWidth(), MAIN_CHARACTER.getHeight());
		this.enemySheet = new SpriteSheet("/enemy.png");
		this.enemySprites = enemySheet.split(BASIC_ENEMY.getWidth(), BASIC_ENEMY.getHeight());
		this.stats = new JLabel();
		this.stats.setFont(new Font("Verdana", 1, 20));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(this.stats);
	}
	
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		this.drawMainCharacter(g);
		this.drawMovables(g);
		this.drawStables(g);
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
	 * Draws the movable entities.
	 * @param g
	 * 		the {@link java.awt.Graphics} object
	 */
	private void drawMovables(final Graphics g) {
		g.drawImage(this.enemySprites.get(FRONT_INDEX),
				(this.getX() + 200), (this.getY() + 200), null);
	}
	
	/**
	 * Draws the stable entities.
	 * @param g
	 * 		the {@link java.awt.Graphics} object
	 */
	private void drawStables(final Graphics g) {
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
