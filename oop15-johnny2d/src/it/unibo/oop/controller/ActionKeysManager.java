package it.unibo.oop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class for a keyboard manager which manages ActionKeys.
 */
public class ActionKeysManager extends AbstractKeysManager<ActionKey, ActionKey> {

    private static Optional<KeysManager<ActionKey, ActionKey>> singleton = Optional.empty();
    private static final int LIMIT = 0;
    
    private ActionKeysManager() {
    }
    
    /**
     * @return the singleton instance of the class.
     */
    public static synchronized KeysManager<ActionKey, ActionKey> getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new ActionKeysManager());
        }
        return singleton.get();
    }
    
    @Override
    public synchronized ActionKey processKeys() {
        final List<ActionKey> tmpList = new ArrayList<>();
        ActionKey out = ActionKey.NONE;

        this.processPressed(LIMIT, tmpList);
        this.processTyped(LIMIT, tmpList);
        if (!tmpList.isEmpty()) {
            out = tmpList.get(0);
        }
        this.removeKey(ActionKey.PAUSE);
        return out;
    }
}