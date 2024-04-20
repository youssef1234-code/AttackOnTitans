package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class SniperCannon extends Weapon {
	public static final int WEAPON_CODE  = 2;

	public SniperCannon(int baseDamage) {
		super(baseDamage);
		
	}
	
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		if(!laneTitans.isEmpty()) {
			Titan beingDefeated = laneTitans.poll();
			beingDefeated.takeDamage(this.getDamage());
			if(beingDefeated.isDefeated())
				return beingDefeated.getResourcesValue();
			else
				laneTitans.add(beingDefeated);
		}
		return 0;		
	}
	
	

}
