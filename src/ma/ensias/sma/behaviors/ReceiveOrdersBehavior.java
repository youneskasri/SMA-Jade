package ma.ensias.sma.behaviors;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ma.ensias.sma.beans.Order;

public class ReceiveOrdersBehavior extends CyclicBehaviour {
	@Override
	public void action() {
		// Réception du message			
		ACLMessage message = receiveMessage(ACLMessage.REQUEST);		
		try {
			if (message != null) { 
				Order order = (Order)message.getContentObject(); 
				System.out.println("Réception de la commande :" + order.toString());
			} else {
				block();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private ACLMessage receiveMessage(int performative) {
		MessageTemplate template = 
				MessageTemplate.and(MessageTemplate.MatchPerformative(performative), 
						MessageTemplate.MatchOntology("commerce"));			
		ACLMessage message = myAgent.receive(template);
		return message;
	}
	
}