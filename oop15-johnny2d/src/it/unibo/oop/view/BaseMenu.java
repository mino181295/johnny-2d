package it.unibo.oop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import it.unibo.oop.controller.StateObserver;

public abstract class BaseMenu implements MenuInterface {

    private final FrameMenu frame;
    private final List<StateObserver> obsList;
    
    public BaseMenu(final String name) {
        this.frame = new FrameMenu(name);
        this.obsList = new ArrayList<>();
    }
    
    public void addComponent(final JComponent cmp) {
        this.frame.addComponent(cmp);
    }
    
    public void addObserver(final StateObserver obs) {
        this.obsList.add(obs);
    }
    
    protected void doObsAction(final Consumer<StateObserver> action) {
        this.obsList.forEach(action);
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