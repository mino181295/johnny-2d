package it.unibo.oop.model;

public abstract class AbstractBonus extends AbstractEntity {
	
	abstract void applyBonus();
	
	public void collectBonus(){
		this.applyBonus();
	}

}
