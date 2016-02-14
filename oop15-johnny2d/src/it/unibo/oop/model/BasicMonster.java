package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;;

public class BasicMonster extends MovableEntity implements Enemy{

	public BasicMonster(int startingX, int startingY, Vector2 movementVector, Velocity speedValue) {
		super(startingX, startingY, movementVector, speedValue);
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		this.move();
	}
	public boolean checkCollision(Position newPosition) {
		return false;
	}

	protected int getEntityHeight() {
		return BASIC_ENEMY.getHeight();
	}

	protected int getEntityWidth() {
		return BASIC_ENEMY.getWidth();
	}

	

}
