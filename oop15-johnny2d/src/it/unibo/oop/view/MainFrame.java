package it.unibo.oop.view;

import it.unibo.oop.controller.State;

/**
 * Interface menus-frame, which "contains" each panel.
 */
public interface MainFrame {

    /**
     * 
     * @param state
     *            state used to define the view to show.
     */
    void changeView(final State state);

    /**
     * @param val
     *            true-setVisible; false-setNotVisible.
     */
    void setVisible(final boolean val);
}
