package game.engine.titans;
import  game.engine.interfaces.*;
public class ArmoredTitan extends Titan implements Attackee{
	
	public static final int TITAN_CODE = 3;
	
	public ArmoredTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
		super( baseHealth, baseDamage,heightInMeters,distanceFromBase , speed,resourcesValue,dangerLevel);
	}
		
	@Override
	public  int takeDamage(int damage) {
		if(damage>=0)
			this.setCurrentHealth((int)Math.ceil((this.getCurrentHealth()-0.25*damage)));
		if(this.isDefeated())
			return this.getResourcesValue();
		return 0;
	}
	
}