package it.unibo.oop.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import it.unibo.oop.controller.StateObserver;

public class BaseMenu implements MenuInterface {

    private final FrameMenu frame;
    private final List<StateObserver> obsList;

    public BaseMenu(final String name) {
        this.frame = new FrameMenu(name);
        this.obsList = new ArrayList<>();
    }

    public void addComponent(final JComponent cmp, final boolean customize) {
        this.frame.addComponent(cmp, customize);
    }

    public void addObserver(final StateObserver obs) {
        this.obsList.add(obs);
    }

    public void addStateButton(final List<StateButton> btns) {
        btns.forEach(btn -> {
            final JButton jBtn = new JButton(btn.getName());
            this.addComponent(jBtn, true);
            jBtn.addActionListener((e) -> this.doObsAction(obs -> new Thread(()-> obs.stateAction(btn.getState())).start()));
        });
    }
    
    public void setIcon(final String path) {
        final URL imgURL = this.getClass().getResource(path);
        final ImageIcon icon = new ImageIcon(imgURL);
        JLabel label = new JLabel(icon);
        this.addComponent(label, false);
    }
    
    protected void setFrameFeature(Consumer<JFrame> customization) {
        customization.accept(this.frame);
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