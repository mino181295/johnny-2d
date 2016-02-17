package it.unibo.oop.utilities;

public enum CharactersSettings {
	MAIN_CHARACTER(32,32,new Velocity(30,100, 10)),
	BASIC_ENEMY(32,32,new Velocity(30,100, 10)),
	BULLET(32,32,new Velocity(30,100, 10)),
	BONUS(32,32,null),
	WALL(32,32,null);
	
	int width;
	int height;
	Velocity speed;
	
	private CharactersSettings(int width, int height, Velocity speed){
		this.width = width;
		this.height = height;
		this.speed = speed;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Velocity getSpeed() {
		return this.speed;
	}
	
}
