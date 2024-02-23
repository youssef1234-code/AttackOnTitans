package guc.supermarket.products;

public class DairyProduct extends GroceryProduct{
	
	private Fat fat; 
	
	public DairyProduct(String name, double price, double discount,Fat ProductFat) {
		super(name, price, discount);
		this.fat = ProductFat;
	}
	
	public String toString() {
		return super.toString() +"FatLevel: " + this.fat ;
	}
	
	public Fat getFat() {
		return this.fat;
	}
	
	public void setFat(Fat newFat) {
		 this.fat = newFat;
	}
	
	public static void main(String [] args) {
		DairyProduct dp = new DairyProduct("Juhayna Milk",5.0,12.5,Fat.FULLCREAM);
		System.out.println(dp.getActualPrice());
		System.out.println(dp);
		System.out.println(1.2/100);
	}
	
}
