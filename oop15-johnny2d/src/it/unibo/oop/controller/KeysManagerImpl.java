package it.unibo.oop.controller;

import static it.unibo.oop.controller.KeyCommands.D;
import static it.unibo.oop.controller.KeyCommands.NONE;
import static it.unibo.oop.controller.KeyCommands.S;
import static it.unibo.oop.controller.KeyCommands.SA;
import static it.unibo.oop.controller.KeyCommands.SD;
import static it.unibo.oop.controller.KeyCommands.W;
import static it.unibo.oop.controller.KeyCommands.WA;
import static it.unibo.oop.controller.KeyCommands.WD;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import it.unibo.oop.utilities.Direction;

/**
 * Class used to process the keys passed.
 */
public final class KeysManagerImpl implements KeysManager<KeyCommands, Direction> {

    private static Optional<KeysManager<KeyCommands, Direction>> singleton = Optional.empty();

    /*
     * elementi rimossi all'occorenza di un KeyRelease event: relativa a tasti
     * premuti PROLUNGATAMENTE
     */
    private List<KeyCommands> keysPressed;

    /*
     * lista svuotata ad ogni frame: indica tasti premuti NON prolungatamente
     */
    private List<KeyCommands> keysTyped;

    private KeysManagerImpl() {
        this.reset();
    }

    /**
     * @return the singleton instance of the class.
     */
    public static synchronized KeysManager<KeyCommands, Direction> getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new KeysManagerImpl());
        }
        return singleton.get();
    }

    @Override
    public synchronized void reset() {
        this.keysPressed = new ArrayList<>();
        this.keysTyped = new ArrayList<>();
    }

    public synchronized void addKey(final KeyCommands key) {
        if (!this.keysPressed.contains(key) && key != KeyCommands.ESC) {
            this.keysPressed.add(key);
        }
        // in caso di pressione prolungata la key viene inserita
        // solo la prima volta.
        if (!this.keysTyped.contains(key)) {
            this.keysTyped.add(key);
        }
    }

    public synchronized void removeKey(final KeyCommands key) {
        if (this.keysPressed.contains(key)) {
            this.keysPressed.remove(key);
        }
    }

    @Override
    public synchronized boolean isAKeyPressed(final KeyCommands cmd) {
        return this.keysPressed.contains(cmd) || this.keysTyped.contains(cmd);
    }

    /*
     * FUNZIONAMENTO: Scorro la lista keysPressed e cerco i primi due tasti di
     * direzione; gli eventuali "posti liberi" vengono riemipi da max 2 tasti di
     * direzione presi dalla keysTyped list. NOTE: Al massimo vengono considerati 2
     * tasti di direzione; i Pressed hanno priorita' maggiore di quelli Typed;
     * eventuali altri tasti vengono ignorati per il frame da disegnare; le due direzioni
     * che servono per formare le 8 possibili direzioni nello spazio vengono inoltre scelte 
     * evitando di scegliere per entrambe la stessa direzione.
     */

    @Override
    public synchronized Direction processKeys() {
        final List<KeyCommands> tmpList = new ArrayList<>();
        KeyCommands out = NONE;

        this.process(this.keysPressed, tmpList);
        this.process(this.keysTyped, tmpList);
        switch (tmpList.size()) {
        case 1:
            out = tmpList.get(0);
            break;
        case 2:
            if (tmpList.contains(W) && tmpList.contains(S)) {
                break;
            }
            if (tmpList.contains(W)) {
                if (tmpList.contains(D)) {
                    out = WD;
                } else {
                    out = WA;
                }
            } else if (tmpList.contains(S)) {
                if (tmpList.contains(D)) {
                    out = SD;
                } else {
                    out = SA;
                }
            }
            break;
        default:
            break;
        }
        this.keysTyped = new ArrayList<>(); /* resetto la keysTyped list */

        return out.getDir();
    }

    private void process(final List<KeyCommands> inList, final List<KeyCommands> outList) {
        for (final KeyCommands key : inList) {
            if (key.isMovement()) {
                if (outList.isEmpty()) {
                    outList.add(key);
                } else if (outList.size() == 1
                        && outList.get(0) != key) { 
                    outList.add(key);
                } else {
                    break;
                }
            }
        }
    }
}