package it.unibo.oop.controller;

import static it.unibo.oop.controller.KeyCommands.NONE;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import it.unibo.oop.utilities.Direction;

/**
 * Class used to process the keys passed.
 */
public final class KeysManagerImpl extends AbstractKeysManager<KeyCommands, Direction> {

    private static Optional<KeysManager<KeyCommands, Direction>> singleton = Optional.empty();
    private static final int LIMIT = 1;
    
    /**
     * @return the singleton instance of the class.
     */
    public static synchronized KeysManager<KeyCommands, Direction> getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new KeysManagerImpl());
        }
        return singleton.get();
    }

    /*
     * FUNZIONAMENTO: Scorro la lista keysPressed e cerco i primi due tasti di
     * direzione; gli eventuali "posti liberi" vengono riemipi da max 2 tasti di
     * direzione presi dalla keysTyped list. NOTE: Al massimo vengono
     * considerati 2 tasti di direzione; i Pressed hanno priorita' maggiore di
     * quelli Typed; eventuali altri tasti vengono ignorati per il frame da
     * disegnare; le due direzioni che servono per formare le 8 possibili
     * direzioni nello spazio vengono inoltre scelte evitando di scegliere per
     * entrambe la stessa direzione.
     */

    @Override
    public synchronized Direction processKeys() {
        final List<KeyCommands> tmpList = new ArrayList<>();
        KeyCommands out = NONE;

        this.processPressed(LIMIT, tmpList);
        this.processTyped(LIMIT, tmpList);
        switch (tmpList.size()) {
        case 1:
            out = tmpList.get(0);
            break;
        case 2:
            if (tmpList.contains(KeyCommands.UP) && tmpList.contains(KeyCommands.DOWN)) {
                break;
            }
            if (tmpList.contains(KeyCommands.UP)) {
                if (tmpList.contains(KeyCommands.RIGHT)) {
                    out = KeyCommands.RIGHTUP;
                } else {
                    out = KeyCommands.LEFTUP;
                }
            } else if (tmpList.contains(KeyCommands.DOWN)) {
                if (tmpList.contains(KeyCommands.RIGHT)) {
                    out = KeyCommands.RIGHTDOWN;
                } else {
                    out = KeyCommands.LEFTDOWN;
                }
            }
            break;
        default:
            break;
        }

        return out.getDir();
    }
}