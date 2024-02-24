package game.engine.interfaces;
//Interface containing the methods available to all objects that gets attacked within thegame.
public interface Attackee {
	
	public int getCurrentHealth();
	
	public void setCurrentHealth(int health);
	
	public int getResourcesValue();
	
} 
