package ma.ensias.sma;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;

public class JadeContainer {

	private ContainerController container;
	
	public JadeContainer() {
		// Créer une instance de la MV
		Runtime rt = Runtime.instance();
		// Pas de propriétés, ce n'et pas un main container, mais un profile !
		ProfileImpl profile = new ProfileImpl(false);
		// Le main container associé est déjà démarré sur localhost
		profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
		profile.setParameter(ProfileImpl.CONTAINER_NAME, "Market");
		container = rt.createAgentContainer(profile); // Créer le container
	}

	public ContainerController getContainer() { return container; }
}

