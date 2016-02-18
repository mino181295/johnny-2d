package it.unibo.oop.view;

import javax.swing.JComponent;

import it.unibo.oop.controller.StateObserver;

/**
 * 
 * @author Paolo
 *
 * Interface implemented by menu views.
 *       
 */
public interface MenuInterface extends Showable {

    /**
     * @param cmp
     *            component to attach.
     * @param customize
     *            true to apply custom constraints.
     */
    public void addComponent(JComponent cmp, boolean customize);
    
    /**
     * @param obs
     *          observer to attach to the view.
     */
    public void addObserver(StateObserver obs);
}
