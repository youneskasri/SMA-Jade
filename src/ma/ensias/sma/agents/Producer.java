package ma.ensias.sma.agents;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

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
public class Producer extends Agent {
		
	private ProducerGUI window;
	private Product product;
	private List<String> consumersNames = new ArrayList<>();
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
	
	public static void main(String[] args) {
		// new Producer().setup(); // For Tests
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
	
	public void saveAnOrder(Order order) {
		order.setProduct(product);
		orders.add(order);
		
		int totalQuantitySold = 0; 
		double amountOfProfit = 0;
		
		for(Order o : orders) {
			totalQuantitySold += o.qmax;
			amountOfProfit += o.pmax * o.qmax;
		}
		
		window.updateProductReport(totalQuantitySold, amountOfProfit);
	}
}