package it.unibo.oop.controller;

import static it.unibo.oop.controller.KeyCommands.D;
import static it.unibo.oop.controller.KeyCommands.NONE;
import static it.unibo.oop.controller.KeyCommands.S;
import static it.unibo.oop.controller.KeyCommands.SA;
import static it.unibo.oop.controller.KeyCommands.SD;
import static it.unibo.oop.controller.KeyCommands.W;
import static it.unibo.oop.controller.KeyCommands.WA;
import static it.unibo.oop.controller.KeyCommands.WD;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.utilities.Direction;

/**
 * class used by the controller to process the key list.
 */
public final class KeysManager implements KeyboardObserver {

    private static Optional<KeysManager> singleton = Optional.empty();
    private static final int NO_COMMANDS = KeyCommands.class.getEnumConstants().length; 
    private List<KeyCommands> keysPressed; /* elementi rimossi da KeyRelease event: indica tasti premuti PROLUNGATAMENTE */
    private List<KeyCommands> keysTyped;         /* svuotata ad ogni frame: indica tasti premuti NON prolungatamente*/
    private final Map<Integer, KeyCommands> mapVKCodeToKeyCmd;

    private KeysManager() {
        this.reset();
        this.mapVKCodeToKeyCmd = new HashMap<>(NO_COMMANDS);
        for (final KeyCommands cmd: KeyCommands.class.getEnumConstants()) {
            this.mapVKCodeToKeyCmd.put(cmd.getVkCode(), cmd);
        }
    }

    /**
     * @return
     *          the singleton instance of the class.
     */
    public static synchronized KeysManager getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new KeysManager());
        }
        return singleton.get();
    }

    public synchronized void reset() {
        this.keysPressed = new ArrayList<>();
        this.keysTyped = new ArrayList<>(); /*lista perché più tasti alla volta potrebbero essere typed p.e. M tasti direzione e 1 spara */
    }

    public synchronized boolean isAKeyPressed(final KeyCommands cmd) {
        return this.keysPressed.contains(cmd) || this.keysTyped.contains(cmd);
    }

    /*
     * FUNZIONAMENTO:
     * Scorro la lista keysPressed e cerco i primi due tasti di direzione; gli eventuali "posti liberi" vengono riemipi
     * da max 2 tasti di direzione presi dalla keysTyped.
     * NOTE: 
     * Al massimo vengono considerati 2 tasti di direzione; i Pressed hanno priorità maggiore di quelli Typed;
     * eventuali altri tasti vengono ignorati per il frame da disegnare;
     * dir1 e dir2 (i due campo della classe Pair in questo caso) servono per formare 8 direzioni nello spazio: 
     * nel fare ciò non possono essere associare a dir1 e dir2 la stessa direzione (possono però essere associare
     * direzioni opposte).
     * 
     */

    public synchronized Direction getDirection() {
        final List<KeyCommands> tmpList = new ArrayList<>();
        KeyCommands out = NONE;

        this.processKeys(this.keysPressed, tmpList);
        this.processKeys(this.keysTyped, tmpList);
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
        }
        this.keysTyped = new ArrayList<>(); /* resetto le keysTyped */

        return out.getDir();
    }

    private void processKeys(final List<KeyCommands> inList, final List<KeyCommands> outList) {
        for (final KeyCommands key: inList) {
            if (key.isMovement()) {
                if (outList.isEmpty()) {
                    outList.add(key);
                } else if (outList.size() == 1 && outList.get(0) != key) { /* do priorità a dir 2 diverse da dir 1 */
                    outList.add(key);
                } else {
                    break;
                }
            }
        }
    }

    @Override 
    public synchronized void keyAction(final int keyCode, final int eventID) {
        final Optional<KeyCommands> cmd = this.vk_CodeToKeyCommand(keyCode);
        if (cmd.isPresent()) { /* ignoro eventi provenienti da tasti non significativi */
            switch (eventID) {
            case KeyEvent.KEY_PRESSED:
                if (!this.keysPressed.contains(cmd.get()) && cmd.get() != KeyCommands.ESC) {
                    this.keysPressed.add(cmd.get());
                }
                /* in caso di pressione prolungata viene inserito solo la prima volta */
                if (!this.keysTyped.contains(cmd.get())) {
                    this.keysTyped.add(cmd.get());
                }
                break;
            case KeyEvent.KEY_RELEASED:
                if (this.keysPressed.contains(cmd.get())) {
                    this.keysPressed.remove(cmd.get()); /* rimuovo solo le keys premute a lungo; se era typed rimane in lista finché
                    non viene disegnato il frame */
                }
                break;
            default:
            }
        }
    }

    /* per filtrare(da cui l'Optional)/mappare i tasti su i comandi */
    private Optional<KeyCommands> vk_CodeToKeyCommand(final int vkCode) {
        return Optional.ofNullable(this.mapVKCodeToKeyCmd.get(vkCode));
    }
}