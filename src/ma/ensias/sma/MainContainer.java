package ma.ensias.sma;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.ContainerController;

public class MainContainer {

	private ContainerController mainContainer;
	
	public MainContainer() {
		// Créer une instance de la MV
		Runtime rt = Runtime.instance();		
		// Fixer quelques propriétés
		Properties p = new ExtendedProperties();
		// le GUI Jade
		p.setProperty("gui", "true");
		ProfileImpl profile = new ProfileImpl(p);
		// créer le main container
		mainContainer = rt.createMainContainer(profile);
	}
	
	public ContainerController getContainer() { return mainContainer; }
}
