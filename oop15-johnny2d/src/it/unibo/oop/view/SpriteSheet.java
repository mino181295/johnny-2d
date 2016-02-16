package it.unibo.oop.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a sprite sheet.
 */
public class SpriteSheet {
	
	private BufferedImage sheet;
	private List<BufferedImage> sprites;
	private final ImageLoader loader;
	private boolean isSplitted = false;
	
	/**
	 * Constructs a new sprite sheet.
	 * @param sheetName
	 * 		the name of the sprite sheet
	 */
	public SpriteSheet(String sheetName) {
		this.loader = new ImageLoader();
		try {
			this.sheet = this.loader.load(sheetName);
		} catch (IOException e) {
			System.out.println("Sheet not found");
		}
		this.sprites = new LinkedList<>();
	}
	
	private BufferedImage grabSprite(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	/**
	 * Splits every sprite in the sprite sheet. It considers only the
	 * first sprite for every row in the sheet (only one animation).
	 * @param imagesWidth
	 * 		the width of each sprite in the sprite sheet
	 * @param imagesHeight
	 * 		the height of each sprite in the sprite sheet
	 * @return
	 * 		a list of {@link java.awt.image.BufferedImage} with all the sprite in the sprite sheet
	 */
	public List<BufferedImage> split(int imagesWidth, int imagesHeight) {
		sprites = new LinkedList<>();
		isSplitted = ((sheet.getHeight() % imagesHeight == 0) &&
				(sheet.getWidth() % imagesWidth == 0)) ? true : false;
		if ((sheet != null) && isSplitted) {
			for (int y = 0; y < sheet.getHeight(); y += imagesHeight) {
				for(int x = 0; x < imagesWidth; x += imagesWidth) {
					sprites.add(grabSprite(x, y, imagesWidth, imagesHeight));
				}
			}
		}
		return sprites;
	}
	
	/**
	 * Returns the sprite in the list corresponding to the given index.
	 * @param i
	 * 		the index of the sprite
	 * @return
	 * 		the corresponding sprite
	 * @throws IndexOutOfBoundsException
	 * 		if the index is invalid
	 */
	public BufferedImage getSprite(int i) throws IndexOutOfBoundsException {
		if ((i >= sprites.size()) && isSplitted) {
			throw new IndexOutOfBoundsException();
		}
		return sprites.get(i);
	}
}