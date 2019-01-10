package ma.ensias.agents.tp2;

import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

public class Vendeur2 extends Agent {

	Map<String, Double> leCatalogue = new HashMap<String, Double>(10);
	
	@Override
	protected void setup() {
		// attend un CFP...
		MessageTemplate template = MessageTemplate.and(
				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET), 
				MessageTemplate.MatchPerformative(ACLMessage.CFP));
		
		Object args[] = this.getArguments();// Le catalogue en argument	
		if (args == null || args.length < 1 ) {
			System.out.println("Usage : <catalogue>");
			doDelete(); // appelle takeDown
		} else {
			leCatalogue = (Map<String, Double>)args[0];
			System.out.println("Agent ("+ this.getLocalName()+") crée");			
			addBehaviour(new VendeurCNR(this, template));
		}	
	}
	
	private final class VendeurCNR extends ContractNetResponder {
		private VendeurCNR(Agent a, MessageTemplate mt) {
			super(a, mt);
		}

		@Override
		protected ACLMessage handleCfp(ACLMessage cfp)
				throws RefuseException, FailureException, NotUnderstoodException {
			String title = cfp.getContent();
			if (leCatalogue.containsKey(title)) {
				Double price = leCatalogue.get(title);
				ACLMessage propose = cfp.createReply();
				propose.setPerformative(ACLMessage.PROPOSE);
				propose.setContent(price.toString());
				return propose;
			}
			else throw new RefuseException("Objet non disponible");
		}
		
		@Override
		protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept)
				throws FailureException {
			String title = cfp.getContent();
			if (leCatalogue.containsKey(title)) {
				ACLMessage inform = accept.createReply();
				inform.setPerformative(ACLMessage.INFORM);
				leCatalogue.remove(title);
				return inform;
			}
			else throw new FailureException("Vente impossible");
		}
	}
	
	
}
