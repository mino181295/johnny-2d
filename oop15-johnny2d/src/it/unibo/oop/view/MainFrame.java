package it.unibo.oop.view;

import javax.swing.JPanel;

public interface MainFrame {
    
    /**
     * @param panel
     *              panel to view.
     */
    public void setPanel(JPanel panel);

    /**
     * @param val
     *          true-setVisible; false-setNotVisible.
     */
    public void setVisible(final boolean val);
}


