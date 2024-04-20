package game.engine.weapons.factory;

import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;

import java.io.IOException;
import java.util.HashMap;

import game.engine.weapons.*;

public class WeaponFactory {
	private final HashMap<Integer,WeaponRegistry> weaponShop;

	public WeaponFactory() throws IOException{
		weaponShop = DataLoader.readWeaponRegistry();
		
	}

	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}

	public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException{
			WeaponRegistry wp = this.weaponShop.get(weaponCode);
			if(wp == null) 
				return null;
			if(wp.getPrice() > resources)
				throw new InsufficientResourcesException(resources);
			if(weaponCode>=1 && weaponCode<=4)
				return new FactoryResponse(wp.buildWeapon(),resources - wp.getPrice());
			return null;
	}
	
	public void addWeaponToShop(int code, int price) {
		if(code >=1 && code<=4)
			weaponShop.put(code, new WeaponRegistry(code,price));
	}
	
	public  void addWeaponToShop(int code, int price, int damage, String name) {
		if(code >=1 && code<=4 && code!=3)
		weaponShop.put(code, new WeaponRegistry(code,price,damage,name));
	}
	
	public  void addWeaponToShop(int code, int price, int damage, String name,int minRange, int maxRange) {
		if(code >=1 && code<=4 && code==3)
		weaponShop.put(code, new WeaponRegistry(code,price,damage,name,minRange,maxRange));
	}
	
}
