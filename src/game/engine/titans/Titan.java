package game.engine.titans;


abstract public class Titan implements Comparable{
	
	private int baseHealth;
	private int currentHealth;
	private int baseDamage;
	private int heightInMeters;
	private int distanceFrombase;
	private int speed;
	private int resourceValue;
	private int dangerLevel;
	
	public Titan(int baseHealth, int currentHealth, int baseDamage, int heightInMeters, int distanceFrombase, int speed,
			int resourceValue, int dangerLevel) {
		
		this.baseHealth = baseHealth;
		this.currentHealth = currentHealth;
		this.baseDamage = baseDamage;
		this.heightInMeters = heightInMeters;
		this.distanceFrombase = distanceFrombase;
		this.speed = speed;
		this.resourceValue = resourceValue;
		this.dangerLevel = dangerLevel;
	}
	
	public int compareTo(Titan o) {
		return this.distanceFrombase - o.distanceFrombase;
	}
	
	

}
