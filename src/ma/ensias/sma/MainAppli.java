package ma.ensias.sma;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ma.ensias.sma.agents.Producer;

public class MainAppli {
	ContainerController mainContainer, container;
	private AgentController producerAgent;
	
	
	public static void main(String[] args) throws StaleProxyException {
		new MainAppli();
	}
	
	public MainAppli() throws StaleProxyException {
		mainContainer = new MainContainer().getContainer();
		container = new JadeContainer().getContainer();
		producerAgent = container
				.createNewAgent("Producteur", Producer.class.getName(), new Object[]{});
		
		producerAgent.start();
		
		System.out.println("Created Producer : " + Producer.class.getName());
	}
}
