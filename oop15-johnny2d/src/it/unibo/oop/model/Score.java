package it.unibo.oop.model;

import java.io.Serializable;
/**
 * Rapresents the score based on how many {@link Enemy} has the {@link MainCharacter} killed.
 * Class implementing {@link Serializable} that can be writed and saved by the user.
 * @author Matteo Minardi
 *
 */
public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SCORE = 0;
	
	private int currentScore;
	
	public Score(int initialScore){
		this.currentScore = initialScore;
	}
	public Score(){
		this(DEFAULT_SCORE);
	}
	/**
	 * Increase the current score
	 */
	public void increaseScore(int value){
		this.currentScore += value;
	}

	public String toString() {
		return "Score: "+ currentScore;
	}
	
}
