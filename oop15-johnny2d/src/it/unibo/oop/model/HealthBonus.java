package it.unibo.oop.model;

import it.unibo.oop.utilities.Position;

public class HealthBonus extends AbstractEntity implements Collectable{


	public HealthBonus(int startingX, int startingY) {
		super(startingX, startingY);
	}

	protected int getEntityHeight() {
		return 0;
	}

	protected int getEntityWidth() {
		return 0;
	}

	public void collect(MainCharacter bonusCollector) {
		
	}
	
}
