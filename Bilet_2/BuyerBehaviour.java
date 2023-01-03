package Bilet_2;

import Agents.DFhelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BuyerBehaviour extends WakerBehaviour {
    DFhelper dFhelper = new DFhelper();
    List<AID> agents = new ArrayList<>();
    public BuyerBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onWake() {
        log.info("Seller wants to buy");
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        String content = "Wants to buy";
        msg.setContent(content);
        msg.setProtocol(getAgent().getLocalName());
        agents = dFhelper.findAgents(getAgent(),"Seller");
        for(AID agent:agents)
                msg.addReceiver(agent);
        getAgent().send(msg);
        log.info("Запрос о покупке отправлен");
        block();

    }


}
