package ma.ensias.sma.behaviors;

import java.io.IOException;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ma.ensias.sma.beans.Order;
import ma.ensias.sma.beans.Product;

public class ReceiveAndOrderProductsBehavior extends CyclicBehaviour {
	
	@Override
	public void action() {
		ACLMessage message = receiveMessage(ACLMessage.INFORM);	
		if (message != null) {
			System.out.println("Received an INFORM message");
			Order order = generateOrderForTheProduct(message);	
			//System.out.println("TEST: Received = "+message.getContent());
			if (order != null) sendOrder(message, order, ACLMessage.REQUEST);
			/*ACLMessage reply = message.createReply();
			reply.setPerformative(ACLMessage.REQUEST);
			reply.setContent("TEST REPLIED WITH ORDER"); 
			myAgent.send(reply); */
			
		} else {
			block();
		}
		
	}
	
	private ACLMessage receiveMessage(int performative) {
		MessageTemplate template = 
				MessageTemplate.and(MessageTemplate.MatchPerformative(performative), 
						MessageTemplate.MatchOntology("commerce"));				
		ACLMessage message = myAgent.receive(template);
		return message;
	}

	private Order generateOrderForTheProduct(ACLMessage message) {
		try { 
			Product product = (Product) message.getContentObject();
			System.out.println("message content (product): " + product);
			double pmax = 1200, qmax = 2400;
			return new Order(product, pmax, qmax);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void sendOrder(ACLMessage message, Order order, int performative) {
		ACLMessage reply = message.createReply();
		reply.setPerformative(performative);
		try { reply.setContentObject(order);
		} catch (IOException e) { e.printStackTrace(); }		
		myAgent.send(reply);
	}

}
