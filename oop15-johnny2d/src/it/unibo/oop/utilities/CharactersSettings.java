package it.unibo.oop.utilities;

import java.util.Optional;

public enum CharactersSettings {
	MAIN_CHARACTER(32, 48, new Velocity(8, 12, 2)),
	BASIC_ENEMY(32, 48, new Velocity(7, 15, 10)),
	BULLET(8, 8, new Velocity(20, 30, 1)),
	BONUS(32, 32, null),
	WALL(32, 32, null);
	
	int width;
	int height;
	Optional<Velocity> speed;
	
	private CharactersSettings(final int width, final int height, final Velocity speed){
		this.width = width;
		this.height = height;
		this.speed = Optional.ofNullable(speed);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Velocity getSpeed() {
		return this.speed.get();
	}
	
}
