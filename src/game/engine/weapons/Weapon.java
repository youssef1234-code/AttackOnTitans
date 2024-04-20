package game.engine.weapons;
import java.util.PriorityQueue;
import game.engine.titans.*;
public abstract class Weapon implements game.engine.interfaces.Attacker{
	
	private final int baseDamage;

	
	public Weapon(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
	public int getDamage() {
		return this.baseDamage;
	}

	
	public abstract int turnAttack(PriorityQueue<Titan> laneTitans);
	
}
