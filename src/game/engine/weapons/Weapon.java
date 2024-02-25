package game.engine.weapons;

public abstract class Weapon implements game.engine.interfaces.Attacker{
	
	private int baseDamage;

	
	public Weapon(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public int getDamage() {
		return this.baseDamage;
	}


	
	
}
