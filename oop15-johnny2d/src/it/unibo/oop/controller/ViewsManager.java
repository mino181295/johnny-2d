package it.unibo.oop.controller;

/**
 * 
 * Interface for a views manager.
 *
 * @param <V>
 *            type of the main view to manage.
 * @param <S>
 *            type of object used to choose which view should be shown.
 */
public interface ViewsManager<V, S> extends Manager {

    /**
     * @return the main view V.
     */
    V getView();

    /**
     * @param state
     *            object used to choose which view should be shown.
     */
    void showView(S state);

    /**
     * Hides the view or some parts of it.
     */
    void hideView();
}
