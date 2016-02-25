package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.INVISIBLE_ENEMY;
import static it.unibo.oop.utilities.CharactersSettings.WALL;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Vector2;

public class Factory {
	
	public static class WallFactory {
		
		private static final double HUD_PERCENTAGE = 0.05;
		
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
			return new Arena(panelHeight,panelWidth,(int)(panelHeight*HUD_PERCENTAGE));
		}
	}
	
	public static class MainCharacterFactory {
		
		private MainCharacterFactory() {}
		
		public static MainCharacter generateCentredCharacter(final Rectangle panel){
			return new MainCharacter(panel.getCenterX(), panel.getCenterY());
		}
		public static MainCharacter generateStillCharacter(final double startingX, final double startingY){
			return new MainCharacter(startingX,startingY);
		}		
		public static MainCharacter generateMovingCharacter(final double startingX, final double startingY, final Vector2 movement){
			return new MainCharacter(startingX,startingY,movement);
		}
	}
	
	public static class EnemiesFactory {
		
		private EnemiesFactory() {}
		
		public static BasicMonster generateStillBasicEnemy(final double intialX, final double intialY){
			return new BasicMonster(intialX, intialY, new Vector2());
		}
		public static InvisibleMonster generateStillInvisibleEnemy(final double intialX, final double intialY){
			return new InvisibleMonster(intialX, intialY, new Vector2(), INVISIBLE_ENEMY.getSpeed());
		}
	}
	
	public static class BulletFactory {
		
		private BulletFactory() {	}
		
		public static Bullet shootBulletFromCharacter(final MainCharacter mainChar){
			return new Bullet(mainChar);
		}
		public static Bullet createBullet(final double startingX, final double startingY, final Vector2 movementVector){
			return new Bullet(startingX, startingY, movementVector);
		}
	}

}
