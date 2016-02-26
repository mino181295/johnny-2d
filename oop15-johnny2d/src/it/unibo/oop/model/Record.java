package it.unibo.oop.model;

/**
 * Interface for a a class managing the record score.
 */
public interface Record {

    /**
     * @return current record value.
     */
    public Score getValue();

    /**
     * @param value
     *            of the record.
     */
    public void setValue(Score value);

    /**
     * Reset player's record-score at {@link Score} value passed.
     */
    void reset(Score value);

    /**
     * @param value
     *            value of new record.
     */
    public void setRecord(Score value);

    /**
     * @return true if player has made a new record.
     */
    boolean isRecord();

}