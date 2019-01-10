package ma.ensias.sma.agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import ma.ensias.sma.services.ConsumerService;
import ma.ensias.sma.services.ConsumerServiceImpl;

public class Consumer extends Agent {

	private ConsumerService consumerService;
	
	@Override
	protected void setup() {
		consumerService = new ConsumerServiceImpl();
		
		addBehaviour(new OneShotBehaviour() {			
			@Override
			public void action() {
				System.out.println("new Consumer agent Created !!");
			}
		});
	}
}
