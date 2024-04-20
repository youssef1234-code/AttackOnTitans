package game.engine.weapons;

import java.util.ArrayList;
import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class VolleySpreadCannon extends Weapon {
	public static final int WEAPON_CODE = 3;
	private final int minRange;
	private final int maxRange;

	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
		super(baseDamage);
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public int turnAttack(PriorityQueue<Titan> laneTitans) {
			int res = 0;
			ArrayList<Titan> temp = new ArrayList<Titan>();
			while(!laneTitans.isEmpty()) {
				Titan beingDefeated = laneTitans.poll();
				int rem = beingDefeated.getDistance();
				if (rem >= this.getMinRange() && rem <= this.getMaxRange())
					beingDefeated.takeDamage(this.getDamage());
				if(beingDefeated.isDefeated())
					res+= beingDefeated.getResourcesValue();
				else
					temp.add(beingDefeated);
			}
			for(int i=0;i<temp.size();i++) {
				laneTitans.add(temp.get(i));
			}
			return res;		
	}

}
