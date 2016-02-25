package it.unibo.oop.controller;

import static it.unibo.oop.controller.MovementKey.NONE;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import it.unibo.oop.utilities.Direction;

/**
 * Class used to process the keys passed.
 */
public final class MovementKeysManager extends AbstractKeysManager<MovementKey, Direction> {

    private static Optional<KeysManager<MovementKey, Direction>> singleton = Optional.empty();
    private static final int LIMIT = 1;
    
    /**
     * @return the singleton instance of the class.
     */
    public static synchronized KeysManager<MovementKey, Direction> getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new MovementKeysManager());
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
        final List<MovementKey> tmpList = new ArrayList<>();
        MovementKey out = NONE;

        this.processPressed(LIMIT, tmpList);
        this.processTyped(LIMIT, tmpList);
        switch (tmpList.size()) {
        case 1:
            out = tmpList.get(0);
            break;
        case 2:
            if (tmpList.contains(MovementKey.UP) && tmpList.contains(MovementKey.DOWN)) {
                break;
            }
            if (tmpList.contains(MovementKey.UP)) {
                if (tmpList.contains(MovementKey.RIGHT)) {
                    out = MovementKey.RIGHTUP;
                } else {
                    out = MovementKey.LEFTUP;
                }
            } else if (tmpList.contains(MovementKey.DOWN)) {
                if (tmpList.contains(MovementKey.RIGHT)) {
                    out = MovementKey.RIGHTDOWN;
                } else {
                    out = MovementKey.LEFTDOWN;
                }
            }
            break;
        default:
            break;
        }

        return out.getDir();
    }
}