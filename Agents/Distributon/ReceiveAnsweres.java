package Agents.Distributon;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j

public class ReceiveAnsweres extends Behaviour {

    int count = 0;
    Map<String, String > producerMap = new HashMap<>();

    public ReceiveAnsweres(Agent a, Map<String, String> producerMap) {
        super(a);
        this.producerMap = producerMap;
    }

    @Override
    public void action() {

        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE);
        ACLMessage receive = getAgent().receive(mt);

        if (receive != null) {
            log.info(getAgent().getLocalName() + " получил ставку от " + receive.getSender());
            String[] auctionMessages = receive.getContent().split(",");
            producerMap.put(auctionMessages[0],auctionMessages[1]);



        }
    }

    @Override
    public boolean done() {

        return false;
    }
}
