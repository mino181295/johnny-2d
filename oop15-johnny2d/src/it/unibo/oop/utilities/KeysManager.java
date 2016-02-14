package it.unibo.oop.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.controller.KeyCommands;
import static it.unibo.oop.controller.KeyCommands.*;
import it.unibo.oop.controller.KeyboardObserver;

/**
 * 
 * @author Paolo
 *
 * class used by the controller to process the key list.
 */
public class KeysManager implements KeyboardObserver {

    private static final KeysManager SINGLETON = new KeysManager();
    
    private static final int NO_COMMANDS = KeyCommands.class.getEnumConstants().length; 
    private final List<KeyCommands> keysPressed; /* elementi rimossi da KeyRelease event: indica tasti premuti PROLUNGATAMENTE */
    private List<KeyCommands> keysTyped;         /* svuotata ad ogni frame: indica tasti premuti NON prolungatamente*/
    private final Map<Integer, KeyCommands> mapVKCodeToKeyCmd;
    
    private KeysManager() {
        this.keysPressed = new ArrayList<>();
        this.keysTyped = new ArrayList<>();     /*lista perché più tasti alla volta potrebbero essere typed p.e. M tasti direzione e 1 spara */
        this.mapVKCodeToKeyCmd = new HashMap<>(NO_COMMANDS);
        for (final KeyCommands cmd: KeyCommands.class.getEnumConstants()) {
            this.mapVKCodeToKeyCmd.put(cmd.getVkCode(), cmd);
        }
    }
    
    public static KeysManager getKeysManager() {
        return SINGLETON;
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
    /* eventualmente parametizzare Pair */
//    public synchronized Pair<Direction, Direction> getDirection() {
//        final Pair<Direction, Direction> out = new Pair<>(Direction.NONE, Direction.NONE);      
//        
////        System.out.println("PRESSED: " + this.keysPressed);
////        System.out.println("TYPED: " + this.keysTyped);
//        
//        this.processOutPair(this.keysPressed, out);
//        this.processOutPair(this.keysTyped, out);
//        this.keysTyped = new ArrayList<>(); /* resetto le keysTyped */
//        
//        
//            
//        return out;
//    }
//
//    private void processOutPair(final List<KeyCommands> inList, final Pair<Direction, Direction> outPair) {
//        for (final KeyCommands key: inList) {
//            if (key.isMovement()) {
//                if (outPair.getX() == Direction.NONE) {
//                    outPair.setX(key.getDir());
//                } else if (outPair.getY() == Direction.NONE && outPair.getX() != key.getDir()) { /* do priorità a dir 2 diverse da dir 1 */
//                    outPair.setY(key.getDir());
//                } else
//                    break;
//            }
//        }
//    }
    
    
/* ALTERNATIVA */
    public synchronized Direction getDirection() {
        final List<KeyCommands> tmpList = new ArrayList<>();      
        KeyCommands out = NONE;
        
//        System.out.println("PRESSED: " + this.keysPressed);
//        System.out.println("TYPED: " + this.keysTyped);
        
        this.processKeys(this.keysPressed, tmpList);
        this.processKeys(this.keysTyped, tmpList);
        this.keysTyped = new ArrayList<>(); /* resetto le keysTyped */
        switch (tmpList.size()) {
        case 1:
            out = tmpList.get(0);
            break;
        case 2:
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
        }
        
        return out.getDir();
    }

    private void processKeys(final List<KeyCommands> inList, final List<KeyCommands> outList) {
        for (final KeyCommands key: inList) {
            if (key.isMovement()) {
                if (outList.isEmpty()) {
                    outList.add(key);
                } else if (outList.size() == 1 && outList.get(0) != key) { /* do priorità a dir 2 diverse da dir 1 */
                    outList.add(key);
                } else
                    break;
            }
        }
    }
/* FINE ALTERNATIVA */
    
    /* per filtrare(da cui l'Optional)/mappare i tasti su i comandi */
    private Optional<KeyCommands> vk_CodeToKeyCommand(final int vk_Code) { 
        return Optional.ofNullable(this.mapVKCodeToKeyCmd.get(vk_Code));
    }
    
    public synchronized boolean isAKeyPressed(final KeyCommands cmd) {
        return this.keysPressed.contains(cmd) || this.keysTyped.contains(cmd);
    }
    
    @Override
    public synchronized void keyPressed(final int keyCode) {
        final Optional<KeyCommands> cmd = this.vk_CodeToKeyCommand(keyCode);
        if (cmd.isPresent()) { /* ignoro eventi provenienti da tasti non significativi */
            if (!this.keysPressed.contains(cmd.get())) {
                this.keysPressed.add(cmd.get());
            }
            if (!this.keysTyped.contains(cmd.get())) {
                this.keysTyped.add(cmd.get());
            }
        }
    }

    @Override
    public synchronized void keyReleased(final int keyCode) {
        final Optional<KeyCommands> cmd = this.vk_CodeToKeyCommand(keyCode);
        if (cmd.isPresent() && this.keysPressed.contains(cmd.get())) {
            this.keysPressed.remove(cmd.get()); /* rimuovo solo le keys premute a lungo; se era typed rimane in lista finché
            non viene disegnato il frame */
        }
    }
    
    @Override /* analogo a keyPressed ma con arg0 un char */
    public synchronized void keyTyped(final char keyChar) {
//        final Optional<KeyCommands> cmd = this.vk_CodeToKeyCommand(KeyEvent.getExtendedKeyCodeForChar(keyChar));
//        if (cmd.isPresent() && !this.keysTyped.contains(cmd.get()) && !this.keysPressed.contains(cmd.get())) {
//            this.keysTyped.add(cmd.get());
//        }
    }
}
