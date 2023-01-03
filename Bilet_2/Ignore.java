package Bilet_2;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class Ignore extends Behaviour {


    @Override
    public void action() {
        MessageTemplate mt  = MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE);
        ACLMessage receive = getAgent().receive(mt);
        if (receive!= null) {
            log.info("A selling is ignored");
            log.info("Buyer wants to repeat a request for Seller");
           getAgent().addBehaviour(new BuyerBehaviour(getAgent(),10000));
           getAgent().addBehaviour(new WaitForRequest(getAgent()));
        }


    }
    public boolean done() {
        return false;
    }


}
