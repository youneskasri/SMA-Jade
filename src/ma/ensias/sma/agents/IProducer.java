package ma.ensias.sma.agents;

import jade.wrapper.StaleProxyException;
import ma.ensias.sma.beans.Order;
import ma.ensias.sma.beans.Product;

public interface IProducer {

	/** Sends product info to all customers
	 * ! replaces old product and clear old orders */
	void advertiseProduct(Product product);
	
	/** creates a new consumer agent, and registers its name 
	 * @throws StaleProxyException */
	int createConsumer() throws StaleProxyException;
	
	/** if Consumer wants 1 or more => Register order and Update Selling report */
	void saveOrderIfBuyerCanAfford(Order order, String consumerName);
}
