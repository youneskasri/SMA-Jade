package ma.ensias.sma.agents;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ma.ensias.sma.beans.Order;
import ma.ensias.sma.beans.Product;
import ma.ensias.sma.behaviors.AdvertiseProductBehavior;
import ma.ensias.sma.behaviors.ReceiveOrdersBehavior;
import ma.ensias.sma.views.ProducerGUI;

/**
 * The Producer Agent offers a GUI
 * Is responsible for creating Consumer agents
 */
public class Producer extends Agent implements IProducer {
		
	private ProducerGUI window;
	private List<String> consumersNames = new ArrayList<>();
	
	/** Our latest product */
	private Product product;
	/** Remise à 0 for each new product */
	private List<Order> orders = new ArrayList<>();
	
	public Product getProduct() {
		return product;
	}
	public List<String> getConsumersNames() {
		return consumersNames;
	}
	
	@Override
	protected void setup() {
		addBehaviours();
		showGUI();
	}
	
	private void addBehaviours() {
		addBehaviour(new OneShotBehaviour() {			
			@Override
			public void action() {
				System.out.println("Producer Agent Created !!");
			}
		});	
		/* The Behavior to Send the Product is added 
		 * Dynamiccaly via the GUI 
		 * */
		addBehaviour(new ReceiveOrdersBehavior());
	}
	
	private void showGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ProducerGUI(Producer.this);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void advertiseProduct(Product product) {
		this.product = product;
		this.orders.clear();
		System.out.println("Advertising " + product.toString() + "...");
		addBehaviour(new AdvertiseProductBehavior());
	}

	public int createConsumer() throws StaleProxyException {
		int numberOfConsumers = consumersNames.size() + 1;
		String consumerName = "Consommateur" + numberOfConsumers;
		ContainerController container = this.getContainerController();
		if (container != null) {			
			AgentController consumer = container.createNewAgent(consumerName, 
					Consumer.class.getName(), new Object[]{});
			consumer.start();
		} else {
			System.out.println("getContainerController() returns NULL !!");
		}
		consumersNames.add(consumerName);
		return numberOfConsumers;
	}
	
	public void saveOrderIfBuyerCanAfford(Order order, String consumerName) {
		order.setProduct(product);
		if (order.getQuantity() < 1) {
			window.showAlert(consumerName+" don't want this product");
			return;
		}
		orders.add(order);
		window.showAlert(consumerName+" wants "+order.getQuantity()+" of "+product.getName());
		updateSellingReport();
	}
	

	/** QTE & BENEFICE = QTE*(PU-CU) for all Selling, then Update KPIs */
	private void updateSellingReport() {
		int totalQuantitySold = 0; 
		double amountOfProfit = 0;
		
		for(Order o : orders) {
			Product soldProduct = o.getProduct();
			totalQuantitySold += o.getQuantity();			
			amountOfProfit += o.getQuantity() * (soldProduct.getUnitPrice() - soldProduct.getUnitCost());
		}
		
		window.updateProductReport(totalQuantitySold, amountOfProfit);
	}

}