package game.engine.base;

public class Wall implements game.engine.interfaces.Attackee {
	private int baseHealth;
	private int currentHealth;
	
	public Wall(int baseHealth) {
		this.baseHealth = baseHealth;
	
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getBaseHealth() {
		return baseHealth;
	}
	
	public int getResourcesValue() {
		return -1;
	}
	
	

}
