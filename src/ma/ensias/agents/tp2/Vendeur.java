package ma.ensias.agents.tp2;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Vendeur extends Agent  {

	@Override
	protected void setup() {
		addBehaviour( new ReceiveAndReplyToMessages());
	}
}

class ReceiveAndReplyToMessages extends CyclicBehaviour {
	@Override
	public void action() {
		// Réception du message
		MessageTemplate template = 
				MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CFP), 
						MessageTemplate.MatchOntology("commerce"));				
		ACLMessage message = myAgent.receive(template);
		if (message != null) {
			System.out.println("Reception du message :" + message.getContent());
		} else {
			block();
		}
		
		// Envoi de la réponse
		ACLMessage reply = message.createReply();
		reply.setContent("440");
		myAgent.send(reply);
	}
}
