package it.unibo.oop.model;

import java.util.Random;

public class ScoreBonus implements Collectable {
	
	private final int scoreBonusValue;
	
	private static final int LOW_BONUS = 10;
	private static final int MEDIUM_BONUS = 50;
	private static final int HIGH_BONUS = 150;
	

	public ScoreBonus() {
		this.scoreBonusValue = randomScoreGeneration();
	}

	@Override
	public void collect(MainCharacter bonusCollector) {
		bonusCollector.getScore().increaseScore(scoreBonusValue);
	}
	
	private int randomScoreGeneration(){
		int value = new Random().nextInt(1000);
		if(isBetween(value, 0, 700)){
			return LOW_BONUS;
		}
		if(isBetween(value, 701, 950)){
			return MEDIUM_BONUS;
		}
		return HIGH_BONUS;
		
	}
	
	private boolean isBetween(int x, int lower, int upper) {
		  return lower <= x && x <= upper;
	}

}
