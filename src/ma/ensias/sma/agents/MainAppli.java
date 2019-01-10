package ma.ensias.sma.agents;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ma.ensias.agents.env.JadeContainer;
import ma.ensias.agents.env.MainContainer;

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
	}
}
