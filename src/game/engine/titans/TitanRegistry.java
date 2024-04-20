package game.engine.titans;
public class TitanRegistry {
	
	private final int code;
	private int baseHealth;
	private int baseDamage;
	private int heightInMeters;
	private int speed;
	private int resourcesValue;
	private int dangerLevel;
	
	public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed,int resourcesValue, int dangerLevel) {
		this.code = code;
		this.baseHealth = baseHealth;
		this.baseDamage = baseDamage;
		this.heightInMeters = heightInMeters;
		this.speed = speed;
		this.resourcesValue = resourcesValue;
		this.dangerLevel = dangerLevel;
	}
	public int getCode() {
		return this.code;
	}

	public int getBaseHealth() {
		return this.baseHealth;
	}

	public int getBaseDamage() {
		return this.baseDamage;
	}

	public int getHeightInMeters() {
		return this.heightInMeters;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getResourcesValue() {
		return this.resourcesValue;
	}

	public int getDangerLevel() {
		return this.dangerLevel;
	}
	
	public Titan spawnTitan(int distanceFromBase) {
		switch(this.code) {
			case(1): return new PureTitan(this.baseHealth,this.baseDamage,this.heightInMeters,distanceFromBase,this.speed,this.resourcesValue,this.dangerLevel);
			case(2): return new AbnormalTitan(this.baseHealth,this.baseDamage,this.heightInMeters,distanceFromBase,this.speed,this.resourcesValue,this.dangerLevel);
			case(3): return new ArmoredTitan(this.baseHealth,this.baseDamage,this.heightInMeters,distanceFromBase,this.speed,this.resourcesValue,this.dangerLevel);
			case(4): return new ColossalTitan(this.baseHealth,this.baseDamage,this.heightInMeters,distanceFromBase,this.speed,this.resourcesValue,this.dangerLevel);
		}
		return null;
	}	
}
