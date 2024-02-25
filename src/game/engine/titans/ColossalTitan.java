package game.engine.titans;

public class ColossalTitan extends Titan{
	
	private static final int TITAN_CODE = 4;
	
	public ColossalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
		super( baseHealth, baseDamage,heightInMeters,distanceFromBase , speed,resourcesValue,dangerLevel);
	}
	

}