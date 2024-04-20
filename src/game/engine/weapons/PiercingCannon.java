package game.engine.weapons;

import java.util.ArrayList;
import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class PiercingCannon extends Weapon {
	
	public static final int WEAPON_CODE = 1;
	
	public PiercingCannon(int baseDamage) {
		super(baseDamage);	
	}

	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int totalResources = 0;
		ArrayList <Titan> tempStore = new ArrayList<Titan>();
		int size = laneTitans.size();
		for(int i=0;i< size && i < 5;i++) {
			
			Titan beingDefeated = laneTitans.poll();
			beingDefeated.takeDamage(this.getDamage());
			
			if(beingDefeated.isDefeated())
				totalResources += beingDefeated.getResourcesValue();
			else
				tempStore.add(beingDefeated);

		}
		
		for(int i = 0; i < tempStore.size(); i++)
			laneTitans.add((Titan)tempStore.get(i));
		
		return totalResources;		
	}
	
	
}
