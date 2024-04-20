package game.engine.interfaces;
//Interface containing the methods available to all objects that gets attacked within thegame.
public interface Attackee {
	
	public int getCurrentHealth();
	
	public void setCurrentHealth(int health);
	
	public int getResourcesValue();
	
	public default boolean isDefeated() {
		if(this.getCurrentHealth()<=0)
			return true;
		return false;
	}
	public default int takeDamage(int damage) {
		if(damage>=0)
			this.setCurrentHealth(this.getCurrentHealth()-damage);
		if(this.isDefeated())
			return this.getResourcesValue();
		return 0;
	}
} 
