package it.unibo.oop.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * System informations about the computer locations and the screen properties.
 * 
 * @author Matteo Minardi
 */
public final class Settings {
	
	private Settings() {};	
	/**
	 * Home directory.
	 */
	public static final String HOME_FOLDER = System.getProperty("user.home");
	/**
	 * File separator based on the Operating System.
	 */
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	/**
	 * Screen sized based on the hardware.
	 */
	public static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Shortcut to Width 
	 */
	public static final int SCREEN_WIDTH = (int) SCREEN_DIMENSION.getWidth();
	/**
	 * Shortcut to Height
	 */
	public static final int SCREEN_HEIGHT = (int) SCREEN_DIMENSION.getHeight();
	
	/**
	 * Folder for the saves and highscores
	 */
	public static final String MY_FOLDER = HOME_FOLDER + FILE_SEPARATOR + "Johnny2D" + FILE_SEPARATOR;
	/**
	 * High scores folder 
	 */
	public static final String HIGHSCORE_FOLDER = MY_FOLDER + FILE_SEPARATOR + "Highscores" + FILE_SEPARATOR;

}
