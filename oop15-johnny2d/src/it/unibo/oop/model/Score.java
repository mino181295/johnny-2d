package it.unibo.oop.model;

import java.io.Serializable;

/**
 * Represents the score based on how many {@link Enemy} the
 * {@link MainCharacter} has killed. Class implementing
 * {@link java.io.Serializable} that can be wrote and saved by the user.
 */
public class Score implements Serializable, Comparable<Score> {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SCORE = 0;

    private int currentScore;
    /**
     * Constructor that initializes the {@link Score} with an initial value
     */
    public Score(final int initialScore) {
        this.currentScore = initialScore;
    }

    public Score() {
        this(DEFAULT_SCORE);
    }

    /**
     * Increases the current score.
     */
    public void increaseScore(final int value) {
        this.currentScore += value;
    }

    public String toString() {
        return "Score: " + currentScore;
    }
    
    public int getValue() {
        return this.currentScore;
    }

    @Override
    public int compareTo(final Score score) {
        return this.getValue() - score.getValue();
    }
}