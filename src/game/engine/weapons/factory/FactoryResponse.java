package game.engine.weapons.factory;

import game.engine.weapons.Weapon;

public class FactoryResponse {
	
	private final Weapon weapon;
	private final int remainigResources;
	
	public FactoryResponse(Weapon weapon, int remainigResources) {
		
		this.weapon = weapon;
		this.remainigResources = remainigResources;
	}
	
	
	

}
