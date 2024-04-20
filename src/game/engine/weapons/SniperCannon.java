package game.engine.weapons;

import java.util.PriorityQueue;
import java.util.ArrayList;

import game.engine.titans.Titan;

public class SniperCannon extends Weapon {
	public static final int WEAPON_CODE  = 2;

	public SniperCannon(int baseDamage) {
		super(baseDamage);
		
	}
	
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int res = 0;
		if(laneTitans.isEmpty())
			return 0;
		
		Titan beingDefeated = laneTitans.poll();
		beingDefeated.takeDamage(this.getDamage());
		
		if(beingDefeated.isDefeated())
			res+= beingDefeated.getResourcesValue();
		else
			laneTitans.add(beingDefeated);
		
		return res;			
	}
	
	

}
