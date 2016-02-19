package it.unibo.oop.model;
/**
 * Class that model the health of the {@link MainCharacter}
 * @author Matteo Minardi
 *
 */
public class Health {
	
	public static final int DEFAULT_HEALTH = 3;
	public static final int DEFAULT_MIN_HEALTH = 0;	
		
	private final int maxHealth;
	private final int minHealth;

	private int currentHealth;
	
	public Health(int minHealth, int maxHealth, int initialHealth){
		this.minHealth = minHealth;
		this.maxHealth = maxHealth;
		this.currentHealth = initialHealth;
	}
	
	public Health(int maxHealth){
		this(DEFAULT_MIN_HEALTH,maxHealth,maxHealth);
	}
	
	public Health(){
		this(DEFAULT_HEALTH);
	}
	/**
	 * Decrease the {@link Health} of the {@link MainCharacter} if it gets damaged by an {@link Enemy}
	 * @param damage The damage taken
	 */
	public void decreaseHealth(int damage){
		this.currentHealth -= damage; 
		currentHealth = (currentHealth < minHealth ? minHealth : currentHealth);
	}
	/**
	 * Increase the {@link Health} of the {@link MainCharacter} if he collects a special {@link Collectable}
	 * @param damage The damage taken
	 */
	public void increaseHealth(int heal){
		this.currentHealth += heal;
		currentHealth = (currentHealth > maxHealth ? maxHealth : currentHealth);
	}
	/**
	 * Special information of the {@link MainCharacter} remaining {@link Health}
	 * @return
	 */
	public int computePercentage(){
		double tmp = currentHealth/ (maxHealth-minHealth);
		return (int)(tmp*100);
	}
	
	public int getCurrentHealth(){
		return this.currentHealth;
	}
	/**
	 * This returns true if the {@link Health} is ended and the {@link MainCharacter} is dead
	 */
	public boolean isDead(){
		return (this.currentHealth <= minHealth);
	}
	
	

}
