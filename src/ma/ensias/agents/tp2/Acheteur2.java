package ma.ensias.agents.tp2;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;


public class Acheteur2  extends Agent {

	String titre;
	static double prix;
	static boolean finTransaction;
	
	@Override
	protected void setup() {
		finTransaction = false;
		receiveArgumentsOrStop();
		addBehaviour( new OneShotBehaviour() {
			@Override
			public void action() {
				if (!Acheteur2.finTransaction) {
					ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
					cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
					// On veut recevoir la réponse dans 2 secondes
					cfp.setReplyByDate(new Date(System.currentTimeMillis() + 2000));
					for (int i = 0; i < 4; i++)
						cfp.addReceiver(new AID("Vendeur"+i, AID.ISLOCALNAME));
					cfp.setContent(titre);
					addBehaviour(new AcheteurCNI(myAgent, cfp));
				} else {
					doDelete();
				}
			}
		});
	}

	
	@SuppressWarnings("deprecation")
	private void receiveArgumentsOrStop() {		
		Object args[] = this.getArguments();
		if (args == null || args.length < 2) {
			System.out.println("Usage: <titre> <seuil>");
			doDelete(); // appelle takeDown();
		} else {
			titre = (String)args[0]; // Le livre et le prix en arguments
			System.out.println(titre);
			System.out.println("args1 = " +  args[1]);
			try {
			prix = Double.valueOf((Double)args[1]);
			}catch(ClassCastException e) {
				prix = Double.valueOf((String)args[1]); // To receive args from GUI Jade
			}
			System.out.println(prix);
			System.out.println("Agent acheteur (" + this.getLocalName() + ") crée "+
					"il désire acheter " + titre + " au prix " + prix);
		}
	}
}

/** Acheteur ContractNetInitiator */
class AcheteurCNI extends ContractNetInitiator {
	public AcheteurCNI(Agent agent, ACLMessage cfp) {
		super(agent, cfp);
	}
	
	/* Reception d'un message PROPOSE */
	@Override
	protected void handlePropose(ACLMessage propose, @SuppressWarnings("rawtypes") Vector acceptances) {
		System.out.println("Info (Acheteur) : " + propose.getSender().getLocalName()+
				" propose " + propose.getContent());
	}
	
	/* Reception d'un message REFUSE */
	protected void handleRefuse(ACLMessage refuse) {
		System.out.println("Info (Acheteur): " + refuse.getSender().getLocalName()+
			" ne possède pas l'object désiré");
	}
	
	// Réception d'un message FAILURE
	protected void handleFailure(ACLMessage failure) {
		if (failure.getSender().equals(myAgent.getAMS())) {
			System.out.println("Le destinataire n'existe pas");
		} else {
			System.out.println("Erreur: Action non réalisée ("+failure.getSender().getName()+")");
		}
		Acheteur2.finTransaction = true;		
	}
	
	@Override
	protected void handleInform(ACLMessage inform) {
		System.out.println("Succes: Achat réalisé avec succès auprès de l'agent " + inform.getSender().getName());
		Acheteur2.finTransaction = true;
	}
	
	// Préparation des réponses 
	@Override
	protected void handleAllResponses(Vector responses, Vector acceptances) {
		double bestPrice = Double.MAX_VALUE;
		ACLMessage accept = null;
		System.out.println("Préparation des réponses");
		for (Iterator iterator = responses.iterator(); iterator.hasNext();) {
			ACLMessage message = (ACLMessage) iterator.next();
			if (message.getPerformative() == ACLMessage.PROPOSE) {
				ACLMessage reply = message.createReply();
				reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
				acceptances.addElement(reply);

				double price = Double.parseDouble(message.getContent());
				if (price < bestPrice ) {
					bestPrice = price;
					accept = reply;
				}
			}	
		} 
		if (accept != null) {
			accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
		}
	} // handleAllResponses
	
} // class CNInitiator