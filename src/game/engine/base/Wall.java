package game.engine.base;

public class Wall implements game.engine.interfaces.Attackee {
	private final int baseHealth;
	private int currentHealth;
	
	public Wall(int baseHealth) {
		this.baseHealth = baseHealth;
		this.currentHealth = baseHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		if(currentHealth < 0 ) 
			this.currentHealth = 0;
		else
			this.currentHealth = currentHealth;
	}

	public int getBaseHealth() {
		return baseHealth;
	}
	
	public int getResourcesValue() {
		return -1;
	}
	
	

}
