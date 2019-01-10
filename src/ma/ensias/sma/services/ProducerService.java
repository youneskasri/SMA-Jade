package ma.ensias.sma.services;

import ma.ensias.sma.beans.Product;

public interface ProducerService {
	
	/** Créer un agent consommateur */
	void createConsumerAgent();
	
	/** Envoi détails du Produit aux Consommateurs */
	void advertise(Product product);
	
	/** Quantité totale vendue */
	int  getTotalQuantitySold();
	
	/** Calcul du Bénéfice */
	double calculateAmountOfProfit(Product product, int quantity);

	/** # of Consumer Agents */
	int getNumberOfConsumers();
}
