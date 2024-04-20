package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class PiercingCannon extends Weapon {
	
	public static final int WEAPON_CODE = 1;
	
	public PiercingCannon(int baseDamage) {
		super(baseDamage);	
	}

	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int totalResources = 0;
		PriorityQueue <Titan> tempStore = new PriorityQueue<Titan>();
		
		for(int i=1;i<=laneTitans.size();i++) {
			if(i>5) 
				break;
			
			Titan beingDefeated = laneTitans.poll();
			beingDefeated.takeDamage(this.getDamage());
			
			if(beingDefeated.isDefeated())
				totalResources += beingDefeated.getResourcesValue();
			else
				tempStore.add(beingDefeated);

		}
		
		while(!tempStore.isEmpty())
			laneTitans.add((Titan)tempStore.poll());
		
		return totalResources;		
	}
	
	
}
