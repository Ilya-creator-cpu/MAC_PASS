package Agents.Distributon;

import Agents.TopicHelper;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.TreeMap;
@Slf4j
public class PharallelReceiveAnswers extends ParallelBehaviour {
    int timeout;
    Map<String, String > producerMap = new TreeMap<>();
    public PharallelReceiveAnswers(Agent a, Map<String, String> phMap) {
        super(a, WHEN_ANY);
        this.producerMap = phMap;

        addSubBehaviour(new ReceiveAnsweres(myAgent, producerMap));
        addSubBehaviour(new WakerBehaviour(getAgent(), 9000) {
            @Override
            protected void onWake() {
                ACLMessage msg = new ACLMessage(ACLMessage.CANCEL);
                msg.setContent("Time out. Auction finished");
                msg.addReceiver(TopicHelper.createTopic(myAgent, (getAgent().getLocalName()+"Topic")));
                log.info("Время ожидания ответов истекло");
                getAgent().send(msg);
            }
        });
    }
}
