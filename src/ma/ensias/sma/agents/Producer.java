package ma.ensias.sma.agents;

import java.util.ArrayList;
import java.util.List;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import ma.ensias.sma.services.ProducerService;
import ma.ensias.sma.services.ProducerServiceImpl;

public class Producer extends Agent {
	
	private ProducerService producerService;
	private List<Agent> consumers;

	@Override
	protected void setup() {
		initDependencies();
		addBehaviours();		
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
	
}