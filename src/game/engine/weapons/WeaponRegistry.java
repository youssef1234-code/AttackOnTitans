package game.engine.weapons;

public class WeaponRegistry {
	private final int code;
	private int price;
	private int damage;
	private String name;
	private int minRange;
	private int  maxRange;
	
	
	public WeaponRegistry(int code, int price) {
		this.code = code;
		this.price = price;
	}

	public WeaponRegistry(int code, int price, int damage, String name) {
		
		this.code = code;
		this.price = price;
		this.damage = damage;
		this.name = name;
	}

	public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange) {
		this.code = code;
		this.price = price;
		this.damage = damage;
		this.name = name;
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public int getCode() {
		return code;
	}

	public int getPrice() {
		return price;
	}

	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}
	
	
	public Weapon buildWeapon() {
		switch(this.code) {
			case(1): return new PiercingCannon(this.damage); 
			case(2): return new SniperCannon(this.damage);
			case(3): return new VolleySpreadCannon(this.damage,this.minRange,this.maxRange);
			case(4): return new WallTrap(this.damage);
		}
		return null;
	}	
	

}
