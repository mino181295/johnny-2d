package it.unibo.oop.view;

/**
 * Interface for each object that can be showed (designed for
 * {@link javax.swing.JFrame} wrappers).
 */
public interface Showable {

    /**
     * Shows the component i.e. sets it visible.
     */
    void showIt();

    /**
     * Hides the component i.e. sets it not visible.
     */
    void hideIt();
}