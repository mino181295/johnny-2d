package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BULLET;
import java.util.Random;
import it.unibo.oop.utilities.Position;

public class Bullet extends MovableEntity implements Shot {

	private int distancePercurred;
	private final int maxBulletDistance = 10 + new Random().nextInt(10);
	
	public Bullet(MainCharacter heroPosition) {
		super(heroPosition.getX(), heroPosition.getY(), heroPosition.getDirection(), null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean checkCollision(Position newPosition) {
		return true;		
	}

	protected int getEntityHeight() {
		return BULLET.getHeight();
	}

	@Override
	protected int getEntityWidth() {
		return BULLET.getWidth();
	}

	@Override
	public int getDistancePercurred() {
		return this.distancePercurred;
	}

	
	
	

}
