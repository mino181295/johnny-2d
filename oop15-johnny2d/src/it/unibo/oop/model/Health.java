package it.unibo.oop.model;

import it.unibo.oop.exceptions.OutOfHealthException;

public class Health implements Feature {
	
	private final String HEALTH_TAG = "Health";
	
		
	private final int maxHealth;
	private final int minHealth;

	private int currentHealth;
	
	public Health(int minHealth, int maxHealth, int initialHealth){
		this.minHealth = minHealth;
		this.maxHealth = maxHealth;
		this.currentHealth = initialHealth;
	}

	public String getName() {
		return this.HEALTH_TAG;
	}
	
	public void decreaseHealth(int damage) throws OutOfHealthException{
		this.currentHealth -= damage; 
		if (currentHealth < 0){			
			currentHealth = 0;
			throw new OutOfHealthException();			
		}
	}
	
	public void increaseHealth(int heal){
		this.currentHealth += heal;
		currentHealth = (currentHealth > maxHealth ? maxHealth : currentHealth);
	}
	
	public int computePercentage(){
		double tmp = currentHealth/ (maxHealth-minHealth);
		return (int)(tmp*100);
	}
	
	

}
