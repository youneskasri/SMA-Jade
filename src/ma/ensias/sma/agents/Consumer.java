package ma.ensias.sma.agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import ma.ensias.sma.behaviors.ReceiveAndOrderProductsBehavior;
import ma.ensias.sma.services.ConsumerService;
import ma.ensias.sma.services.ConsumerServiceImpl;

public class Consumer extends Agent {

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
}



