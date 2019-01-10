package ma.ensias.sma.agents;

import java.util.HashMap;
import java.util.Map;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ma.ensias.agents.env.JadeContainer;
import ma.ensias.agents.env.MainContainer;

public class MainAppli {
	ContainerController mainContainer, container;
	
	public static void main(String[] args) throws StaleProxyException {
		new MainAppli();
	}
	
	public MainAppli() throws StaleProxyException {
		mainContainer = new MainContainer().getContainer();
		container = new JadeContainer().getContainer();
	}
}
