package ma.ensias.sma.beans;

import java.io.Serializable;

public class Order implements Serializable {

	private Product product;
	/** Le prix maximum que le consommateur est prêt à payer */
	public double pmax;
	/** La valeur de la "gourmandise" */
	public double qmax;
	
	public Order(Product product, double pmax, double qmax) {
		this.product = product;
		this.pmax = pmax;
		this.qmax = qmax;
	}
	
	public String toString() {
		return "Pmax="+pmax+", Qmax="+qmax;
	}

	public void setProduct(Product product) {
		this.product = product;		
	}
}
