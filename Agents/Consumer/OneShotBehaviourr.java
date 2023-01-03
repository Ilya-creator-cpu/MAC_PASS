package Agents.Consumer;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class OneShotBehaviourr extends OneShotBehaviour {
    String agentName;
    String power;
    String hour;
    String distributer;

    int i = 0;
    String information;
    public OneShotBehaviourr(String agentName, String power, String hour, String distributer) {
        this.agentName = agentName;
        this.power = power;
        this.hour = hour;
        this.distributer = distributer;
    }

    @Override
    public void action() {
        System.out.println( agentName + "  "+ power + "  "+ hour);
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        information = (agentName +"," + power+","+hour);
        message.setContent(information);
        message.addReceiver(new AID(distributer, false));
        getAgent().send(message);
        i++;
    }
}
