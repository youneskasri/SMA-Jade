package ma.ensias.sma.agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import ma.ensias.sma.beans.Product;
import ma.ensias.sma.behaviors.ReceiveAndOrderProductsBehavior;

public class Consumer extends Agent implements IConsumer {
	
	@Override
	protected void setup() {

		addBehaviour(new OneShotBehaviour() {			
			@Override
			public void action() {
				System.out.println("new Consumer agent Created !!");
			}
		});
		
		addBehaviour(new ReceiveAndOrderProductsBehavior());
	}

	public int getDesiredQuantityOf(Product product) {
		double pmax = getPmax(product.getUnitPrice());
		int qmax = getQmax(product.getUnitPrice());
		double m = -qmax/pmax;
		double p = product.getUnitPrice();
		int q = (int) (Math.round(m*p + DEMAND_CONSTANT));
		return q>0 ? q:0;
	}
	
	/** Consumer se permet d'utiliser son budget jusqu'à 
	 * MAX_BUDGET * (1-rand) pour acheter produit
	 * Qmax = La valeur de la 'Gourmandise'
	 */
	private int getQmax(double productPrice) {
		return  (int)Math.round(MAX_BUDGET*(1-Math.random()) / productPrice);
	}

	/** Consumer peut ajouter jusqu'à MAX_AUGMENTATION %
	 * Pmax = Le prix maximum que le consommateur est prêt à payer
	 */
	private double getPmax(double productPrice) {
		double augmentation = Math.random();
		while (augmentation > MAX_AUGMENTATION) augmentation = Math.random();
		return productPrice * ( 1 + augmentation );
	}
	
}



