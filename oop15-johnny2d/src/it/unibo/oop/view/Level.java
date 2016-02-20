package it.unibo.oop.view;

import static it.unibo.oop.utilities.Settings.SCREEN_DIMENSION;

import javax.swing.JFrame;

import it.unibo.oop.controller.KeyboardObserver;

/**
 * The main frame containing the game's main level.
 */
public class Level implements LevelInterface {

    /**
     * Builds the frame.
     */
    private static final String TITLE = "Johnny2D";
    private final JFrame frame;
    private final MainKeyListener keyListener;
    private final LevelPanel mainLevel;

    /**
     * @param obs
     */
    public Level(final KeyboardObserver obs) {
        this.frame = new JFrame(TITLE);
        this.frame.setSize(SCREEN_DIMENSION);
        frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        mainLevel = new LevelPanel();
        this.frame.getContentPane().add(mainLevel);

        /* per MainKeyListener */
        this.keyListener = new MainKeyListener();
        this.keyListener.addObserver(obs);
        this.frame.addKeyListener(this.keyListener);
        this.frame.setFocusTraversalKeysEnabled(false);
        // this.frame.requestFocus(); /* per attivare il key listener */
    }

    public void updateLevel() {
        this.mainLevel.repaint();
    }

    public void addObserver(final KeyboardObserver obs) {
        this.keyListener.addObserver(obs);
    }

    @Override
    public void showIt() {
        this.frame.setVisible(true);
    }

    @Override
    public void hideIt() {
        this.frame.setVisible(false);
    }

}