package ma.ensias.sma.beans;

import java.io.Serializable;

public class Order implements Serializable {

	private Product product;
;	
	/** La quantitée desirée par Consumer */
	private int quantity;
		
	public Order(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public String toString() {
		return "Quantity="+quantity;
	}

	public void setProduct(Product product) {
		this.product = product;		
	}

	public int getQuantity() {
		return quantity;
	}
	
	public Product getProduct() {
		return product;
	}
}
