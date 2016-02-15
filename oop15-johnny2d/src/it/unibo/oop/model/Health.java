package it.unibo.oop.model;

public class Health implements Feature {
	
	private final String HEALTH_TAG = "Health";
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

	public String getName() {
		return this.HEALTH_TAG;
	}
	
	public void decreaseHealth(int damage){
		this.currentHealth -= damage; 
		currentHealth = (currentHealth < minHealth ? minHealth : currentHealth);
	}
	
	public void increaseHealth(int heal){
		this.currentHealth += heal;
		currentHealth = (currentHealth > maxHealth ? maxHealth : currentHealth);
	}
	
	public int computePercentage(){
		double tmp = currentHealth/ (maxHealth-minHealth);
		return (int)(tmp*100);
	}
	
	public int getCurrentHealth(){
		return this.currentHealth;
	}
	
	public boolean isDead(){
		return (this.currentHealth == minHealth);
	}
	
	

}
