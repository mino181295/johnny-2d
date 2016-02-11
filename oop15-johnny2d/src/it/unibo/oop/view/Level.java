package it.unibo.oop.view;

<<<<<<< local
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

=======
>>>>>>> other
import javax.swing.JFrame;
import it.unibo.oop.controller.KeyboardObserver;
import static it.unibo.oop.utilities.Settings.*;

/**
 * The main frame containing the game's main level.
 */
public class Level extends JFrame {

	private static final long serialVersionUID = 3472179234994682358L;
	
	/**
	 * Builds the frame.
	 */
	public Level(final KeyboardObserver gLObserver) {
		super("Johnny 2D");
		
		this.setSize(SCREEN_DIMENSION);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().add(new LevelPanel());
		
		/* per MainKeyListener */
        this.addKeyListener(new MainKeyListener(Arrays.asList(gLObserver)));
        this.setFocusTraversalKeysEnabled(false);
        this.requestFocus(); /* per attivare il key listener */
		
		this.setVisible(true);
	}
}