package it.unibo.oop.view;

/**
 * 
 * @author Paolo
 *
 * Interface for each object that can be showed (designed for JFrame wrappers).
 */
public interface Showable {

    /**
     * shows the component i.e. set it visible.
     */
    void showIt();
    
    /**
     * hides the component i.e. set it not visible.
     */
    void hideIt();
}
