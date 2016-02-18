package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.*;

import java.util.ArrayList;
import java.util.List;

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
			int hudDimension = (int) (panelHeight * 0.3);
			int drawableAreaHeight = panelHeight - hudDimension;
			
			int heightRest = drawableAreaHeight%WALL.getHeight();
			int widthRest = panelWidth%WALL.getWidth();
			
			int heightBlocks = drawableAreaHeight/WALL.getHeight();
			int widthBlocks = panelWidth/WALL.getWidth();
			
			for (int offsetX = 1; offsetX < heightBlocks-1; offsetX++){
				for (int offsetY = 1; offsetY < widthBlocks-1; offsetY++){
					resultList.add(new Wall(widthRest/2 + offsetX * WALL.getWidth() + WALL.getWidth()/2, heightRest/2 +offsetY * WALL.getHeight() + WALL.getHeight()/2 ));
				}
			}			
			return resultList;
		}
	}
	
	public static class MainCharacterFactory {
		public static MainCharacter generateCentredCharacter(int panelHeight, int panelWidth){
			return new MainCharacter();
		}
		
		public static MainCharacter generateCharacter(int startingX, int startingY){
			return new MainCharacter(startingX,startingY);
		}
	}
	
	public static class EnemiesFactory {
		public static BasicMonster generateBasicEnemy(int xPos, int yPos){
			return null;
		}
	}
	
	public static class BulletFactory {
		public static Bullet generateBullet(int xPos, int yPos){
			return null;
		}
	}

}
