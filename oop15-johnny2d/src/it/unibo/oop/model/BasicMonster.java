package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;;

public class BasicMonster extends MovableEntity implements Enemy{

	private static final int SCORE_VALUE = 10;
	
	public BasicMonster(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		this.move();
	}
	public void checkCollision(Position newPosition) {
	}

	protected int getEntityHeight() {
		return BASIC_ENEMY.getHeight();
	}

	protected int getEntityWidth() {
		return BASIC_ENEMY.getWidth();
	}

	@Override
	public int getScoreValue() {
		return this.SCORE_VALUE;
	}

	

}
