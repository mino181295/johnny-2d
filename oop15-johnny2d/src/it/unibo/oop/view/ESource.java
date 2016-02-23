package it.unibo.oop.view;

/**
 * Interface for classes which generates events for some O-Observer.
 * 
 * @param <O>
 *              type of observer.
 */
public interface ESource <O> {

    /**
     * @param obs
     *          observer to attach.
     */
    void addObserver(O obs);
}
