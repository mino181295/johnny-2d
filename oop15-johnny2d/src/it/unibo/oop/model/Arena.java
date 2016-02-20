package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.WALL;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.oop.utilities.Position;

public class Arena {
	
	private static final int EMPTY_SPACES=1;
		
	private final List<Wall> boundsList;
	private final Rectangle playableRectangle;
	
	private List<Position> spawnPoints;
		

	public Arena(int panelHeight, int panelWidth, int hudDimension) {
		//Settings the arena walls
		this.boundsList = new ArrayList<>();
		int drawableAreaHeight = panelHeight - hudDimension;
		
		int heightRest = drawableAreaHeight%WALL.getHeight();
		int widthRest = panelWidth%WALL.getWidth();
		
		int heightBlocks = drawableAreaHeight/WALL.getHeight();
		int widthBlocks = panelWidth/WALL.getWidth();
		//Creation of externs walls
		for (int offsetX = EMPTY_SPACES; offsetX < widthBlocks-EMPTY_SPACES; offsetX++){
			for (int offsetY = EMPTY_SPACES; offsetY < heightBlocks-EMPTY_SPACES; offsetY++){
				if (offsetX==EMPTY_SPACES || offsetY==EMPTY_SPACES || offsetX==widthBlocks-1-EMPTY_SPACES || offsetY == heightBlocks-1-EMPTY_SPACES){
					this.boundsList.add(new Wall(widthRest/2 + offsetX * WALL.getWidth() + WALL.getWidth()/2, 
											hudDimension + heightRest/2 +offsetY * WALL.getHeight() + WALL.getHeight()/2 ));
				}
			}
		}
		//Getting playable rectangle
		Position topLeftCorner = this.boundsList.get(0).getPosition();				
		this.playableRectangle = new Rectangle(topLeftCorner.getIntX()+(int)WALL.getWidth()/2,topLeftCorner.getIntY()+(int)WALL.getHeight()/2
										, (int)(widthBlocks-2*EMPTY_SPACES)*WALL.getWidth(), (int)(heightBlocks-2*EMPTY_SPACES)*WALL.getHeight());
		
		this.spawnPoints = new ArrayList<>();
	
	
	}
	
	public List<Wall> getBoundsList() {
		return this.boundsList;
	}


	public Rectangle getPlayableRectangle() {
		return this.playableRectangle;
	}


	public List<Position> getSpawnPoints() {
		return this.spawnPoints;
	}
	
	public void addSpawnPoint(Position spawnPosition){
		this.spawnPoints.add(spawnPosition);
	}
	
	public Position getRandomSpawnPoint(){
		Random tmpRandom = new Random(this.spawnPoints.size());
		return this.spawnPoints.get(tmpRandom.nextInt());
	}
	
	

}
