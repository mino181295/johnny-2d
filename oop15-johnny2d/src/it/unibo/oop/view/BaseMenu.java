package it.unibo.oop.view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
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

    public void addStateButton(final List<StateButton> btns) {
        btns.forEach(btn -> {
            final JButton jBtn = new JButton(btn.getName());
            this.addComponent(jBtn);
            jBtn.addActionListener((e) -> this.doObsAction(obs -> new Thread(()-> obs.stateAction(btn.getState())).start()));
        });
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