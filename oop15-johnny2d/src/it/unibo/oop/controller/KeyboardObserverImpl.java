package it.unibo.oop.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KeyboardObserverImpl implements KeyboardObserver {

    private static final int NO_COMMANDS = KeyCommands.class.getEnumConstants().length;
    private final Map<Integer, KeyCommands> mapVKCodeToKeyCmd;
    private final KeysManager<KeyCommands, ?> man;

    public KeyboardObserverImpl(final KeysManager<KeyCommands, ?> man) {
        this.man = man;
        this.mapVKCodeToKeyCmd = new HashMap<>(NO_COMMANDS);
        for (final KeyCommands cmd : KeyCommands.class.getEnumConstants()) {
            this.mapVKCodeToKeyCmd.put(cmd.getVkCode(), cmd);
        }
    }

    @Override
    /* synchronized per essere sicuro che l'esecuzione per un KEY_PRESSED event non venga sospesa in favore di un 
     * evento KEY_RELEASED.
     */
    public synchronized void keyAction(final int keyCode, final int eventID) {
        final Optional<KeyCommands> cmd = this.vkCodeToKeyCommand(keyCode);
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
    private Optional<KeyCommands> vkCodeToKeyCommand(final int vkCode) {
        return Optional.ofNullable(this.mapVKCodeToKeyCmd.get(vkCode));
    }
}