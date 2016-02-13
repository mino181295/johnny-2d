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
public interface MenuInterface {

    /**
     * @param cmp
     *           component to attach.
     */
    public void addComponent(JComponent cmp);
    
    /**
     * @param obs
     *          observer to attach to the view.
     */
    public void addObserver(StateObserver obs);
    
    /**
     * shows the component i.e. set it visible.
     */
    public void showIt();
    
    /**
     * hides the component i.e. set it not visible.
     */
    public void hideIt();
    
    
    
    
}
