package it.unibo.oop.view;

import java.util.List;
import java.util.function.Consumer;
import javax.swing.JComponent;
import it.unibo.oop.controller.StateObserver;

/**
 * Interface implemented by menu views.
 */
public interface MenuInterface extends ESource<StateObserver> {

    /**
     * @param cmp
     *            component to attach.
     * @param customize
     *            true to apply custom constraints.
     */
    void addComponent(JComponent cmp, boolean customize);

    /**
     * @param btns
     *            buttons to add.
     */
    void addStateButton(final List<StateButton> btns);

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
