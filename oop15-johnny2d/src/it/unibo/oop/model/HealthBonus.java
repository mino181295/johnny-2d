package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.*;

public class HealthBonus extends AbstractEntity implements Collectable{


	public HealthBonus(int startingX, int startingY) {
		super(startingX, startingY);
	}

	protected int getEntityHeight() {
		return BONUS.getHeight();
	}

	protected int getEntityWidth() {
		return BONUS.getWidth();
	}

	public void collect(MainCharacter bonusCollector) {
		
	}
	
}
