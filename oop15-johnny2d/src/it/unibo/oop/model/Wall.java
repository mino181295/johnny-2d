package it.unibo.oop.model;

import java.awt.Rectangle;

import it.unibo.oop.utilities.Position;

public class Wall extends AbstractEntity{

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	public Position getTopLeftCorner(){
		//TODO
		return null;
	}

	@Override
	protected int getEntityHeight() {
		return 0;
	}

	@Override
	protected int getEntityWidth() {
		
		return 0;
	}

}
