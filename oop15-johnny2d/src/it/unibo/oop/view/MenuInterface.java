package it.unibo.oop.view;

import java.awt.FlowLayout;
import java.util.function.Consumer;
import javax.swing.JComponent;
import javax.swing.JPanel;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.view.MenuPanel.StateButton;

/**
 * Interface implemented by menu views.
 */
public interface MenuInterface extends ESource<StateObserver> {

    /**
     * @param cmp
     *            component to attach.
     */
    void addComponent(JComponent cmp);
    
    /**
     * @param cmps
     *            components to attach. NB: a {@link JPanel} with {@link FlowLayout}
     *            is used in addition to wrap the components.
     */
    void addComponents(final JComponent... cmps);

    /**
     * @param btns
     *            buttons to add.
     */
    void addStateButton(final StateButton... btns);

    /**
     * @param path
     *            path of image to add as icon.
     */
    void setIcon(final String path);

    /**
     * @param action
     *            which will be performed by each {@link StateObserver}
     */
    void doObsAction(final Consumer<StateObserver> action);
}