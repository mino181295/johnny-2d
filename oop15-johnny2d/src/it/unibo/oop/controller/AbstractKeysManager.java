package it.unibo.oop.controller;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractKeysManager<I, O> implements KeysManager<I, O> {

    /*
     * elementi rimossi all'occorenza di un KeyRelease event: relativa a tasti
     * premuti PROLUNGATAMENTE
     */
    private List<I> keysPressed;

    /*
     * lista svuotata ad ogni frame: indica tasti premuti NON prolungatamente
     */
    private List<I> keysTyped;

    public AbstractKeysManager() {
        this.reset();
    }

    @Override
    public synchronized void reset() {
        this.keysPressed = new ArrayList<>();
        this.keysTyped = new ArrayList<>();
    }

    public synchronized void addKey(final I key) {
        if (!this.keysPressed.contains(key)) {
            this.keysPressed.add(key);
        }
        // in caso di pressione prolungata la key viene inserita
        // solo la prima volta.
        if (!this.keysTyped.contains(key)) {
            this.keysTyped.add(key);
        }
    }

    @Override
    public synchronized void removeKey(final I key) {
        if (this.keysPressed.contains(key)) {
            this.keysPressed.remove(key);
        }
    }

    @Override
    public synchronized boolean isAKeyPressed(final I key) {
        return this.keysPressed.contains(key) || this.keysTyped.contains(key);
    }

    @Override
    public abstract O processKeys();

    protected void processPressed(final int limit, final List<I> outList) {
        this.process(limit, this.keysPressed, outList);
    }

    protected void processTyped(final int limit, final List<I> outList) {
        this.process(limit, this.keysTyped, outList);
        this.keysTyped = new ArrayList<>(); /* resetto la keysTyped list */
    }

    private void process(final int noElem, final List<I> inList, final List<I> outList) {
        for (final I key : inList) {
            if (outList.size() <= noElem && !outList.contains(key)) {
                outList.add(key);
            } else {
                break;
            }
        }
    }
}