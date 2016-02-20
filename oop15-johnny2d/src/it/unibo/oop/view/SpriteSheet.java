package it.unibo.oop.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.oop.utilities.Direction;
import static it.unibo.oop.utilities.Direction.*;

/**
 * A class representing a sprite sheet.
 */
public class SpriteSheet {
	
	private BufferedImage sheet;
	private final Map<Direction, BufferedImage> sprites;
	
	/**
	 * Constructs a new sprite sheet.
	 * @param sheetName
	 * 		the name of the sprite sheet
	 */
	public SpriteSheet(final String sheetName) {
		try {
			this.sheet = ImageLoader.load(sheetName);
		} catch (IOException e) {
			System.out.println("Sheet not found");
		}
		this.sprites = new HashMap<>();
	}
	
	private BufferedImage grabSprite(final int x, final int y, final int width, final int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	/**
	 * Splits every sprite in the {@link it.unibo.oop.view.SpriteSheet}.
	 * It considers only the first sprite for every row in the sheet
	 * (only one animation).
	 * @param imagesWidth
	 * 		the width of each sprite in the {@link it.unibo.oop.view.SpriteSheet}
	 * @param imagesHeight
	 * 		the height of each sprite in the {@link it.unibo.oop.view.SpriteSheet}
	 * @return
	 * 		a {@link java.util.Map} of {@link java.awt.image.BufferedImage} with
	 * 		all the sprites in the {@link it.unibo.oop.view.SpriteSheet} mapped
	 * 		with their {@link it.unibo.oop.utilities.Direction}
	 */
	public Map<Direction, BufferedImage> split(final int imagesWidth, final int imagesHeight) {
		final boolean isSplitted = ((sheet.getHeight() % imagesHeight == 0) &&
				(sheet.getWidth() % imagesWidth == 0)) ? true : false;
		if ((sheet != null) && isSplitted) {
			for (int y = 0, currentRow = 0; y < sheet.getHeight(); y += imagesHeight, currentRow++) {
				for (int x = 0; x < sheet.getWidth(); x += imagesWidth) {
					switch (currentRow) {
						case 0: sprites.put(DOWN, grabSprite(x, y, imagesWidth, imagesHeight));
								break;
						case 1: sprites.put(LEFT, grabSprite(x, y, imagesWidth, imagesHeight));
								break;
						case 2: sprites.put(RIGHT, grabSprite(x, y, imagesWidth, imagesHeight));
								break;
						case 3: sprites.put(UP, grabSprite(x, y, imagesWidth, imagesHeight));
								break;
						default: sprites.put(DOWN, grabSprite(x, y, imagesWidth, imagesHeight));
								break;
					}
				}
			}
		}
		return sprites;
	}
	
	/**
	 * Returns the sprite in the {@link java.util.map} corresponding to the given
	 * {@link it.unibo.oop.utilities.Direction}.
	 * @param key
	 * 		the {@link it.unibo.oop.utilities.Direction} of the sprite
	 * @return
	 * 		the corresponding sprite
	 */
	public BufferedImage getSprite(final Direction dir) {
		return sprites.get(dir);
	}
}