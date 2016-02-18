package it.unibo.oop.utilities;

import java.util.Optional;

public enum CharactersSettings {
	MAIN_CHARACTER(32, 48, new Velocity(10, 20, 10)),
	BASIC_ENEMY(32, 48, new Velocity(7, 15, 10)),
	BULLET(32, 32, new Velocity(20, 35, 5)),
	BONUS(32, 32, null),
	WALL(32, 32, null);
	
	int width;
	int height;
	Optional<Velocity> speed;
	
	private CharactersSettings(int width, int height, Velocity speed){
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
