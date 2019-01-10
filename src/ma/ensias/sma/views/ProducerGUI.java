package ma.ensias.sma.views;

import java.util.ArrayList;
import java.util.List;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class ProducerGUI {

	private ContainerController container;
	private AgentController producerAgent;
	private List<AgentController> consumersAgents;
	
	public ProducerGUI(ContainerController container) throws StaleProxyException {
		this.container = container;
		this.consumersAgents = new ArrayList<>();
		
		producerAgent = container
					.createNewAgent("Producteur", "ma.ensias.agents.Producer", null);		
	}
}
