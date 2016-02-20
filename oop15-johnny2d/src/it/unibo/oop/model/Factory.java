package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;
import static it.unibo.oop.utilities.CharactersSettings.MAIN_CHARACTER;
import static it.unibo.oop.utilities.CharactersSettings.WALL;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public class Factory {
	
	public static class WallFactory {
				
		public static List<Wall> generateHorizontalWall(int startingX, int startingY, int dimension){
			List<Wall> resultList = new ArrayList<>(dimension);
			for(int offset = 0; offset < WALL.getWidth()*dimension; offset++ ){
				resultList.add(new Wall(offset + WALL.getWidth()/2, startingY));
			}			
			return resultList;
		}
		
		public static List<Wall> generateVerticalWall(int startingX, int startingY, int dimension){
			List<Wall> resultList = new ArrayList<>(dimension);
			for(int offset = 0; offset < WALL.getHeight()*dimension; offset++ ){
				resultList.add(new Wall(startingX, offset + WALL.getHeight()/2));
			}			
			return resultList;
		}
		
		public static Arena generateArena(int panelHeight, int panelWidth){
			//TODO settare l'hud dimension
			return new Arena(panelHeight,panelWidth,(int)(panelHeight*0.2));
		}
	}
	
	public static class MainCharacterFactory {
		public static MainCharacter generateCentredCharacter(double panelHeight, double panelWidth){
			return new MainCharacter();
		}
		
		public static MainCharacter generateCentredCharacter(Position centerPos){
			return new MainCharacter(centerPos.getX(), centerPos.getY());
		}
		
		public static MainCharacter generateStillCharacter(double startingX, double startingY){
			return new MainCharacter(startingX,startingY);
		}
		
		public static MainCharacter generateMovingCharacter(double startingX, double startingY, Vector2 movement){
			return new MainCharacter(startingX,startingY,movement,MAIN_CHARACTER.getSpeed());
		}
	}
	
	public static class EnemiesFactory {
		public static BasicMonster generateStillBasicEnemy(double intialX, double intialY){
			return new BasicMonster(intialX, intialY, new Vector2(), BASIC_ENEMY.getSpeed());
		}
	}
	
	public static class BulletFactory {
		public static Bullet shootBulletFromCharacter(MainCharacter mainChar){
			return new Bullet(mainChar);
		}
	}

}
