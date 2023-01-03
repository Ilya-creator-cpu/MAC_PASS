package Agents.Production;

import Agents.TopicHelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import jade.core.Agent;
@Slf4j

public class SendPrice extends WakerBehaviour {

    String topicName;
    String content;
    AID topic;

    boolean finish;
    double firstPrice;
    double ability;
    double minPrice;


    public SendPrice(Agent a, long timeout,String topicName, double ability, double minPrice, double firstPrice) {
        super(a, timeout);
        this.topicName = topicName;
        this.minPrice = minPrice;
        this.ability = ability;
        this.firstPrice = firstPrice;
    }

    @Override
    public void onWake() {
        topic = TopicHelper.createTopic(getAgent(),topicName);
            ACLMessage msg = new ACLMessage(ACLMessage.PROPAGATE);
            msg.setContent(getAgent().getLocalName() + "," + String.valueOf(firstPrice));
            msg.addReceiver(topic);
            getAgent().send(msg);
          //  log.info(getAgent().getLocalName() + "Отправил первую ставку" + "топик " + topic.getName());
            finish = true;


    }

    @Override
    public int onEnd() {
        return super.onEnd();
    }
}
