package game.engine.titans;


 public abstract class Titan implements Comparable<Titan>,game.engine.interfaces.Attackee,game.engine.interfaces.Attacker,game.engine.interfaces.Mobil {
	
	private final int baseHealth ;
	private final int heightInMeters;
	private final int dangerLevel;
	private int currentHealth;
	private int baseDamage;
	private int distanceFromBase;
	private int speed;
	private int resourcesValue;
	
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
		return this.getDistance() - o.getDistance();
	}
	
	public int getBaseDamage() {
		return this.baseDamage;
	}
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	
	public void setCurrentHealth(int health) {
		this.currentHealth = health;
	}
	
	public int getDamage() {
		return this.baseDamage;
	}
	
	public int getHeightInMeters() {
		return this.heightInMeters;
	}
	
	public int  getDistance() {
		return this.distanceFromBase;
	}
	
	public void setDistance(int distance) {
		this.distanceFromBase = distance;
	}
	
	public int  getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getResourcesValue() {
		return this.resourcesValue;
	}

	public int getDangerLevel() {
		return this.dangerLevel;
	}
	
	
}
