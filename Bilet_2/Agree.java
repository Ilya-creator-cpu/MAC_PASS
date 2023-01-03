package Bilet_2;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Agree extends Behaviour {

    boolean flag;
    @Override
    public void action() {
        MessageTemplate mt  = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
        ACLMessage receive = getAgent().receive(mt);
        if (receive!= null) {
            log.info("A selling is confirm");
            flag = true;
            }


    }

    @Override
    public boolean done() {
        return flag;
    }
}
