package guc.supermarket.products;

public  class Beverage extends GroceryProduct {

	private SugarLevel sugarLevel;
	
	public Beverage(String name, double price, double discount , SugarLevel ProductSugarLevel) {
		super(name, price, discount);
		this.sugarLevel = ProductSugarLevel;
	}

	public String toString() {
		return super.toString() +"Sugar Level: " + this.sugarLevel;
	}
	
	public static void main(String[] args) {
		Beverage bev = new Beverage("Coke Zero" , 5.0, 12.5 ,SugarLevel.ZERO);
		System.out.println(bev.getActualPrice());
		System.out.println(bev);
		
	}
	public SugarLevel getSugarLevel() {
		return this.sugarLevel;
	}
	public void setSugarLevel(SugarLevel newProductSugarLevel) {
		 this.sugarLevel = newProductSugarLevel;
	}
	
}
