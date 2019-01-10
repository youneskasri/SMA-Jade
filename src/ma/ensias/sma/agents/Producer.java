package ma.ensias.sma.agents;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ma.ensias.agents.env.JadeContainer;
import ma.ensias.sma.beans.Product;
import ma.ensias.sma.services.ProducerService;
import ma.ensias.sma.services.ProducerServiceImpl;
import ma.ensias.sma.views.ProducerGUI;

/**
 * The Producer Agent offers a GUI
 * Is responsible for creating Consumer agents
 */
public class Producer extends Agent {
		
	private ProducerGUI window;
	private ProducerService producerService;
	private List<String> consumersNames;

	@Override
	protected void setup() {
		initDependencies();
		addBehaviours();
		showGUI();
	}
	
	private void after() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		new Producer().setup(); // For Tests
	}
	
	private void initDependencies() {
		producerService = new ProducerServiceImpl();
		consumersNames = new ArrayList<>();
	}
	
	
	private void addBehaviours() {
		addBehaviour(new OneShotBehaviour() {			
			@Override
			public void action() {
				System.out.println("Producer Agent Created !!");
			}
		});
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
		System.out.println(product.toString());
	}

	public int createConsumer() throws StaleProxyException {
		int numberOfConsumers = consumersNames.size() + 1;
		String consumerName = "Consommateur " + numberOfConsumers;
		ContainerController container = new JadeContainer().getContainer(); // this.getContainerController();
		if (container != null) {			
			container.createNewAgent(consumerName, Consumer.class.getName(), new Object[]{});
		} else {
			System.out.println("getContainerController() returns NULL !!");
		}
		consumersNames.add(consumerName);
		return numberOfConsumers;
	}
	
}