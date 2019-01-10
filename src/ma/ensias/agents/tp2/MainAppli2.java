package ma.ensias.agents.tp2;

import java.util.HashMap;
import java.util.Map;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ma.ensias.agents.env.JadeContainer;
import ma.ensias.agents.env.MainContainer;

public class MainAppli2 {
	ContainerController mainContainer, container;
	AgentController agentAcheteur, agentVendeur;
	String[][] titres = {
			{"Java BIEN !", "Php", "MySQL"},
			{"Java BIEN !", "Php", "MySQL"},
			{"Java BIEN !", "Php", "MySQL"},
			{"Java BIEN !", "Php", "MySQL"}
	};
	
	double[][] prix = {
			{100, 200, 300},
			{110, 200, 360},
			{96, 220, 380},
			{98, 280, 340}
	};
	
	public static void main(String[] args) throws StaleProxyException {
		new MainAppli2();
	}
	
	public MainAppli2() throws StaleProxyException {
		mainContainer = new MainContainer().getContainer();
		container = new JadeContainer().getContainer();
		
		AgentController agentAcheteur = container
				.createNewAgent("acheteur", "ma.ensias.agents.tp2.Acheteur2", 
						new Object[] {"Java BIEN !", 90.0});		
		agentAcheteur.start();
		
		
		for (int i =0; i<4; i++) {
			Map<String, Double> catalogue = new HashMap<String, Double>(10);
			for(int j =0; j<titres[0].length; j++) {
				catalogue.put(titres[i][j], prix[i][j]);
			}
			
			AgentController agentVendeur = container.createNewAgent("Vendeur"+i, "ma.ensias.agents.tp2.Vendeur2",
					new Object[] { catalogue });
			agentVendeur.start();
		}
		
	}
}
