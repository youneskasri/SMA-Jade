package ma.ensias.sma.agents;

import ma.ensias.sma.beans.Product;

public interface IConsumer {
	/** Constante pour simuler une demande linéaire */
	static final int DEMAND_CONSTANT = 10;

	/** Consumer peut ajouter entre 0 et 50% pour un produit*/
	static final double MAX_AUGMENTATION = 0.50;
	
	/** Consumer se permet d'utiliser un budget maximum
	 *  pour un produit donné */
	static final double MAX_BUDGET = 1000;
	
	/** La demande est linéaire :
	 *  <b>qté = m * prix + CTE</b>, avec m la pente
	 *  tq: <b>m = -qmax/pmax</b>
	 *  avec <b>Qté >= 0</b> */
	public int getDesiredQuantityOf(Product product);
}
