package ma.ensias.agents.tp2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Acheteur extends Agent  {
	@Override
	protected void setup() {
		Object args[] = this.getArguments();
		System.out.println("args len = " + args.length);
		
		addBehaviour( new OneShotBehaviour() {			
			@Override
			public void action() {
				ACLMessage message = new ACLMessage(ACLMessage.CFP);
				message.addReceiver( new AID("vendeur", AID.ISLOCALNAME));
				message.setContent("Livre UML");
				message.setOntology("commerce");
				send(message);
			}
		});
	}
}
