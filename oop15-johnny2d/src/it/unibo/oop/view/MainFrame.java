package it.unibo.oop.view;

import it.unibo.oop.controller.State;

public interface MainFrame {
    
    /**
     * 
     * @param state
     *              state used to define the view to show.
     */
    public void changeView(final State state);

    /**
     * @param val
     *          true-setVisible; false-setNotVisible.
     */
    public void setVisible(final boolean val);
}


