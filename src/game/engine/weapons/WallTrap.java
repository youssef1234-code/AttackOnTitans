package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class WallTrap extends Weapon{
	public static final int WEAPON_CODE = 4;

	public WallTrap(int baseDamage) {
		super(baseDamage);
		
	}
	
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		if(!laneTitans.isEmpty()) {
			Titan beingDefeated = laneTitans.poll();
			if(beingDefeated.hasReachedTarget())
				beingDefeated.takeDamage(this.getDamage());
			
			if(beingDefeated.isDefeated())
				return beingDefeated.getResourcesValue();
			else
				laneTitans.add(beingDefeated);
		}
		return 0;	
	}
	
}
