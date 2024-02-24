package game.engine.titans;


abstract public class Titan implements Comparable {
	
	private final int baseHealth;
	private int currentHealth;
	private int baseDamage;
	private int heightInMeters;
	private int distanceFromBase;
	private int speed;
	private int resourcesValue;
	private int dangerLevel;
	
	public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int
			speed, int resourcesValue, int dangerLevel) {
		
		this.baseHealth       = baseHealth;
		this.currentHealth    = baseHealth;
		this.baseDamage       = baseDamage;
		this.heightInMeters   = heightInMeters;
		this.distanceFromBase = distanceFromBase;
		this.speed            = speed;
		this.resourcesValue   = resourcesValue;
		this.dangerLevel      = dangerLevel;
	}
	
	public int compareTo(Titan o) {
		return this.distanceFromBase - o.distanceFromBase;
	}
	s
	
	

}
