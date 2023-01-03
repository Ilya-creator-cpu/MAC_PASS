package Bilet_2;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SellerBehaviour extends Behaviour {

    DFhelper dFhelper = new DFhelper();

    List<AID> agents = new ArrayList<>();

    ACLMessage msg = new ACLMessage(ACLMessage.PROPAGATE);

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage receive = getAgent().receive(mt);
        if (receive!= null) {
            agents = dFhelper.findAgents(getAgent(),"Buyer");

            log.info("Seller was received a message");
            int a = (int) (Math.random() * 3);
            switch (a) {
                case 0:
                     msg = new ACLMessage(ACLMessage.AGREE);
                     log.info("Даем согласие");
                    break;
                case 1:
                       msg = new ACLMessage(ACLMessage.PROPAGATE);//игнорирование
                    log.info("Игнорируем");
                    break;
                case 2:
                        msg = new ACLMessage(ACLMessage.INFORM); //отказ
                    log.info("Отказываем");
                    break;
            }
            for(AID agent:agents)
                msg.addReceiver(agent);
            getAgent().send(msg);

        }

    }

    @Override
    public boolean done() {
        return false;
    }





}
