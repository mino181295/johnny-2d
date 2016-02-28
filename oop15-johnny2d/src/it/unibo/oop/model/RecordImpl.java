package it.unibo.oop.model;

import java.util.Optional;

/**
 * Class implementing {@link Record}.
 */
public final class RecordImpl implements Record {

    private static Optional<Record> singleton = Optional.empty();
    private volatile boolean record;
    private Score value;

    private RecordImpl() {
        this.value = new Score();
    }

    /**
     * @return the SINGLETON instance of the class.
     */
    public static synchronized Record getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new RecordImpl());
        }
        return singleton.get();
    }

    @Override
    public synchronized void reset() {
        this.record = false;
        this.value = new Score();
    }

    @Override
    public synchronized void setRecord(final Score value) {
        this.value = value;
        this.record = true;
    }

    @Override
    public boolean isRecord() {
        return this.record;
    }

    @Override
    public synchronized Score getValue() {
        return this.value;
    }

    @Override
    public synchronized void setValue(final Score value) {
        this.reset();
        this.value = value;
    }
}