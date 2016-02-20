package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;
import static it.unibo.oop.utilities.CharactersSettings.MAIN_CHARACTER;
import static it.unibo.oop.utilities.CharactersSettings.WALL;

import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public class Factory {
	
	public static class WallFactory {
		
		private WallFactory() {
		}
				
		public static List<Wall> generateHorizontalWall(final int startingX, final int startingY, final int dimension){
			final List<Wall> resultList = new ArrayList<>(dimension);
			for(int offset = 0; offset < WALL.getWidth()*dimension; offset++ ){
				resultList.add(new Wall(offset + WALL.getWidth()/2, startingY));
			}			
			return resultList;
		}
		
		public static List<Wall> generateVerticalWall(final int startingX, final int startingY, final int dimension){
			final List<Wall> resultList = new ArrayList<>(dimension);
			for(int offset = 0; offset < WALL.getHeight()*dimension; offset++ ){
				resultList.add(new Wall(startingX, offset + WALL.getHeight()/2));
			}			
			return resultList;
		}
		
		public static Arena generateArena(final int panelHeight, final int panelWidth){
			//TODO settare l'hud dimension
			return new Arena(panelHeight,panelWidth,(int)(panelHeight*0.2));
		}
	}
	
	public static class MainCharacterFactory {
		
		private MainCharacterFactory() {
		}
		
		public static MainCharacter generateCentredCharacter(final double panelHeight, final double panelWidth){
			return new MainCharacter();
		}
		
		public static MainCharacter generateCentredCharacter(Position centerPos){
			return new MainCharacter(centerPos.getX(), centerPos.getY());
		}
		
		public static MainCharacter generateStillCharacter(double startingX, double startingY){

		public static MainCharacter generateStillCharacter(final double startingX, final double startingY){

			return new MainCharacter(startingX,startingY);
		}
		
		public static MainCharacter generateMovingCharacter(final double startingX, final double startingY, final Vector2 movement){
			return new MainCharacter(startingX,startingY,movement,MAIN_CHARACTER.getSpeed());
		}
	}
	
	public static class EnemiesFactory {
		
		private EnemiesFactory() {
		}
		
		public static BasicMonster generateStillBasicEnemy(final double intialX, final double intialY){
			return new BasicMonster(intialX, intialY, new Vector2(), BASIC_ENEMY.getSpeed());
		}
	}
	
	public static class BulletFactory {
		
		private BulletFactory() {
		}
		
		public static Bullet shootBulletFromCharacter(final MainCharacter mainChar){
			return new Bullet(mainChar);
		}
	}
<<<<<<< local

=======
	
	public static class PositionFactory {
		
		private PositionFactory() {
		}
		
		public static Position generateRandomPsition(final int maxX, final int maxY){
			final Random nextRandom = new Random();
			return new Position(nextRandom.nextInt(maxX), nextRandom.nextInt(maxY));
		}
	}
>>>>>>> other
}
