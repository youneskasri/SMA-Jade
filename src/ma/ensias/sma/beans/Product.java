package ma.ensias.sma.beans;

public class Product {

	private String name;
	private double unitPrice;
	private double unitCost;
	
	public Product(String productName, Double unitPrice, Double unitCost) {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double price) {
		this.unitPrice = price;
	}

	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}	
}
