package game.engine.weapons;

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
		int tot = 0;
		PriorityQueue<Titan> temp = new PriorityQueue<Titan>();
		while (!laneTitans.isEmpty()) {
			Titan beingDefeated = laneTitans.poll();
			int rem = beingDefeated.getDistance();
			if (rem >= this.getMinRange() && rem <= this.getMaxRange())
				beingDefeated.takeDamage(this.getDamage());
			if (beingDefeated.isDefeated())
				tot += beingDefeated.getResourcesValue();
			else
				temp.add(beingDefeated);
		}
		while (!temp.isEmpty())
			laneTitans.add((Titan) temp.poll());
		return tot;
	}

}
