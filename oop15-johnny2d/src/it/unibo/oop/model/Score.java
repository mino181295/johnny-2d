package it.unibo.oop.model;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Score implements Feature, Serializable {

	private static final long serialVersionUID = 1L;

	private final String SCORE_TAG = "Score";
	
	private int currentScore;
	
	public Score(int score){
		this.currentScore = score;
	}
	
	public void increaseScore(int value){
		this.currentScore += value;
	}
	
	public void writeScoreOnFile(String filePath){
		try(
				ObjectOutputStream oos = new ObjectOutputStream(
											new FileOutputStream(filePath));				
		){
			oos.writeObject(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return this.SCORE_TAG;
	}

	public String toString() {
		return "Score: "+ currentScore;
	}
	
}
