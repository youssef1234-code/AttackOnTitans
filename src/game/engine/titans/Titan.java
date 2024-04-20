package game.engine.titans;


 public abstract class Titan implements Comparable<Titan>,game.engine.interfaces.Attackee,game.engine.interfaces.Attacker,game.engine.interfaces.Mobil {
	
	private final int baseHealth;
	private final int heightInMeters;
	private final int baseDamage;
	private final int dangerLevel;
	private final int resourcesValue;
	private int currentHealth;
	private int distanceFromBase;
	private int speed;
	
	
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
	
	public int getBaseHealth() {
		return baseHealth;
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
		if(health <0) 
			this.currentHealth = 0;
		else 
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
		if(distance < 0 ) {
			this.distanceFromBase = 0;
			return;
		} 
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
