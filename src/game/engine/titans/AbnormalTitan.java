package game.engine.titans;

import game.engine.interfaces.*;

public class AbnormalTitan extends Titan {

	public static final int TITAN_CODE = 2;

	public AbnormalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}

	@Override
	public int attack(Attackee target) {
		target.takeDamage(this.getDamage());
		if (!target.isDefeated())
			target.takeDamage(this.getDamage());
		if (target.isDefeated())
			return target.getResourcesValue();
		return 0;
	}

}
