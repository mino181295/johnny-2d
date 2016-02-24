package it.unibo.oop.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KeyboardObserverImpl <T extends Key> implements KeyboardObserver {

    private final Map<Integer, T> mapVKCodeToKeyCmd;
    private final KeysManager<T, ?> man;

    public KeyboardObserverImpl(final Class<T> keysEnum, final KeysManager<T, ?> man) {
        this.man = man;
        this.mapVKCodeToKeyCmd = new HashMap<>();
        for (final T cmd : keysEnum.getEnumConstants()) {
            this.mapVKCodeToKeyCmd.put(cmd.getVkCode(), cmd);
        }
    }

    @Override
    /* synchronized per essere sicuro che l'esecuzione per un KEY_PRESSED event non venga sospesa in favore di un 
     * evento KEY_RELEASED.
     */
    public synchronized void keyAction(final int keyCode, final int eventID) {
        final Optional<T> cmd = this.vkCodeToKeyCommand(keyCode);
        if (cmd.isPresent()) { // ignoro eventi provenienti da tasti non
                               // significativi.
            switch (eventID) {
            case KeyEvent.KEY_PRESSED:
                this.man.addKey(cmd.get());
                break;
            /*
             * rimuovo solo le keys premute a lungo; se era typed rimane in
             * lista finche' non viene disegnato il frame
             */
            case KeyEvent.KEY_RELEASED:
                this.man.removeKey(cmd.get());
                break;
            default:
                break;
            }
        }
    }

    /* per filtrare(da cui l'Optional)/mappare i tasti su i comandi */
    private Optional<T> vkCodeToKeyCommand(final int vkCode) {
        return Optional.ofNullable(this.mapVKCodeToKeyCmd.get(vkCode));
    }
}