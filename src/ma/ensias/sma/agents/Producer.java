package ma.ensias.sma.agents;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import ma.ensias.sma.beans.Product;
import ma.ensias.sma.services.ProducerService;
import ma.ensias.sma.services.ProducerServiceImpl;
import ma.ensias.sma.views.ProducerGUI;

public class Producer extends Agent {
		
	ProducerGUI window;
	private ProducerService producerService;
	private List<Agent> consumers;

	@Override
	protected void setup() {
		initDependencies();
		addBehaviours();
		showGUI();
	}
	
	public static void main(String[] args) {
		new Producer().setup();
	}
	
	private void initDependencies() {
		producerService = new ProducerServiceImpl();
		consumers = new ArrayList<>();
	}
	
	
	private void addBehaviours() {
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				// TODO Auto-generated method stub
				
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
		// TODO Auto-generated method stub
		
	}


	static int n = 0;
	public int createConsumer() {
		// TODO Auto-generated method stub
		return ++n;
	}
	
}