package Agents.Distributon;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.TreeMap;

@Slf4j
public class TakeQuest extends Behaviour {

    Boolean finish = false;
    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
        ACLMessage receive = getAgent().receive(mt);

        if (receive != null){

            String maxPrice = "100000";
            String[] receiveMessage = receive.getContent().split(",");
            String whichAgent = receive.getProtocol();
            String neededPower = receiveMessage[0];
            String whichHour = receiveMessage[1];
            //log.info(receive.getContent()+"контент");
            log.info(getAgent().getLocalName() + " получил запрос на поставку от " + whichAgent
            + " на " + neededPower + " кВТ");
            getAgent().addBehaviour(new FSMDistributor(whichAgent, neededPower, whichHour, maxPrice, new TreeMap<>()));

        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
}
