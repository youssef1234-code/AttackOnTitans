package game.engine.titans;

abstract public class Titan {
	private int baseHealth;
	private int currentHealth;
	private int baseDamage;
	private int heightInMeters;
	private int distanceFrombase;
	private int speed;
	private int resourceValue;
	private int dangerLevel;
	
	public	Titan (int baseHealth, int baseDamage, int heightINMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
		this.baseHealth = baseHealth;
	}

}
