package ma.ensias.sma.agents;

import jade.core.Agent;
import ma.ensias.sma.services.ConsumerService;
import ma.ensias.sma.services.ConsumerServiceImpl;

public class Consumer extends Agent {

	private ConsumerService consumerService;
	
	@Override
	protected void setup() {
		consumerService = new ConsumerServiceImpl();
		
		// add Behaviours
	}
}
