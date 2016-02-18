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
		
		public static List<Wall> generateArena(int panelHeight, int panelWidth){
			List<Wall> resultList = new ArrayList<>();
			int hudDimension = (int) (panelHeight * 0.3);  //HUD DIMENSION
			int drawableAreaHeight = panelHeight - hudDimension;
			
			int heightRest = drawableAreaHeight%WALL.getHeight();
			int widthRest = panelWidth%WALL.getWidth();
			
			int heightBlocks = drawableAreaHeight/WALL.getHeight();
			int widthBlocks = panelWidth/WALL.getWidth();
			
			for (int offsetX = 1; offsetX < widthBlocks-1; offsetX++){
				for (int offsetY = 1; offsetY < heightBlocks-1; offsetY++){
					resultList.add(new Wall(widthRest/2 + offsetX * WALL.getWidth() + WALL.getWidth()/2, 
											hudDimension + heightRest/2 +offsetY * WALL.getHeight() + WALL.getHeight()/2 ));
				}
			}			
			return resultList;
		}
	}
	
	public static class MainCharacterFactory {
		public static MainCharacter generateCentredCharacter(double panelHeight, double panelWidth){
			return new MainCharacter();
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
		public static Bullet generateBullet(int xPos, int yPos){
			//TODO
			return null;
		}
	}
	
	public static class PositionFactory {
		public static Position generateRandomPsition(int xPos, int yPos){
			//TODO
			return null;
		}
	}

}
