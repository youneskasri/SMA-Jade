package ma.ensias.sma.beans;

public class Product {

	private String name;
	private double unitPrice;
	private double unitCost;
	
	public Product(String productName, Double unitPrice, Double unitCost) {
		this.name = productName;
		this.unitPrice = unitPrice;
		this.unitCost = unitCost;
	}
	
	public String getName() {
		return name;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}

	public double getUnitCost() {
		return unitCost;
	}	
	
	@Override
	public String toString() {
		return "ProductName="+name+", UP="+unitPrice+", UC="+unitCost;
	}
}
