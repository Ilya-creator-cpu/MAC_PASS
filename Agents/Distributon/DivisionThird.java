package Agents.Distributon;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
@Slf4j

public class DivisionThird extends Behaviour {
    Boolean agreement = false;
    Boolean finish = false;
    String whichAgent;
    int cnt;


    public DivisionThird(String whichAgent) {
        this.whichAgent = whichAgent;
    }


    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.or(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM_IF),
                MessageTemplate.MatchPerformative(ACLMessage.INFORM_REF));
        ACLMessage receive = getAgent().receive(mt);


        if (receive != null) {
            if (receive.getPerformative() == ACLMessage.INFORM_IF) {
                log.info(getAgent().getLocalName() + " получил согласие");

                ACLMessage msg = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);                                     //отправка  первого сообщ в тоПик
                msg.setContent("halth"+","+receive.getContent());
                msg.addReceiver(new AID(whichAgent, false));
                getAgent().send(msg);

                agreement = true;
                finish = true;
            }

            if (receive.getPerformative() == ACLMessage.INFORM_REF) {
                log.info(getAgent().getLocalName() + " получил еще один отказ отправляю новое cообщ в новом поведении");
                cnt++;
                if(cnt==2){
                    ACLMessage msg = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);                                     //отправка  первого сообщ в тоПик
                    msg.setContent("Без энергии");
                    msg.addReceiver(new AID(whichAgent, false));
                    getAgent().send(msg);
                    agreement = true;
                    finish = true;
                }

            }


        }


    }

    @Override
    public boolean done() {
        return finish;
    }


}
