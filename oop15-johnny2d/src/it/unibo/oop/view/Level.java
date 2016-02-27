package it.unibo.oop.view;

import static it.unibo.oop.utilities.Settings.SCREEN_DIMENSION;
import java.util.Arrays;
import javax.swing.JFrame;
import it.unibo.oop.view.keyboard.KeyboardObserver;

/**
 * The main frame containing the game's main level.
 */
public class Level implements LevelInterface {

    /**
     * Builds the frame.
     */
    private static final String TITLE = "Johnny2D";
    private final JFrame frame;
    private final LevelPanel mainLevel;

    /**
     * @param obs
     *            observer of the keyboard.
     */
    public Level(final KeyboardObserver... obs) {
        final MainKeyListener keyListener;
        this.frame = new JFrame(TITLE);
        this.frame.setSize(SCREEN_DIMENSION);
        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.mainLevel = new LevelPanel();
        this.frame.getContentPane().add(mainLevel);

        /* per MainKeyListener */
        keyListener = new MainKeyListener(Arrays.asList(obs));
        this.frame.addKeyListener(keyListener);
        this.frame.setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void updateLevel() {
        this.mainLevel.repaint();
    }

    @Override
    public void showIt() {
        this.frame.setVisible(true);
    }

    @Override
    public void hideIt() {
        this.frame.setVisible(false);
    }

    @Override
    public void enableMusic(final boolean val) {

    }

    public void initialize(final int levelNumber) {
        this.mainLevel.setArena(levelNumber);
    }
}