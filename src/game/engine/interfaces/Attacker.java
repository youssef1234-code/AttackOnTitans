package game.engine.interfaces;
//Interface containing the methods available to all attackers within the game.

public interface Attacker {
	public int getDamage();

	public default int attack(Attackee target) {
		 target.takeDamage(this.getDamage());
		 if(target.isDefeated())
			 return target.getResourcesValue();
		 return 0;
	}
}
