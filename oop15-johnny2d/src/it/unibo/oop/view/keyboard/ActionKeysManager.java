package it.unibo.oop.view.keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a keyboard manager which manages ActionKeys.
 */
public final class ActionKeysManager extends AbstractKeysManager<ActionKey, ActionKey> {
    
    private static final int LIMIT = 0;
    
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